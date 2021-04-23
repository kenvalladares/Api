package com.example.demo.service;
import com.example.demo.entity.Proveedores;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IProveedoresService {
    Proveedores saveProveedores(Proveedores proveedores)throws BusinessException;
    List<Proveedores> saveProveedores(List<Proveedores> proveedores) throws BusinessException;
    List<Proveedores> getProveedores() throws BusinessException;
    Proveedores getProveedoresById(long id) throws BusinessException, NotFoundException;
    Proveedores getProveedoresByName(String name)throws BusinessException, NotFoundException;
    void deleteProveedores(long id)throws BusinessException, NotFoundException;
    Proveedores updateProveedores (Proveedores proveedores)throws BusinessException, NotFoundException;
}
