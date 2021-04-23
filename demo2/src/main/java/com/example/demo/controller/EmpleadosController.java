package com.example.demo.controller;
import com.example.demo.entity.Empleados;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.EmpleadosService;
import com.example.demo.utils.Constants;
import com.example.demo.utils.RestApiError;
import org.hibernate.mapping.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Empleados")
public class EmpleadosController {
    @Autowired
    private EmpleadosService service;
    @PostMapping("/addEmpleado")
    public ResponseEntity<Object> addEmpleados(@RequestBody Empleados empleados){
        try{
            service.saveEmpleados(empleados);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_EMPLEADOS + empleados.getId());
            return new ResponseEntity(empleados,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addEmpleados")
    public ResponseEntity<Any> addEmpleados(@RequestBody List<Empleados> empleados){
        try{
            return new ResponseEntity(service.saveEmpleados(empleados), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Empleados>> findAllEmpleados(){
        try{
            return new ResponseEntity(service.getEmpleados(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Empleados> findEmpleadosbyId(@PathVariable long id){
        try{
            return new ResponseEntity(service.getEmpleadosbyId(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Empleados> findEmpleadosByName(@PathVariable String name){
        try{
            return new ResponseEntity(service.getEmpleadosbyName(name), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateEmpleados(@RequestBody Empleados empleados){
        try {
            service.updateEmpleados(empleados);
            return new ResponseEntity(empleados,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteEmpleados(@PathVariable long id){
        try {
            service.deleteEmpleados(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}