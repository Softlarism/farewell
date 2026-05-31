package com.example.Softlarism.Controller;

import com.example.Softlarism.Dto.UsuariosDto;
import com.example.Softlarism.Model.Usuarios;
import com.example.Softlarism.Repository.UsuariosRepository;
import com.example.Softlarism.Service.UsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping ("/Softlarism/api")
@RestController
@CrossOrigin (origins = "*")
@AllArgsConstructor
public class UsuariosController {
    @Autowired
    private UsuariosRepository usuariosRepository;
    private final UsuariosService usuariosService;
    private List<UsuariosDto> usuariosDtos;

    public void loadList(){
        usuariosDtos = new ArrayList<>();
        for (int i = 0 ; i < 10; i++){
            usuariosDtos.add(
                    UsuariosDto.builder().
                            id_usuario(i++)
                            .telefono("Telefono "+i)
                            .contrasena("Contraseña "+i)
                            .nombre("Nombre "+ i)
                            .apellido("Apellido "+i)
                            .cp1("Codigo Postal "+i)
                            .numero("Numero "+i)
                            .build()
            );
        }
    }
    @RequestMapping("/Usuarios")
    public ResponseEntity<List<UsuariosDto>> lista (@RequestParam (name = "nombre",defaultValue = "",required = false)String nombre){
        List<Usuarios> usuarios = usuariosService.getAll();
        if (usuarios == null || usuarios.size() == 0){
            return ResponseEntity.notFound().build();
        }
        if ( nombre != null && !nombre.isEmpty()){
            return ResponseEntity.ok(usuarios
                    .stream()
                    .filter(u -> u.getNombre().equals(nombre) )
                    .map(u -> UsuariosDto.builder()
                            .id_usuario(u.getId_usuario())
                            .telefono(u.getTelefono())
                            .contrasena(u.getContrasena())
                            .nombre(u.getNombre())
                            .apellido(u.getApellido())
                            .cp1(u.getCp1())
                            .numero(u.getNumero()).build())
                    .collect(Collectors.toList()));

        }
        return ResponseEntity
                .ok(
                        usuarios
                                .stream()
                                .map(u -> UsuariosDto.builder()
                                        .id_usuario(u.getId_usuario())
                                        .telefono(u.getTelefono())
                                        .contrasena(u.getContrasena())
                                        .nombre(u.getNombre())
                                        .apellido(u.getApellido())
                                        .cp1(u.getCp1())
                                        .numero(u.getNumero()).build()).collect(Collectors.toList())

                );
    }
    @RequestMapping("/Usuarios/{id}")
    public ResponseEntity<UsuariosDto> getById(@PathVariable Integer id_usuario){

        Usuarios u =  usuariosService.getById(id_usuario);
        if ( u == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(UsuariosDto.builder()
                .id_usuario(u.getId_usuario())
                .telefono(u.getTelefono())
                .contrasena(u.getContrasena())
                .nombre(u.getNombre())
                .apellido(u.getApellido())
                .cp1(u.getCp1()).numero(u.getNumero()).build());
    }
    @PostMapping ("/Usuarios")
    public ResponseEntity<UsuariosDto> save(@RequestBody UsuariosDto usuariosDto){

        Usuarios u = Usuarios
                .builder().nombre(usuariosDto.getNombre())
                .id_usuario(usuariosDto.getId_usuario())
                .telefono(usuariosDto.getTelefono())
                .contrasena(usuariosDto.getContrasena())
                .apellido(usuariosDto.getApellido())
                .cp1(usuariosDto.getCp1())
                .numero(usuariosDto.getNumero()).build();
        usuariosService.save(u);
        return ResponseEntity.ok(UsuariosDto.builder()
                .id_usuario(u.getId_usuario())
                .nombre(u.getNombre())
                .telefono(u.getTelefono())
                .contrasena(u.getContrasena())
                .apellido(u.getApellido()).cp1(u.getCp1())
                .cp1(u.getCp1()).build());
    }
    @DeleteMapping("/Usuarios/{id}")
    public ResponseEntity<UsuariosDto> delete(@PathVariable Integer id_usuario,@RequestBody UsuariosDto usuariosDto){

        usuariosService.delete(id_usuario);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/Usuarios/{id}")
    public ResponseEntity<UsuariosDto> update(@PathVariable Integer id_usuario,@RequestBody UsuariosDto usuariosDto){

        Usuarios aux = usuariosService.update(id_usuario,Usuarios
                .builder()
                .nombre(usuariosDto.getNombre())
                .apellido(usuariosDto.getApellido())
                .telefono(usuariosDto.getTelefono())
                .contrasena(usuariosDto.getContrasena())
                .cp1(usuariosDto.getCp1())
                .numero(usuariosDto.getNumero())
                .build());
        return ResponseEntity.ok(UsuariosDto.builder()
                .id_usuario(aux.getId_usuario())
                .nombre(aux.getNombre())
                .apellido(aux.getApellido())
                .telefono(aux.getTelefono())
                .contrasena(aux.getContrasena())
                .cp1(aux.getCp1()).numero(aux.getNumero())
                .build());
    }

    // los endpoins
    @PostMapping ("/Usuarios/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuarios usuarios){
        try{
            Optional<Usuarios> usuariosExistente = usuariosRepository.findByTelefono(usuarios.getTelefono());
            if (usuariosExistente.isPresent()){
                return ResponseEntity.status(400).body("El telefono ya esta registrado");
            }

            Usuarios nuevosUsuarios = usuariosRepository.save(usuarios);
            return ResponseEntity.ok(nuevosUsuarios);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al registrar usuario: "+ e.getMessage());
        }
    }

    @PostMapping("/Usuarios/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> credenciales){
        try {
            String telefono = credenciales.get("telefono");
            String contrasena = credenciales.get("contrasena");

            Optional<Usuarios> usuarios = usuariosRepository.findByTelefonoAndContrasena(telefono,contrasena);

            if (usuarios.isPresent()){
                return ResponseEntity.ok(usuarios.get());
            }else {
                return ResponseEntity.status(401).body("Telefono o contraseña incorrectos");
            }
        }catch (Exception e){
            return ResponseEntity.status(500).body("Error en el login: "+ e.getMessage());
        }
    }
}
