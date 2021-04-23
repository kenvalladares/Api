package com.example.demo.controller;
import com.example.demo.entity.*;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.*;
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
@RequestMapping("/api/v1/Orden")
public class OrdenController {
    @Autowired
    private OrdenService service;
    @PostMapping("/addOrden")
    public ResponseEntity<Object> addOrden(@RequestBody Orden orden){
        try{
            service.saveOrdens(orden);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_ORDEN + orden.getId());
            return new ResponseEntity(orden,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addOrdens")
    public ResponseEntity<Any> addOrdens(@RequestBody List<Orden> ordens){
        try{
            return new ResponseEntity(service.saveOrdens(ordens), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Orden>> findAllOrdens(){
        try{
            return new ResponseEntity(service.getOrdens(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Orden> findOrdenById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getOrdensById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateOrdens(@RequestBody Orden orden){
        try {
            service.updateOrdens(orden);
            return new ResponseEntity(orden,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteOrden(@PathVariable long id){
        try {
            service.deleteOrdens(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}