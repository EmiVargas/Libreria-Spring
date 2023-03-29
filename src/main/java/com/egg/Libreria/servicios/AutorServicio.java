/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.excepciones.MiExcepcion;
import com.egg.Libreria.repositorios.AutorRepositorio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Usuario
 */
@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio autorRepositorio;

    @Transactional
    public void crearAutor(String nombre) throws MiExcepcion {
        validar(nombre);
        Autor autor = new Autor();

        autor.setNombre(nombre);
        autorRepositorio.save(autor);
    }

    public List<Autor> listarAutores() {

        List<Autor> autores = new ArrayList();

        autores = autorRepositorio.findAll();
        return autores;
    }
    
    @Transactional
    public void modificarAutor(String nombre, String id) throws MiExcepcion{
       validar(nombre);
        Optional<Autor> respuesta = autorRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Autor autor = respuesta.get();
            
            autor.setNombre(nombre);
            autorRepositorio.save(autor);
        }
    }
    private void validar(String nombre) throws MiExcepcion{
       
        if(nombre.isEmpty() || nombre == null){
              throw new MiExcepcion("El nombre no puede estar vacío ni ser nulo");
        }
      
    }
    
    public Autor getOne(String id){
        return autorRepositorio.getOne(id);
    }
}
