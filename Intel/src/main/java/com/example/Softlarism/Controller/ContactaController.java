package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.ContactaDto;
import com.example.Softlarism.Model.Contacta;
import com.example.Softlarism.Service.ContactaService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping ("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class ContactaController {

    private final ContactaService contactaService;

    @RequestMapping("/Contacta")
    public ResponseEntity<List<ContactaDto>> lista (){
        List<Contacta> contactas = contactaService.getAll();
        if (contactas == null || contactas.size() == 0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity
                .ok(
                        contactas
                                .stream()
                                .map(u ->ContactaDto.builder()
                                        .id_autoridad(u.getId_autoridad())
                                        .id_usuario(u.getId_usuario())
                                        .build()).collect(Collectors.toList()));
    }
    @RequestMapping ("/Contacta/{id}")
    public ResponseEntity<ContactaDto>getById(@PathVariable Integer id)
    {
        Contacta u = contactaService.getById(id);
        if (u == null){
            return  ResponseEntity.notFound( ).build( );
        }
        return ResponseEntity.ok(ContactaDto.builder()
                .id_autoridad(u.getId_autoridad())
                .id_usuario(u.getId_usuario())
                .build());
    }
    @PostMapping ("/Contacta")
    public ResponseEntity<ContactaDto> save(@RequestBody ContactaDto contactaDto){
        Contacta u = Contacta
                .builder().id_autoridad(contactaDto.getId_autoridad())
                .id_usuario(contactaDto.getId_usuario())
                .build();
        contactaService.save(u);
        return ResponseEntity.ok(ContactaDto.builder()
                .id_autoridad(u.getId_autoridad())
                .id_usuario(u.getId_usuario()).build());
    }
    @DeleteMapping("/Contacta/{id}")
    public ResponseEntity<ContactaDto> delete(@PathVariable Integer id){
        contactaService.delete(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping ("/Contacta/{id}")
    public ResponseEntity<ContactaDto> update(@PathVariable Integer id, @RequestBody ContactaDto contactaDto){
        Contacta aux = contactaService.update(id,Contacta
                .builder( )
                    .id_autoridad(contactaDto.getId_autoridad())
                    .id_usuario(contactaDto.getId_usuario())
                .build( ));
        return ResponseEntity.ok(ContactaDto.builder()
                .id_autoridad(aux.getId_autoridad())
                .id_usuario(aux.getId_usuario()).build());
    }
}
