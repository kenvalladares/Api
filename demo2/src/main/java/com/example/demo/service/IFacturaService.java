package com.example.demo.service;
import com.example.demo.entity.Factura;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IFacturaService {
    Factura saveFacturas(Factura facturas)throws BusinessException;
    List<Factura> saveFacturas(List<Factura> facturas) throws BusinessException;
    List<Factura> getFacturas() throws BusinessException;
    Factura getFacturasById(long id) throws BusinessException, NotFoundException;
    void deleteFacturas(long id)throws BusinessException, NotFoundException;
    Factura updateFacturas (Factura facturas)throws BusinessException, NotFoundException;
}