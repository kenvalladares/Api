package com.example.demo.service;
import com.example.demo.entity.MetodoPago;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MetodoPagoService implements IMetodoPagoService {
    @Autowired
    private MetodoPagoRepository repository;

    public MetodoPago saveMetodoPagos(MetodoPago metodoPago) throws BusinessException{
        try{

            if (metodoPago.getName().isEmpty()){
                throw new BusinessException("Error:El nombre del metodo de pago no debe estar Vacio");
            }
            if (metodoPago.getEstado().isEmpty()){
                throw new BusinessException("Error:El estado del metodo de pago no debe estar Vacio");
            }
            return repository.save(metodoPago);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<MetodoPago> saveMetodoPagos(List<MetodoPago> metodoPago) throws BusinessException{
        try{
            return repository.saveAll(metodoPago);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<MetodoPago> getMetodoPagos() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public MetodoPago getMetodoPagosById(long id) throws BusinessException, NotFoundException {
        Optional<MetodoPago> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Metodo de Pago " + id);
        }
        return opt.get();
    }

    public void deleteMetodoPagos(long id)throws BusinessException, NotFoundException {
        Optional<MetodoPago> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Metodo de Pago " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public MetodoPago updateMetodoPagos(MetodoPago metodoPago)throws BusinessException,NotFoundException{
        Optional<MetodoPago> opt;
        try{
            opt = repository.findById(metodoPago.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Metodo de Pago" + metodoPago.getId());
        }
        else{
            try {
                MetodoPago existingMetodoPago=new MetodoPago();
                existingMetodoPago.setId(metodoPago.getId());
                existingMetodoPago.setName(metodoPago.getName());
                existingMetodoPago.setEstado(metodoPago.getEstado());
                return repository.save(existingMetodoPago);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
