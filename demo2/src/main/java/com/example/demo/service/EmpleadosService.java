package com.example.demo.service;
import com.example.demo.entity.Empleados;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.EmpleadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadosService implements IEmpleadosService {
    @Autowired
    private EmpleadosRepository repository;

    public Empleados saveEmpleados(Empleados empleados) throws BusinessException{
        try{
            if (empleados.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre del Empleado no debe estar Vacio");
            }
            if (empleados.getPuesto().isEmpty()){
                throw new BusinessException("Error:El Puesto del Empleado no debe estar Vacio");
            }
            if (empleados.getSueldo()<= 0){
                throw new BusinessException("Error: Sueldo incorrecto");
            }
            if (empleados.getDNI()<= 0){
                throw new BusinessException("Error: DNI incorrecto");
            }
            if (empleados.getTelefono()<= 0){
                throw new BusinessException("Error: Telefono incorrecto");
            }
            return repository.save(empleados);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Empleados> saveEmpleados(List<Empleados> empleados) throws BusinessException{
        try{
            return repository.saveAll(empleados);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Empleados> getEmpleados() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Empleados getEmpleadosbyId(long id) throws BusinessException, NotFoundException {
        Optional<Empleados> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el empleado " + id);
        }
        return opt.get();
    }

    public Empleados getEmpleadosbyName(String name)throws BusinessException, NotFoundException {
        Optional<Empleados> opt = null;
        try {
            opt = repository.findByName(name);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el empleado " + name);
        }
        return opt.get();
    }
    public void deleteEmpleados(long id)throws BusinessException, NotFoundException {
        Optional<Empleados> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el empleado " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Empleados updateEmpleados(Empleados empleados)throws BusinessException,NotFoundException{
        Optional<Empleados> opt;
        try{
            opt = repository.findById(empleados.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Empleado " + empleados.getId());
        }
        else{
            try {
                Empleados existingEmpleados=new Empleados();
                existingEmpleados.setId(empleados.getId());
                existingEmpleados.setName(empleados.getName());
                existingEmpleados.setPuesto(empleados.getPuesto());
                existingEmpleados.setSueldo(empleados.getSueldo());
                existingEmpleados.setDNI(empleados.getDNI());
                existingEmpleados.setTelefono(empleados.getTelefono());
                return repository.save(existingEmpleados);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}