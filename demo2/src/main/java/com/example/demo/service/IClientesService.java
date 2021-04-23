package com.example.demo.service;

import com.example.demo.entity.Clientes;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IClientesService {
    Clientes saveCliente(Clientes clientes)throws BusinessException;
    List<Clientes> saveClientes(List<Clientes> clientes) throws BusinessException;
    List<Clientes> getClientes() throws BusinessException;
    Clientes getClientesById(long id) throws BusinessException, NotFoundException;
    Clientes getClientesByName(String name)throws BusinessException, NotFoundException;
    void deleteClientes(long id)throws BusinessException, NotFoundException;
    Clientes updateClientes (Clientes clientes)throws BusinessException, NotFoundException;
}
