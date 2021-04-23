package com.example.demo.controller;
import com.example.demo.entity.Menu;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.service.MenuService;
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
@RequestMapping("/api/v1/Menu")
public class MenuController {
    @Autowired
    private MenuService service;
    @PostMapping("/addMenu")
    public ResponseEntity<Object> addMenu(@RequestBody Menu menus){
        try{
            service.saveMenus(menus);
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("location", Constants.URL_BASE_MENU + menus.getId());
            return new ResponseEntity(menus,responseHeader,HttpStatus.CREATED);
        }catch (Exception e){
            RestApiError apiError = new RestApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Informacion enviada no es valida.", e.getMessage());
            return new ResponseEntity<>(apiError,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/addMenus")
    public ResponseEntity<Any> addMenus(@RequestBody List<Menu> menus){
        try{
            return new ResponseEntity(service.saveMenus(menus), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Menu>> findAllMenus(){
        try{
            return new ResponseEntity(service.getMenus(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Menu> findMenusById(@PathVariable long id){
        try{
            return new ResponseEntity(service.getMenusById(id), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<Menu> findMenuByName(@PathVariable String name){
        try{
            return new ResponseEntity(service.getMenusByName(name), HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("")
    public ResponseEntity<Any> updateMenus(@RequestBody Menu menus){
        try {
            service.updateMenus(menus);
            return new ResponseEntity(menus,HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Any> deleteMenu(@PathVariable long id){
        try {
            service.deleteMenus(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch (BusinessException e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        catch (NotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}