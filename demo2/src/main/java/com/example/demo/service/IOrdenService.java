package com.example.demo.service;
import com.example.demo.entity.Orden;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IOrdenService {
    Orden saveOrdens(Orden orden)throws BusinessException;
    List<Orden> saveOrdens(List<Orden> ordens) throws BusinessException;
    List<Orden> getOrdens() throws BusinessException;
    Orden getOrdensById(long id) throws BusinessException, NotFoundException;
    void deleteOrdens(long id)throws BusinessException, NotFoundException;
    Orden updateOrdens (Orden orden)throws BusinessException, NotFoundException;
}
