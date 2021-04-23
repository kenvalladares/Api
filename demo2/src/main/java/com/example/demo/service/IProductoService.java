package com.example.demo.service;
import com.example.demo.entity.Producto;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IProductoService {
    Producto saveProductos(Producto productos)throws BusinessException;
    List<Producto> saveProductos(List<Producto> productos) throws BusinessException;
    List<Producto> getProductos() throws BusinessException;
    Producto getProductosById(long id) throws BusinessException, NotFoundException;
    Producto getProductosByName(String name)throws BusinessException, NotFoundException;
    void deleteProductos(long id)throws BusinessException, NotFoundException;
    Producto updateProductos (Producto producto)throws BusinessException, NotFoundException;
}
