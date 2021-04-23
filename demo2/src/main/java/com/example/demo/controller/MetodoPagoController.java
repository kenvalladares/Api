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
@RequestMapping("/api/v1/MetodoPago")
public class MetodoPagoController {
    @Autowired
    private MetodoPagoService service;
    @PostMapping("/addMetodoPago")
    public ResponseEntity<Object> addMetodoPago(@RequestBody MetodoPago metodoPago){
        try{
            service.saveMetodoPagos(metodoPago);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_METODOPAGO + metodoPago.getId());
            return new ResponseEntity(metodoPago,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addMetodoPagos")
    public ResponseEntity<Any> addMetodoPagos(@RequestBody List<MetodoPago> metodoPagos){
        try{
            return new ResponseEntity(service.saveMetodoPagos(metodoPagos), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<MetodoPago>> findAllMetodoPagos(){
        try{
            return new ResponseEntity(service.getMetodoPagos(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<MetodoPago> findMetodoPagoById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getMetodoPagosById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateMetodoPagos(@RequestBody MetodoPago metodoPago){
        try {
            service.updateMetodoPagos(metodoPago);
            return new ResponseEntity(metodoPago,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteMetodoPago(@PathVariable long id){
        try {
            service.deleteMetodoPagos(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}