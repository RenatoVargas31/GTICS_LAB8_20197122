package com.app.gtics_lab8_20197122.restController;

import com.app.gtics_lab8_20197122.entity.Estudiante;
import com.app.gtics_lab8_20197122.repository.EstudianteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiante")
public class EstudianteRest {
    //Repository
    final EstudianteRepository estudianteRepository;

    public EstudianteRest(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    //Listar estudiante
    @GetMapping("/listar")
    public List<Estudiante> getEstudiantes(){
        return estudianteRepository.listarPorGPA();
    }
    //Listar filtrando por facultad con ResponseEntity y mensajes de error con try catch y if y else y que los mensajes de error se escriban en el json
    @GetMapping("/listar/{facultad}")
    public ResponseEntity<List<Estudiante>> getEstudiantesPorFacultad(@PathVariable String facultad){
        try {
            List<Estudiante> estudiantes = estudianteRepository.listarPorFacultad(facultad);
            if(estudiantes.isEmpty()){
                return ResponseEntity.noContent().build();
            }else{
                return ResponseEntity.ok(estudiantes);
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    //Guardar estudiante con Post ResponseEntity y mensajes de error con try catch y if y else y hashmap
    @PostMapping("/guardar")
    public ResponseEntity<HashMap<String,Object>> guardarEstudiante(@RequestBody Estudiante estudiante){
        try {
            estudianteRepository.save(estudiante);
            HashMap<String,Object> response = new HashMap<>();
            response.put("message","Estudiante guardado correctamente");
            return ResponseEntity.ok(response);
        }catch (Exception e){
            HashMap<String,Object> response = new HashMap<>();
            response.put("message","Error al guardar estudiante");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //Actualizar estudiante con Put mapeando el estudiante recibido y buscar por id para editar ResponseEntity y mensajes de error con try catch y if y else y hashmap
    @PutMapping("/actualizar")
    public ResponseEntity<HashMap<String,Object>> actualizarEstudiante(@RequestBody Estudiante estudiante){
        HashMap<String,Object> response = new HashMap<>();
        if(estudiante.getId() != null && estudiante.getId()>0) {
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudiante.getId());
            if(estudianteOptional.isPresent()){
                //Validar cada campo
                if(estudiante.getDni() != null){
                    estudianteOptional.get().setDni(estudiante.getDni());
                }
                if(estudiante.getNombre() != null && !estudiante.getNombre().isEmpty()){
                    estudianteOptional.get().setNombre(estudiante.getNombre());
                }
                if(estudiante.getFacultad() != null && !estudiante.getFacultad().isEmpty()){
                    estudianteOptional.get().setFacultad(estudiante.getFacultad());
                }
                if(estudiante.getGpa() != null && estudiante.getGpa().intValue()>0){
                    estudianteOptional.get().setGpa(estudiante.getGpa());
                }
                if(estudiante.getCreditosCom() != null && estudiante.getCreditosCom().intValue()>0){
                    estudianteOptional.get().setCreditosCom(estudiante.getCreditosCom());
                }
                estudianteRepository.save(estudianteOptional.get());
                response.put("message","Estudiante actualizado correctamente");
                return ResponseEntity.ok(response);
            }else{
                response.put("message","Estudiante no existente");
                return ResponseEntity.badRequest().body(response);
            }
        }else {
            response.put("message","Id no válido");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @PostMapping("/actualizar/xwwwform")
    public ResponseEntity<HashMap<String,Object>> actualizarEstudianteXwwwForm(Estudiante estudiante){
        HashMap<String,Object> response = new HashMap<>();
        if(estudiante.getId() != null && estudiante.getId()>0) {
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(estudiante.getId());
            if(estudianteOptional.isPresent()){
                //Validar cada campo
                if(estudiante.getDni() != null){
                    estudianteOptional.get().setDni(estudiante.getDni());
                }
                if(estudiante.getNombre() != null && !estudiante.getNombre().isEmpty()){
                    estudianteOptional.get().setNombre(estudiante.getNombre());
                }
                if(estudiante.getFacultad() != null && !estudiante.getFacultad().isEmpty()){
                    estudianteOptional.get().setFacultad(estudiante.getFacultad());
                }
                if(estudiante.getGpa() != null && estudiante.getGpa().intValue()>0){
                    estudianteOptional.get().setGpa(estudiante.getGpa());
                }
                if(estudiante.getCreditosCom() != null && estudiante.getCreditosCom().intValue()>0){
                    estudianteOptional.get().setCreditosCom(estudiante.getCreditosCom());
                }
                estudianteRepository.save(estudianteOptional.get());
                response.put("message","Estudiante actualizado correctamente");
                return ResponseEntity.ok(response);
            }else{
                response.put("message","Estudiante no existente");
                return ResponseEntity.badRequest().body(response);
            }
        }else {
            response.put("message","Id no válido");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //Eliminar estudiante con Delete ResponseEntity y mensajes de error con try catch y if y else y hashmap
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<HashMap<String,Object>> eliminarEstudiante(@PathVariable Integer id){
        HashMap<String,Object> response = new HashMap<>();
        if(id != null && id>0) {
            Optional<Estudiante> estudianteOptional = estudianteRepository.findById(id);
            if(estudianteOptional.isPresent()){
                estudianteRepository.delete(estudianteOptional.get());
                response.put("message","Estudiante eliminado correctamente");
                return ResponseEntity.ok(response);
            }else{
                response.put("message","Estudiante no existente");
                return ResponseEntity.badRequest().body(response);
            }
        }else {
            response.put("message","Id no válido");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
