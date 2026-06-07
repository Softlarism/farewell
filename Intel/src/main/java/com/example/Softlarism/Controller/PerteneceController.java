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

    // GET todas las relaciones
    @GetMapping("/Pertenece")
    public ResponseEntity<List<PerteneceDto>> lista() {
        List<Pertenece> perteneces = perteneceService.getAll();
        if (perteneces == null || perteneces.isEmpty()) {
            return ResponseEntity.ok(List.of());   // lista vacía en vez de 404
        }
        return ResponseEntity.ok(perteneces.stream().map(this::toDto).collect(Collectors.toList()));
    }

    // GET por id de la relación
    @GetMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> getById(@PathVariable Integer id) {
        Pertenece u = perteneceService.getById(id);
        if (u == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(u));
    }

    // ── NUEVO: GET comunidades de un usuario ──
    @GetMapping("/Pertenece/usuario/{idUsuario}")
    public ResponseEntity<List<PerteneceDto>> porUsuario(@PathVariable Integer idUsuario) {
        List<Pertenece> lista = perteneceService.getByUsuario(idUsuario);
        return ResponseEntity.ok(lista.stream().map(this::toDto).collect(Collectors.toList()));
    }

    // POST: inscribir usuario en comunidad (evita duplicados)
    @PostMapping("/Pertenece")
    public ResponseEntity<PerteneceDto> save(@RequestBody PerteneceDto perteneceDto) {

        // Si ya pertenece, no creamos un duplicado
        if (perteneceDto.getId_usuario() != null && perteneceDto.getId_comunidad() != null
                && perteneceService.existeRelacion(perteneceDto.getId_usuario(), perteneceDto.getId_comunidad())) {
            return ResponseEntity.ok(perteneceDto);
        }

        Pertenece u = Pertenece.builder()
                .id_usuario(perteneceDto.getId_usuario())
                .id_comunidad(perteneceDto.getId_comunidad())
                .build();
        Pertenece guardado = perteneceService.save(u);   // ── CORRECCIÓN: devolvemos lo realmente guardado ──
        return ResponseEntity.ok(toDto(guardado));
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