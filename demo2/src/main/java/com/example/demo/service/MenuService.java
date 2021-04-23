package com.example.demo.service;
import com.example.demo.entity.Menu;
import com.example.demo.exceptions.BusinessException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MenuService implements IMenuService {
    @Autowired
    private MenuRepository repository;

    public Menu saveMenus(Menu menu) throws BusinessException{
        try{
            if (menu.getName().isEmpty()){
                throw new BusinessException("Error:El Nombre del Menu no debe estar Vacio");
            }
            if (menu.getPrecio() <= 0)
            {
                throw new BusinessException("Error: Precio incorrecto");
            }
            if (menu.getDescripcion().isEmpty()){
                throw new BusinessException("Error:La Descripcion del menu no debe estar Vacio");
            }
            return repository.save(menu);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Menu> saveMenus(List<Menu> menus) throws BusinessException{
        try{
            return repository.saveAll(menus);
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }
    }
    public List<Menu> getMenus() throws BusinessException {
        try{
            return repository.findAll();
        }catch (Exception e){
            throw new BusinessException(e.getMessage());
        }

    }
    public Menu getMenusById(long id) throws BusinessException, NotFoundException {
        Optional<Menu> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }

        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Menu " + id);
        }
        return opt.get();
    }

    public Menu getMenusByName(String name)throws BusinessException, NotFoundException {
        Optional<Menu> opt = null;
        try{
            opt = repository.findByName(name);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Menu " + name);
        }
        return opt.get();
    }
    public void deleteMenus(long id)throws BusinessException, NotFoundException {
        Optional<Menu> opt = null;
        try{
            opt = repository.findById(id);
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Menu " + id);
        }
        else{
            try {
                repository.deleteById(id);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
    public Menu updateMenus(Menu menus)throws BusinessException,NotFoundException{
        Optional<Menu> opt;
        try{
            opt = repository.findById(menus.getId());
        }catch(Exception e){
            throw new BusinessException(e.getMessage());
        }
        if (!opt.isPresent()) {
            throw new NotFoundException("No se encontró el Menu " + menus.getId());
        }
        else{
            try {
               Menu existingMenu=new Menu();
                existingMenu.setId(menus.getId());
                existingMenu.setName(menus.getName());
                existingMenu.setPrecio(menus.getPrecio());
                existingMenu.setDescripcion(menus.getDescripcion());
                existingMenu.setDescuento(menus.getDescuento());
                return repository.save(existingMenu);
            }catch (Exception e1){
                throw new BusinessException(e1.getMessage());
            }
        }
    }
}
