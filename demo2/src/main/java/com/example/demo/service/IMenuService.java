package com.example.demo.service;
import com.example.demo.entity.Menu;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;

import java.util.List;

public interface IMenuService {
    Menu saveMenus(Menu menu)throws BusinessException;
    List<Menu> saveMenus(List<Menu> menus) throws BusinessException;
    List<Menu> getMenus() throws BusinessException;
    Menu getMenusById(long id) throws BusinessException, NotFoundException;
    Menu getMenusByName(String name)throws BusinessException, NotFoundException;
    void deleteMenus(long id)throws BusinessException, NotFoundException;
    Menu updateMenus (Menu menu)throws BusinessException, NotFoundException;
}
