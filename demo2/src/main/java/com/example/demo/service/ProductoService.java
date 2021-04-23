package com.example.demo.service;
import com.example.demo.entity.Producto;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ProductoRepository repository;

    public Producto saveProductos(Producto producto) throws BusinessException{
        try{
            if (producto.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre el Producto no debe estar Vacio");
            }
            if (String.valueOf(producto.getProveedor()).isEmpty()){
                throw new BusinessException("Error: Proveedor incorrecto");
            }
            if (producto.getCantidad() < 0)
            {
                throw new BusinessException("Error: Cantidad incorrecto");
            }
            if (producto.getPrecio() <= 0)
            {
                throw new BusinessException("Error: Precio incorrecto");
            }
            return repository.save(producto);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Producto> saveProductos(List<Producto> productos) throws BusinessException{
        try{
            return repository.saveAll(productos);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Producto> getProductos() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Producto getProductosById(long id) throws BusinessException, NotFoundException {
        Optional<Producto> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Producto " + id);
        }
        return opt.get();
    }

    public Producto getProductosByName(String name)throws BusinessException, NotFoundException {
        Optional<Producto> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Producto " + name);
        }
        return opt.get();
    }
    public void deleteProductos(long id)throws BusinessException, NotFoundException {
        Optional<Producto> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Producto " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Producto updateProductos(Producto producto)throws BusinessException,NotFoundException{
        Optional<Producto> opt;
        try{
            opt = repository.findById(producto.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Producto " + producto.getId());
        }
        else{
            try {
                Producto existingProducto=new Producto();
                existingProducto.setId(producto.getId());
                existingProducto.setName(producto.getName());
                existingProducto.setProveedor(producto.getProveedor());
                existingProducto.setCantidad(producto.getCantidad());
                existingProducto.setPrecio(producto.getPrecio());
                return repository.save(existingProducto);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
