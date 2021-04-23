package com.example.demo.controller;
import com.example.demo.entity.DetalleFactura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.DetalleFacturaService;
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
@RequestMapping("/api/v1/DetalleFactura")
public class DetalleFacturaController {
    @Autowired
    private DetalleFacturaService service;
    @PostMapping("/addDetalleFactura")
    public ResponseEntity<Object> addDetalleFactura(@RequestBody DetalleFactura detalleFactura){
        try{
            service.saveDetalleFacturas(detalleFactura);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_DETALLEFACTURA + detalleFactura.getId());
            return new ResponseEntity(detalleFactura,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addDetalleFacturas")
    public ResponseEntity<Any> addDetalleFacturas(@RequestBody List<DetalleFactura> detalleFacturas){
        try{
            return new ResponseEntity(service.saveDetalleFacturas(detalleFacturas), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<DetalleFactura>> findAllDetalleFacturas(){
        try{
            return new ResponseEntity(service.getDetalleFacturas(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<DetalleFactura> findDetalleFacturaById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getDetalleFacturasById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateDetalleFacturas(@RequestBody DetalleFactura detalleFactura){
        try {
            service.updateDetalleFacturas(detalleFactura);
            return new ResponseEntity(detalleFactura,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteDetalleFactura(@PathVariable long id){
        try {
            service.deleteDetalleFacturas(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}