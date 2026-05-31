package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.AutoridadesDto;
import com.example.Softlarism.Model.Autoridades;
import com.example.Softlarism.Service.AutoridadesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class AutoridadesController {

    private final AutoridadesService autoridadesService;

    @RequestMapping("/Autoridades")
    public ResponseEntity<List<AutoridadesDto>> lista( ){
        List<Autoridades> autoridades = autoridadesService.getAll();
        if (autoridades == null || autoridades.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(
                        autoridades
                                .stream()
                                .map(u ->AutoridadesDto.builder()
                                        .id_autoridad(u.getId_autoridad())
                                        .anombre(u.getAnombre())
                                        .contacto(u.getContacto())
                                        .build())
                                .collect(Collectors.toList()));
    }

    @RequestMapping("/Autoridades({id}")
    public ResponseEntity<AutoridadesDto> getById(@PathVariable Integer id){
        Autoridades u = autoridadesService.getById(id);
        if (u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AutoridadesDto.builder()
                .id_autoridad(u.getId_autoridad())
                .anombre(u.getAnombre())
                .contacto(u.getContacto())
                .build());
    }

    @PostMapping("/Autoridades")
    public ResponseEntity<AutoridadesDto> save(@RequestBody AutoridadesDto autoridadesDto){
        Autoridades u = Autoridades
                .builder()
                .id_autoridad(autoridadesDto.getId_autoridad())
                .anombre(autoridadesDto.getAnombre())
                .contacto(autoridadesDto.getContacto())
                .build();
        autoridadesService.save(u);
        return ResponseEntity.ok(AutoridadesDto.builder()
                .id_autoridad(u.getId_autoridad())
                .anombre(u.getAnombre())
                .contacto(u.getContacto())
                .build());
    }

    @DeleteMapping("/Autoridades/{id}")
    public ResponseEntity<AutoridadesDto> delete(@PathVariable Integer id){
        autoridadesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Autoridades/{id}")
    public ResponseEntity<AutoridadesDto> update(@PathVariable Integer id, @RequestBody AutoridadesDto autoridadesDto){
        Autoridades aux = autoridadesService.update(id,Autoridades
                .builder()
                .id_autoridad(autoridadesDto.getId_autoridad())
                .anombre(autoridadesDto.getAnombre())
                .contacto(autoridadesDto.getContacto())
                .build());
        return ResponseEntity.ok(AutoridadesDto.builder()
                .id_autoridad(aux.getId_autoridad())
                .anombre(aux.getAnombre())
                .contacto(aux.getContacto())
                .build( ));
    }
}
