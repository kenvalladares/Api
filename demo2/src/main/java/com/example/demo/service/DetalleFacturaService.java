package com.example.demo.service;
import com.example.demo.entity.DetalleFactura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService implements IDetalleFacturaService {
    @Autowired
    private DetalleFacturaRepository repository;

    public DetalleFactura saveDetalleFacturas(DetalleFactura detalleFactura) throws BusinessException{
        try{
            if (String.valueOf(detalleFactura.getProductoid()).isEmpty()){
                throw new Exception("Error: ProductoId No puede estar vacio");
            }
            if (String.valueOf(detalleFactura.getCliente()).isEmpty()){
                throw new Exception("Error: Cliente incorrecto");
            }
            if (detalleFactura.getTotal() <= 0)
            {
                throw new BusinessException("Error: Total incorrecto");
            }
            if (String.valueOf(detalleFactura.getEmpleado()).isEmpty()){
                throw new Exception("Error: Empleado incorrecto");
            }
            return repository.save(detalleFactura);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<DetalleFactura> saveDetalleFacturas(List<DetalleFactura> detalleFacturas) throws BusinessException{
        try{
            return repository.saveAll(detalleFacturas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<DetalleFactura> getDetalleFacturas() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public DetalleFactura getDetalleFacturasById(long id) throws BusinessException, NotFoundException {
        Optional<DetalleFactura> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la factura " + id);
        }
        return opt.get();
    }

    public void deleteDetalleFacturas(long id)throws BusinessException, NotFoundException {
        Optional<DetalleFactura> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la Factura " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public DetalleFactura updateDetalleFacturas(DetalleFactura detalleFactura)throws BusinessException,NotFoundException{
        Optional<DetalleFactura> opt;
        try{
            opt = repository.findById(detalleFactura.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Detalle de la Factura " + detalleFactura.getId());
        }
        else{
            try {
                DetalleFactura existingDetalleFactura=new DetalleFactura();
                existingDetalleFactura.setId(detalleFactura.getId());
                existingDetalleFactura.setProductoid(detalleFactura.getProductoid());
                existingDetalleFactura.setCliente(detalleFactura.getCliente());
                existingDetalleFactura.setTotal(detalleFactura.getTotal());
                existingDetalleFactura.setDescuento(detalleFactura.getDescuento());
                existingDetalleFactura.setEmpleado(detalleFactura.getEmpleado());
                return repository.save(existingDetalleFactura);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
