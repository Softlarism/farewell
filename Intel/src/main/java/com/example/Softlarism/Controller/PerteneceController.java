package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.PerteneceDto;
import com.example.Softlarism.Model.Pertenece;
import com.example.Softlarism.Service.PerteneceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping ("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class PerteneceController {
    private final PerteneceService perteneceService;

    @RequestMapping("/Pertenece")
    public ResponseEntity<List<PerteneceDto>> lista(){
        List<Pertenece> perteneces = perteneceService.getAll();
        if (perteneces == null || perteneces.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(perteneces
                        .stream()
                        .map(u -> PerteneceDto.builder()
                                .id_comunidad(u.getId_comunidad())
                                .id_usuario(u.getId_usuario()).build())
                        .collect(Collectors.toList()));
    }
    @RequestMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> getById(@PathVariable Integer id){
        Pertenece u = perteneceService.getById(id);
        if (u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(PerteneceDto.builder()
                .id_comunidad(u.getId_comunidad())
                .id_usuario(u.getId_usuario()).build());
    }
    @PostMapping("/Pertenece")
    public ResponseEntity<PerteneceDto> save (@RequestBody PerteneceDto perteneceDto){
        Pertenece u = Pertenece
                .builder().id_comunidad(perteneceDto.getId_comunidad())
                .id_usuario(perteneceDto.getId_usuario())
                .build();
        perteneceService.save(u);
        return ResponseEntity.ok(PerteneceDto.builder()
                .id_usuario(u.getId_usuario()).id_usuario(u.getId_usuario()).build());
    }
    @DeleteMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> delete(@PathVariable Integer id){
        perteneceService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/Pertenece/{id}")
    public ResponseEntity<PerteneceDto> update(@PathVariable Integer id,@RequestBody PerteneceDto perteneceDto){
        Pertenece aux = perteneceService.update(id,Pertenece
                .builder()
                .id_comunidad(perteneceDto.getId_comunidad())
                .id_usuario(perteneceDto.getId_usuario()).build());
        return ResponseEntity.ok(PerteneceDto.builder()
                .id_usuario(aux.getId_usuario()).id_comunidad(aux.getId_comunidad()).build());
    }

}
