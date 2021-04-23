package com.example.demo.service;
import com.example.demo.entity.Mesas;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.MesasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesasService implements IMesasService {
    @Autowired
    private MesasRepository repository;

    public Mesas saveMesas(Mesas mesas) throws BusinessException{
        try{
            if (String.valueOf(mesas.getAsientos()).isEmpty()){
                throw new BusinessException("Error: Asientos incorrectos");
            }
            if (mesas.getEstado().isEmpty()){
                throw new BusinessException("Error:El estado de la mesa no debe estar Vacio");
            }
            if (String.valueOf(mesas.getNumero()).isEmpty()){
                throw new BusinessException("Error: Numero de Mesa incorrecto");
            }

            if (String.valueOf(mesas.getPiso()).isEmpty()){
                throw new BusinessException("Error: Piso incorrecto");
            }
            return repository.save(mesas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Mesas> saveMesas(List<Mesas> mesas) throws BusinessException{
        try{
            return repository.saveAll(mesas);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Mesas> getMesas() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Mesas getMesasById(long id) throws BusinessException, NotFoundException {
        Optional<Mesas> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Mesa " + id);
        }
        return opt.get();
    }

    public void deleteMesas(long id)throws BusinessException, NotFoundException {
        Optional<Mesas> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Mesa " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Mesas updateMesas(Mesas mesas)throws BusinessException,NotFoundException{
        Optional<Mesas> opt;
        try{
            opt = repository.findById(mesas.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró la Mesa " + mesas.getId());
        }
        else{
            try {
                Mesas existingMesa=new Mesas();
                existingMesa.setId(mesas.getId());
                existingMesa.setAsientos(mesas.getAsientos());
                existingMesa.setEstado(mesas.getEstado());
                existingMesa.setNumero(mesas.getNumero());
                existingMesa.setPiso(mesas.getPiso());
                return repository.save(existingMesa);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
