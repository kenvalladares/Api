package com.example.demo.service;
import com.example.demo.entity.Mesas;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IMesasService {
    Mesas saveMesas(Mesas mesas)throws BusinessException;
    List<Mesas> saveMesas(List<Mesas> mesas) throws BusinessException;
    List<Mesas> getMesas() throws BusinessException;
    Mesas getMesasById(long id) throws BusinessException, NotFoundException;
    void deleteMesas(long id)throws BusinessException, NotFoundException;
    Mesas updateMesas (Mesas mesas)throws BusinessException, NotFoundException;
}
