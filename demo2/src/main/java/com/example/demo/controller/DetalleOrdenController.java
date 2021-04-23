package com.example.demo.controller;
import com.example.demo.entity.DetalleOrden;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.DetalleOrdenService;
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
@RequestMapping("/api/v1/DetalleOrden")
public class DetalleOrdenController {
    @Autowired
    private DetalleOrdenService service;
    @PostMapping("/addDetalleOrden")
    public ResponseEntity<Object> addDetalleOrden(@RequestBody DetalleOrden detalleOrden){
        try{
            service.saveDetalleOrdens(detalleOrden);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_DETALLEORDEN + detalleOrden.getId());
            return new ResponseEntity(detalleOrden,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addDetalleOrdens")
    public ResponseEntity<Any> addDetalleOrdens(@RequestBody List<DetalleOrden> detalleOrdens){
        try{
            return new ResponseEntity(service.saveDetalleOrdens(detalleOrdens), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<DetalleOrden>> findAllDetalleOrdens(){
        try{
            return new ResponseEntity(service.getDetalleOrdens(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<DetalleOrden> findDetalleOrdenById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getDetalleOrdensById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateDetalleOrdens(@RequestBody DetalleOrden detalleOrden){
        try {
            service.updateDetalleOrdens(detalleOrden);
            return new ResponseEntity(detalleOrden,HttpStatus.OK);
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
            service.deleteDetalleOrdens(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}