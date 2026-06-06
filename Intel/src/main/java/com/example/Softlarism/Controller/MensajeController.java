package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.MensajeDto;
import com.example.Softlarism.Model.Mensaje;
import com.example.Softlarism.Service.MensajeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/Softlarism/api")
@RestController
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class MensajeController {

    private final MensajeService mensajeService;

    private MensajeDto toDto(Mensaje m) {
        return MensajeDto.builder()
                .id_mensaje(m.getId_mensaje())
                .contenido(m.getContenido())
                .id_usuario(m.getId_usuario())
                .id_comunidad(m.getId_comunidad())
                .fecha(m.getFecha())
                .build();
    }

    @GetMapping("/Mensaje")
    public ResponseEntity<List<MensajeDto>> lista() {
        List<Mensaje> mensajes = mensajeService.getAll();
        if (mensajes == null || mensajes.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(
                mensajes.stream().map(this::toDto).collect(Collectors.toList()));
    }

    @GetMapping("/Mensaje/{id}")
    public ResponseEntity<MensajeDto> getById(@PathVariable Integer id) {
        Mensaje m = mensajeService.getById(id);
        if (m == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(m));
    }

    // Todos los mensajes de una comunidad (orden de chat)
    @GetMapping("/Mensaje/comunidad/{idComunidad}")
    public ResponseEntity<List<MensajeDto>> porComunidad(@PathVariable Integer idComunidad) {
        List<Mensaje> mensajes = mensajeService.getByComunidad(idComunidad);
        if (mensajes == null || mensajes.isEmpty()) {
            return ResponseEntity.ok(List.of());
        }
        return ResponseEntity.ok(
                mensajes.stream().map(this::toDto).collect(Collectors.toList()));
    }

    @PostMapping("/Mensaje")
    public ResponseEntity<MensajeDto> save(@RequestBody MensajeDto dto) {
        Mensaje m = Mensaje.builder()
                .contenido(dto.getContenido())
                .id_usuario(dto.getId_usuario())
                .id_comunidad(dto.getId_comunidad())
                .fecha(dto.getFecha())   // si es null, el Service pone la hora del servidor
                .build();
        Mensaje guardado = mensajeService.save(m);
        return ResponseEntity.ok(toDto(guardado));
    }

    @DeleteMapping("/Mensaje/{id}")
    public ResponseEntity<MensajeDto> delete(@PathVariable Integer id) {
        mensajeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Mensaje/{id}")
    public ResponseEntity<MensajeDto> update(@PathVariable Integer id, @RequestBody MensajeDto dto) {
        Mensaje aux = mensajeService.update(id, Mensaje.builder()
                .contenido(dto.getContenido())
                .id_usuario(dto.getId_usuario())
                .id_comunidad(dto.getId_comunidad())
                .build());
        if (aux == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(toDto(aux));
    }
}
