package com.example.demo.controller;
import com.example.demo.entity.Proveedores;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.ProveedoresService;
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
@RequestMapping("/api/v1/Proveedores")
public class ProveedoresController {
    @Autowired
    private ProveedoresService service;
    @PostMapping("/addProveedor")
    public ResponseEntity<Object> addProveedor(@RequestBody Proveedores proveedores){
        try{
            service.saveProveedores(proveedores);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_PROVEEDORES + proveedores.getId());
            return new ResponseEntity(proveedores,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addProveedores")
    public ResponseEntity<Any> addProveedores(@RequestBody List<Proveedores> proveedores){
        try{
            return new ResponseEntity(service.saveProveedores(proveedores), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Proveedores>> findAllProveedores(){
        try{
            return new ResponseEntity(service.getProveedores(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Proveedores> findProveedorById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getProveedoresById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Proveedores> findProveedorByName(@PathVariable String name){
        try{
            return new ResponseEntity(service.getProveedoresByName(name), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateProveedores(@RequestBody Proveedores proveedores){
        try {
            service.updateProveedores(proveedores);
            return new ResponseEntity(proveedores,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteProveedor(@PathVariable long id){
        try {
            service.deleteProveedores(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}