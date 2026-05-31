package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.ComunidadDto;
import com.example.Softlarism.Model.Comunidad;
import com.example.Softlarism.Repository.ComunidadRepository;
import com.example.Softlarism.Service.ComunidadService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping ("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class ComunidadController {
    private final ComunidadService comunidadService;

    @GetMapping("/Comunidad")
    public ResponseEntity<List<ComunidadDto>> lista( ){
        List<Comunidad> comunidads = comunidadService.getAll();
        if (comunidads == null || comunidads.isEmpty()){
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity
                .ok(
                        comunidads
                                .stream()
                                .map(u -> ComunidadDto.builder()
                                        .id_comunidad(u.getId_comunidad())
                                        .cnombre(u.getCnombre())
                                        .descripcion(u.getDescripcion())
                                        .tipo(u.getTipo())
                                        .cp2(u.getCp2())
                                        .build())
                                .collect(Collectors.toList()));
    }
    @GetMapping("/Comunidad/{id}")
    public ResponseEntity<ComunidadDto> getById(@PathVariable Integer id){
        Comunidad u = comunidadService.getById(id);
        if (u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ComunidadDto.builder()
                .id_comunidad(u.getId_comunidad())
                .cnombre(u.getCnombre())
                .descripcion(u.getDescripcion())
                .tipo(u.getTipo())
                .cp2(u.getCp2())
                .build());
    }

    @PostMapping("/Comunidad")
    public ResponseEntity<ComunidadDto> save(@RequestBody ComunidadDto comunidadDto){

        Comunidad u = Comunidad
                .builder()
                .cnombre(comunidadDto.getCnombre())
                .descripcion(comunidadDto.getDescripcion())
                .tipo(comunidadDto.getTipo())
                .cp2(comunidadDto.getCp2()).build();
        Comunidad guardada = comunidadService.save(u);
        return ResponseEntity.ok(ComunidadDto.builder()
                .id_comunidad(guardada.getId_comunidad())
                .cnombre(guardada.getCnombre())
                .descripcion(guardada.getDescripcion())
                .tipo(guardada.getTipo())
                .cp2(guardada.getCp2())
                .build());
    }
    @DeleteMapping("/Comunidad/{id}")
    public ResponseEntity<ComunidadDto> delete(@PathVariable Integer id){
        comunidadService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Comunidad/{id}")
    public ResponseEntity<ComunidadDto> update(@PathVariable Integer id,@RequestBody ComunidadDto comunidadDto){
        Comunidad aux = comunidadService.update(id,Comunidad
                .builder( )
                .id_comunidad(comunidadDto.getId_comunidad())
                .cnombre(comunidadDto.getCnombre())
                .descripcion(comunidadDto.getDescripcion())
                .tipo(comunidadDto.getTipo())
                .cp2(comunidadDto.getCp2()).build());

        return ResponseEntity.ok(ComunidadDto.builder()
                .id_comunidad(aux.getId_comunidad())
                .cnombre(aux.getCnombre())
                .descripcion(aux.getDescripcion())
                .tipo(aux.getTipo())
                .cp2(aux.getCp2())
                .build( ));
    }
}
