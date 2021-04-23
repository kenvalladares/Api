package com.example.demo.service;
import com.example.demo.entity.DetalleOrden;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.DetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService implements IDetalleOrdenService {
    @Autowired
    private DetalleOrdenRepository repository;

    public DetalleOrden saveDetalleOrdens(DetalleOrden detalleOrden) throws BusinessException{
        try{
            if (String.valueOf(detalleOrden.getProductoid()).isEmpty()){
                throw new BusinessException("Error: ProductoId incorrecto");
            }
            if (detalleOrden.getTotal() <= 0)
            {
                throw new BusinessException("Error: Total incorrecto");
            }
            if (String.valueOf(detalleOrden.getNumeroOrden()).isEmpty()){
                throw new BusinessException("Error: Numero de Orden incorrecto");
            }
            return repository.save(detalleOrden);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<DetalleOrden> saveDetalleOrdens(List<DetalleOrden> detalleOrdens) throws BusinessException{
        try{
            return repository.saveAll(detalleOrdens);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<DetalleOrden> getDetalleOrdens() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public DetalleOrden getDetalleOrdensById(long id) throws BusinessException, NotFoundException {
        Optional<DetalleOrden> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la orden " + id);
        }
        return opt.get();
    }

    public void deleteDetalleOrdens(long id)throws BusinessException, NotFoundException {
        Optional<DetalleOrden> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la orden " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public DetalleOrden updateDetalleOrdens(DetalleOrden detalleOrden)throws BusinessException,NotFoundException{
        Optional<DetalleOrden> opt;
        try{
            opt = repository.findById(detalleOrden.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la orden " + detalleOrden.getId());
        }
        else{
            try {
                DetalleOrden existingDetalleOrden=new DetalleOrden();
                existingDetalleOrden.setId(detalleOrden.getId());
                existingDetalleOrden.setProductoid(detalleOrden.getProductoid());
                existingDetalleOrden.setFecha(detalleOrden.getFecha());
                existingDetalleOrden.setTotal(detalleOrden.getTotal());
                existingDetalleOrden.setNumeroOrden(detalleOrden.getNumeroOrden());
                return repository.save(existingDetalleOrden);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
