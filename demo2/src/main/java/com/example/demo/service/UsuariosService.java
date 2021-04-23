package com.example.demo.service;
import com.example.demo.entity.Usuarios;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService implements IUsuariosService {
    @Autowired
    private UsuariosRepository repository;

    public Usuarios saveUsuarios(Usuarios usuarios) throws BusinessException{
        try{
            if (usuarios.getUsuario().isEmpty()){
                throw new BusinessException("Error:El Usuario no debe estar Vacio");
            }
            if (usuarios.getPassword().isEmpty()){
                throw new BusinessException("Error:La contraseña no debe estar Vacio");
            }
            if (usuarios.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre no debe estar Vacio");
            }
            if (usuarios.getCorreo().isEmpty()){
                throw new BusinessException("Error:El Correo no debe estar Vacio");
            }
              return repository.save(usuarios);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Usuarios> saveUsuarios(List<Usuarios> usuarios) throws BusinessException{
        try{
            return repository.saveAll(usuarios);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Usuarios> getUsuarios() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Usuarios getUsuariosById(long id) throws BusinessException, NotFoundException {
        Optional<Usuarios> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario " + id);
        }
        return opt.get();
    }

    public Usuarios getUsuariosByName(String name)throws BusinessException, NotFoundException {
        Optional<Usuarios> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario" + name);
        }
        return opt.get();
    }
    public void deleteUsuarios(long id)throws BusinessException, NotFoundException {
        Optional<Usuarios> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Usuarios updateUsuarios(Usuarios usuarios)throws BusinessException,NotFoundException{
        Optional<Usuarios> opt;
        try{
            opt = repository.findById(usuarios.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Usuario " + usuarios.getId());
        }
        else{
            try {
                Usuarios existingUsuario=new Usuarios();
                existingUsuario.setId(usuarios.getId());
                existingUsuario.setUsuario(usuarios.getUsuario());
                existingUsuario.setPassword(usuarios.getPassword());
                existingUsuario.setName(usuarios.getName());
                existingUsuario.setCorreo(usuarios.getCorreo());
                return repository.save(existingUsuario);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
