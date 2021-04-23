package com.example.demo.service;
import com.example.demo.entity.Usuarios;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IUsuariosService {
    Usuarios saveUsuarios(Usuarios usuarios)throws BusinessException;
    List<Usuarios> saveUsuarios(List<Usuarios> usuarios) throws BusinessException;
    List<Usuarios> getUsuarios() throws BusinessException;
    Usuarios getUsuariosById(long id) throws BusinessException, NotFoundException;
    Usuarios getUsuariosByName(String name)throws BusinessException, NotFoundException;
    void deleteUsuarios(long id)throws BusinessException, NotFoundException;
    Usuarios updateUsuarios (Usuarios usuarios)throws BusinessException, NotFoundException;
}
