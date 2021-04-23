package com.example.demo.service;
import com.example.demo.entity.MetodoPago;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IMetodoPagoService {
    MetodoPago saveMetodoPagos(MetodoPago metodoPago)throws BusinessException;
    List<MetodoPago> saveMetodoPagos(List<MetodoPago> metodoPagos) throws BusinessException;
    List<MetodoPago> getMetodoPagos() throws BusinessException;
    MetodoPago getMetodoPagosById(long id) throws BusinessException, NotFoundException;
    void deleteMetodoPagos(long id)throws BusinessException, NotFoundException;
    MetodoPago updateMetodoPagos (MetodoPago metodoPago)throws BusinessException, NotFoundException;
}
