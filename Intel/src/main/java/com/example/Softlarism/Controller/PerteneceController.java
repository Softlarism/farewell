package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.PerteneceDto;
import com.example.Softlarism.Model.Pertenece;
import com.example.Softlarism.Service.PerteneceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/Softlarism/api")
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PerteneceController {

    private final PerteneceService perteneceService;

    private PerteneceDto toDto(Pertenece u) {
        return PerteneceDto.builder()
                .id_pertenece(u.getId_pertenece())
                .id_usuario(u.getId_usuario())
                .id_comunidad(u.getId_comunidad())
                .build();
    }

    @GetMapping("/Pertenece")
    public ResponseEntity<List<PerteneceDto>> lista() {
        List<Pertenece> perteneces = perteneceService.getAll();
        if (perteneces == null || perteneces.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(perteneces.stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> getById(@PathVariable Integer id) {
        Pertenece u = perteneceService.getById(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(u));
    }

    @GetMapping("/Pertenece/usuario/{idUsuario}")
    public ResponseEntity<List<PerteneceDto>> porUsuario(@PathVariable Integer idUsuario) {
        List<Pertenece> lista = perteneceService.getByUsuario(idUsuario);
        return ResponseEntity.ok(lista.stream().map(this::toDto).collect(Collectors.toList()));
    }

    // POST: inscribir usuario en comunidad (evita duplicados y nunca lanza 500)
    @PostMapping("/Pertenece")
    public ResponseEntity<?> save(@RequestBody PerteneceDto perteneceDto) {
        try {
            if (perteneceDto.getId_usuario() == null || perteneceDto.getId_comunidad() == null) {
                return ResponseEntity.badRequest()
                        .body("Se requieren id_usuario e id_comunidad.");
            }

            // Si ya pertenece, devolvemos la fila existente (CON su id_pertenece)
            if (perteneceService.existeRelacion(perteneceDto.getId_usuario(), perteneceDto.getId_comunidad())) {
                for (Pertenece p : perteneceService.getByUsuario(perteneceDto.getId_usuario())) {
                    if (p.getId_comunidad() != null
                            && p.getId_comunidad().equals(perteneceDto.getId_comunidad())) {
                        return ResponseEntity.ok(toDto(p));   // incluye id_pertenece
                    }
                }
                return ResponseEntity.ok(perteneceDto);
            }

            Pertenece u = Pertenece.builder()
                    .id_usuario(perteneceDto.getId_usuario())
                    .id_comunidad(perteneceDto.getId_comunidad())
                    .build();
            Pertenece guardado = perteneceService.save(u);
            return ResponseEntity.ok(toDto(guardado));

        } catch (Exception e) {
            // Si el guardado falla (p. ej. choque con la restricción única),
            // lo tratamos como "ya inscrito" en vez de devolver 500.
            return ResponseEntity.ok(perteneceDto);
        }
    }

    @DeleteMapping("/Pertenece/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        perteneceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> update(@PathVariable Integer id, @RequestBody PerteneceDto perteneceDto) {
        Pertenece aux = perteneceService.update(id, Pertenece.builder()
                .id_usuario(perteneceDto.getId_usuario())
                .id_comunidad(perteneceDto.getId_comunidad())
                .build());
        if (aux == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(aux));
    }
}