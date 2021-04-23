package com.example.demo.service;
import com.example.demo.entity.Factura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public  class FacturaService implements IFacturaService {
    @Autowired
    private FacturaRepository repository;

    public Factura saveFacturas(Factura factura) throws BusinessException{
        try{
            if (factura.getCliente()<= 0){
                throw new BusinessException("Error:Cliente Incorrecto");
            }
            if (String.valueOf(factura.getCliente()).isEmpty()){
                throw new BusinessException("Error:Cliente Incorrecto");
            }
            if (factura.getEmpleado()<= 0){
                throw new BusinessException("Error:Empleado Incorrecto");
            }
            if (factura.getCAI()<= 0){
                throw new BusinessException("Error:CAI Incorrecto");
            }
            if (factura.getTotal() <= 0)
            {
                throw new BusinessException("Error: Total incorrecto");
            }
            if (String.valueOf(factura.getFecha()).isEmpty()){
                throw new BusinessException("Error:Fecha Incorrecto");
            }
            return repository.save(factura);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Factura> saveFacturas(List<Factura> facturas) throws BusinessException{
        try{
            return repository.saveAll(facturas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Factura> getFacturas() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Factura getFacturasById(long id) throws BusinessException, NotFoundException {
        Optional<Factura> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró  la factura " + id);
        }
        return opt.get();
    }

    public void deleteFacturas(long id)throws BusinessException, NotFoundException {
        Optional<Factura> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró  la Factura " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Factura updateFacturas(Factura factura)throws BusinessException,NotFoundException{
        Optional<Factura> opt;
        try{
            opt = repository.findById(factura.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró  la Factura " + factura.getId());
        }
        else{
            try {
                Factura existingFactura=new Factura();
                existingFactura.setId(factura.getId());
                existingFactura.setCliente(factura.getCliente());
                existingFactura.setEmpleado(factura.getEmpleado());
                existingFactura.setCAI(factura.getCAI());
                existingFactura.setTotal(factura.getTotal());
                existingFactura.setFecha(factura.getFecha());
                return repository.save(existingFactura);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}