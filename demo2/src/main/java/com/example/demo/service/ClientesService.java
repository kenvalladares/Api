package com.example.demo.service;
import com.example.demo.entity.Clientes;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientesService implements IClientesService {
    @Autowired
    private ClientesRepository repository;

    public Clientes saveCliente(Clientes clientes) throws BusinessException{
        try{
            //VALIDACIONES
            if (clientes.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre del Cliente no debe estar Vacio");
            }
            if (String.valueOf(clientes.getTelefono()).isEmpty()){
                throw new BusinessException("Error: Telefono incorrecto");
            }

            if (clientes.getTelefono()<= 0){
                throw new BusinessException("Error: Telefono incorrecto");
            }

            if (clientes.getDNI()<= 0){
                throw new BusinessException("Error:DNI Incorrecto");
            }
            if (String.valueOf(clientes.getDNI()).isEmpty()){
                throw new BusinessException("Error:DNI Incorrecto");
            }
            if (clientes.getCorreo().isEmpty()){
                throw new BusinessException("Error:Correo no puede estar vacio");
            }
            return repository.save(clientes);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Clientes> saveClientes(List<Clientes> clientes) throws BusinessException{
        try{
            return repository.saveAll(clientes);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Clientes> getClientes() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Clientes getClientesById(long id) throws BusinessException, NotFoundException {
        Optional<Clientes> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Cliente " + id);
        }
        return opt.get();
    }

    public Clientes getClientesByName(String name)throws BusinessException, NotFoundException {
        Optional<Clientes> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el cliente " + name);
        }
        return opt.get();
    }
    public void deleteClientes(long id)throws BusinessException, NotFoundException {
        Optional<Clientes> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el cliente " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Clientes updateClientes(Clientes clientes)throws BusinessException,NotFoundException{
        Optional<Clientes> opt;
        try{
            opt = repository.findById(clientes.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el cliente " + clientes.getId());
        }
        else{
            try {
                Clientes existingCliente=new Clientes();
                existingCliente.setId(clientes.getId());
                existingCliente.setName(clientes.getName());
                existingCliente.setTelefono(clientes.getTelefono());
                existingCliente.setDNI(clientes.getDNI());
                existingCliente.setCorreo(clientes.getCorreo());
                existingCliente.setFechaNacimiento(clientes.getFechaNacimiento());
                return repository.save(existingCliente);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
