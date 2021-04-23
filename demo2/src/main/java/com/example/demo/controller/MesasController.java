package com.example.demo.controller;
import com.example.demo.entity.Mesas;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.MesasService;
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
@RequestMapping("/api/v1/Mesas")
public class MesasController {
    @Autowired
    private MesasService service;
    @PostMapping("/addMesa")
    public ResponseEntity<Object> addMesa(@RequestBody Mesas mesas){
        try{
            service.saveMesas(mesas);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_MESAS + mesas.getId());
            return new ResponseEntity(mesas,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addMesas")
    public ResponseEntity<Any> addMesas(@RequestBody List<Mesas> mesas){
        try{
            return new ResponseEntity(service.saveMesas(mesas), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Mesas>> findAllMesas(){
        try{
            return new ResponseEntity(service.getMesas(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Mesas> findMesaById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getMesasById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("")
    public ResponseEntity<Any> updateMesas(@RequestBody Mesas mesas){
        try {
            service.updateMesas(mesas);
            return new ResponseEntity(mesas,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteMesa(@PathVariable long id){
        try {
            service.deleteMesas(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}