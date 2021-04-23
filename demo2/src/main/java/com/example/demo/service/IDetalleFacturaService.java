package com.example.demo.service;
import com.example.demo.entity.DetalleFactura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IDetalleFacturaService {
    DetalleFactura saveDetalleFacturas(DetalleFactura detalleFactura)throws BusinessException;
    List<DetalleFactura> saveDetalleFacturas(List<DetalleFactura> detalleFacturas) throws BusinessException;
    List<DetalleFactura> getDetalleFacturas() throws BusinessException;
    DetalleFactura getDetalleFacturasById(long id) throws BusinessException, NotFoundException;
    void deleteDetalleFacturas(long id)throws BusinessException, NotFoundException;
    DetalleFactura updateDetalleFacturas (DetalleFactura detalleFactura)throws BusinessException, NotFoundException;
}
