package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.EventosDto;
import com.example.Softlarism.Model.Eventos;
import com.example.Softlarism.Service.EventosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor

public class EventosController {
    private EventosService eventosService;

    @GetMapping("/Eventos")
    public ResponseEntity<List<EventosDto>> lista(){
        List<Eventos> eventos = eventosService.getAll();
        if (eventos == null || eventos.isEmpty()){
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity
                .ok(
                        eventos
                                .stream()
                                .map(u -> EventosDto.builder()
                                        .tipo(u.getTipo())
                                        .id_evento(u.getId_evento())
                                        .id_usuario(u.getId_usuario())
                                        .descripcion(u.getDescripcion())

                                        .build())
                                .collect(Collectors.toList()));
    }
    @GetMapping("/Eventos/{id}")
    public ResponseEntity<EventosDto> getById(@PathVariable Integer id){
        Eventos u = eventosService.getById(id);
        if (u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(EventosDto.builder()
                .tipo(u.getTipo())
                .id_evento(u.getId_evento())
                .id_usuario(u.getId_usuario())
                .descripcion(u.getDescripcion())

                .build());
    }
    @PostMapping("/Eventos")
    public ResponseEntity<EventosDto> save(@RequestBody EventosDto eventosDto){
        Eventos u = Eventos
                .builder()
                .tipo(eventosDto.getTipo())
                .descripcion(eventosDto.getDescripcion())

                .id_usuario(eventosDto.getId_usuario())
                .build();
        Eventos guardado = eventosService.save(u);
        return ResponseEntity.ok(EventosDto.builder()
                .tipo(guardado.getTipo())
                .id_evento(guardado.getId_evento())
                .id_usuario(guardado.getId_usuario())
                .descripcion(guardado.getDescripcion())

                .build());
    }
    @DeleteMapping ("/Eventos/{id}")
    public ResponseEntity<EventosDto> delete(@PathVariable Integer id){
        eventosService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/Eventos/{id}")
    public ResponseEntity<EventosDto> update(@PathVariable Integer id, @RequestBody EventosDto eventosDto){
        Eventos aux = eventosService.update(id,Eventos
                .builder()
                .tipo(eventosDto.getTipo())
                .id_evento(eventosDto.getId_evento())
                .id_usuario(eventosDto.getId_usuario())
                .descripcion(eventosDto.getDescripcion())
                .build());
        return ResponseEntity.ok(EventosDto.builder()
                .tipo(aux.getTipo())
                .id_evento(aux.getId_evento())
                .id_usuario(aux.getId_usuario())
                .descripcion(aux.getDescripcion())

                .build());
    }

}
