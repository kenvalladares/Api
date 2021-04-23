package com.example.demo.service;
import com.example.demo.entity.Product;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import java.util.List;

public interface IProductService {
    Product saveProduct(Product product)throws BusinessException;
    List<Product> saveProducts(List<Product> products) throws BusinessException;
    List<Product> getProducts() throws BusinessException;
    Product getProductById(long id) throws BusinessException, NotFoundException;
    Product getProductByName(String name)throws BusinessException, NotFoundException;
    void deleteProduct(long id)throws BusinessException, NotFoundException;
    Product updateProduct (Product product)throws BusinessException, NotFoundException;
}
