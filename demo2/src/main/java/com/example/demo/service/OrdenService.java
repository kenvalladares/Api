package com.example.demo.service;
import com.example.demo.entity.Orden;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenService implements IOrdenService {
    @Autowired
    private OrdenRepository repository;

    public Orden saveOrdens(Orden orden) throws BusinessException{
        try{
            if (String.valueOf(orden.getCliente()).isEmpty()){
                throw new BusinessException("Error: Cliente incorrecto");
            }
            if (orden.getDescripcion().isEmpty()){
                throw new BusinessException("Error:La Descripcion de la orden no debe estar Vacio");
            }
            if (String.valueOf(orden.getEmpleado()).isEmpty()){
                throw new BusinessException("Error: Empleado incorrecto");
            }
            return repository.save(orden);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Orden> saveOrdens(List<Orden> ordens) throws BusinessException{
        try{
            return repository.saveAll(ordens);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Orden> getOrdens() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Orden getOrdensById(long id) throws BusinessException, NotFoundException {
        Optional<Orden> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Orden " + id);
        }
        return opt.get();
    }

    public void deleteOrdens(long id)throws BusinessException, NotFoundException {
        Optional<Orden> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Orden " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Orden updateOrdens(Orden orden)throws BusinessException,NotFoundException{
        Optional<Orden> opt;
        try{
            opt = repository.findById(orden.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Orden " + orden.getId());
        }
        else{
            try {
                Orden existingOrden=new Orden();
                existingOrden.setId(orden.getId());
                existingOrden.setCliente(orden.getCliente());
                existingOrden.setDescripcion(orden.getDescripcion());
                existingOrden.setFecha(orden.getFecha());
                existingOrden.setEmpleado(orden.getEmpleado());
                return repository.save(existingOrden);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
