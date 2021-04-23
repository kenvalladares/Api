package com.example.demo.controller;
import com.example.demo.entity.Factura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.FacturaService;
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
@RequestMapping("/api/v1/Factura")
public class FacturaController {
    @Autowired
    private FacturaService service;
    @PostMapping("/addFactura")
    public ResponseEntity<Object> addFactura(@RequestBody Factura factura){
        try{
            service.saveFacturas(factura);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_FACTURA + factura.getId());
            return new ResponseEntity(factura,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addFacturas")
    public ResponseEntity<Any> addFacturas(@RequestBody List<Factura> facturas){
        try{
            return new ResponseEntity(service.saveFacturas(facturas), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Factura>> findAllFacturas(){
        try{
            return new ResponseEntity(service.getFacturas(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Factura> findFacturaById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getFacturasById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateFacturas(@RequestBody Factura factura){
        try {
            service.updateFacturas(factura);
            return new ResponseEntity(factura,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteFactura(@PathVariable long id){
        try {
            service.deleteFacturas(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}