package com.example.demo.service;
import com.example.demo.entity.Proveedores;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProveedoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProveedoresService implements IProveedoresService {
    @Autowired
    private ProveedoresRepository repository;

    public Proveedores saveProveedores(Proveedores proveedores) throws BusinessException{
        try{
            if (proveedores.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre del Proveedor no debe estar Vacio");
            }
            if (proveedores.getProducto().isEmpty()){
                throw new BusinessException("Error:El Nombre del Producto no debe estar Vacio");
            }
            if (proveedores.getPrecio() <= 0)
            {
                throw new BusinessException("Error: Precio incorrecto");
            }
            if (String.valueOf(proveedores.getFechaExp()).isEmpty()){
                throw new BusinessException("Error: FechaExp No puede estar vacia");
            }
            if (String.valueOf(proveedores.getFechaElaboracion()).isEmpty()){
                throw new BusinessException("Error: FechaElaboracion No puede estar vacia");
            }
            return repository.save(proveedores);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Proveedores> saveProveedores(List<Proveedores> proveedores) throws BusinessException{
        try{
            return repository.saveAll(proveedores);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Proveedores> getProveedores() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Proveedores getProveedoresById(long id) throws BusinessException, NotFoundException {
        Optional<Proveedores> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Proveedor " + id);
        }
        return opt.get();
    }

    public Proveedores getProveedoresByName(String name)throws BusinessException, NotFoundException {
        Optional<Proveedores> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Proveedor " + name);
        }
        return opt.get();
    }
    public void deleteProveedores(long id)throws BusinessException, NotFoundException {
        Optional<Proveedores> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Proveedor " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Proveedores updateProveedores(Proveedores proveedores)throws BusinessException,NotFoundException{
        Optional<Proveedores> opt;
        try{
            opt = repository.findById(proveedores.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Proveedor " + proveedores.getId());
        }
        else{
            try {
                Proveedores existingProdveedores=new Proveedores();
                existingProdveedores.setId(proveedores.getId());
                existingProdveedores.setName(proveedores.getName());
                existingProdveedores.setProducto(proveedores.getProducto());
                existingProdveedores.setFechaExp(proveedores.getFechaExp());
                existingProdveedores.setFechaElaboracion(proveedores.getFechaElaboracion());
                existingProdveedores.setPrecio(proveedores.getPrecio());
                return repository.save(existingProdveedores);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
