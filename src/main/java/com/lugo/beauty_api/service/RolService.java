package com.lugo.beauty_api.service;

import com.lugo.beauty_api.model.Rol;
import com.lugo.beauty_api.repository.RolRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepositoy rolRepositoy;

    public Rol saveRol(Rol rol){
        return rolRepositoy.save(rol);
    }

    public List<Rol> getRols(){
        return  (List<Rol>) rolRepositoy.findAll();
    }



}
