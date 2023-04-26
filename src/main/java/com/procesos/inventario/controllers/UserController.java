package com.procesos.inventario.controllers;

import com.procesos.inventario.models.User;
import com.procesos.inventario.services.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.OK);
        }catch (Exception e){
            response.put("status","404");
            response.put("message","no se encontro el usuario");
            return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping(value = "/users")
    public ResponseEntity saveUser(@RequestBody User user){
        Map response = new HashMap();
             Boolean userResp = userServiceImp.createUser(user);

             if (userResp == true) {
                 response.put("status", "201");
                 response.put("message", "Se creo el uruuario");
                 return new ResponseEntity<>(response, HttpStatus.CREATED);
             }
             response.put("status","400");
             response.put("message","Hubo un error al crear el usuario");
             return new ResponseEntity(response, HttpStatus.BAD_REQUEST);


    }

    @GetMapping(value = "/users")
    public ResponseEntity allUsers(){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.allUsers(), HttpStatus.OK);
        }catch (Exception e) {
            response.put("status", "404");
            response.put("message", "no se encontro los usuarios");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        Map response = new HashMap();
        Boolean userDB = userServiceImp.updateUser(id, user);
        try {
            if (userDB == null) {
                response.put("status", "201");
                response.put("message", "se creo encontro usuarios");
                return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity(userServiceImp.getUser(id), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            response.put("status", "201");
            response.put("message", "se encontro usuario");
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody User user){
        Map response = new HashMap();
        try {
            return new ResponseEntity(userServiceImp.login(user),HttpStatus.OK);
        }catch (Exception e){
            response.put("status", "404");
            response.put("message", e.getMessage());
            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}
