package com.example.demo.service;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product) throws BusinessException{
        try{
            if (product.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre del product no debe estar Vacio");
            }
            return repository.save(product);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Product> saveProducts(List<Product> products) throws BusinessException{
        try{
            return repository.saveAll(products);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Product> getProducts() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Product getProductById(long id) throws BusinessException, NotFoundException {
        Optional<Product> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + id);
        }
        return opt.get();
    }

    public Product getProductByName(String name)throws BusinessException, NotFoundException {
        Optional<Product> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + name);
        }
        return opt.get();
    }
    public void deleteProduct(long id)throws BusinessException, NotFoundException {
        Optional<Product> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Product updateProduct(Product product)throws BusinessException,NotFoundException{
        Optional<Product> opt;
        try{
            opt = repository.findById(product.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el producto " + product.getId());
        }
        else{
            try {
                Product existingProduct=new Product();
                existingProduct.setId(product.getId());
                existingProduct.setName(product.getName());
                existingProduct.setQuantity(product.getQuantity());
                existingProduct.setPrice(product.getPrice());
                return repository.save(existingProduct);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
