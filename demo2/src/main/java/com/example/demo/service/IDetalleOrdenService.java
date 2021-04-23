package com.example.demo.service;
import com.example.demo.entity.DetalleOrden;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IDetalleOrdenService {
    DetalleOrden saveDetalleOrdens(DetalleOrden detalleOrden)throws BusinessException;
    List<DetalleOrden> saveDetalleOrdens(List<DetalleOrden> detalleOrdens) throws BusinessException;
    List<DetalleOrden> getDetalleOrdens() throws BusinessException;
    DetalleOrden getDetalleOrdensById(long id) throws BusinessException, NotFoundException;
    void deleteDetalleOrdens(long id)throws BusinessException, NotFoundException;
    DetalleOrden updateDetalleOrdens (DetalleOrden detalleOrden)throws BusinessException, NotFoundException;
}
