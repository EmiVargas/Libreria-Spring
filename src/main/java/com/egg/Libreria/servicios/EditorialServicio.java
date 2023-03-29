/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.excepciones.MiExcepcion;
import com.egg.Libreria.repositorios.EditorialRepositorio;
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
public class EditorialServicio {

    @Autowired
    EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearEditorial(String nombre) throws MiExcepcion {
         validar(nombre);
        Editorial editorial = new Editorial();

        editorial.setNombre(nombre);

        editorialRepositorio.save(editorial);
    }

    public List<Editorial> listarEditoriales() {

        List<Editorial> editoriales = editorialRepositorio.findAll();
        return editoriales;
    }
    
      public void modificarEditorial(String nombre, String id) throws MiExcepcion{
       validar(nombre);
        Optional<Editorial> respuesta = editorialRepositorio.findById(id);
        
        if(respuesta.isPresent()){
            Editorial editorial = respuesta.get();
            
            editorial.setNombre(nombre);
            editorialRepositorio.save(editorial);
        }
    }
    
      private void validar(String nombre) throws MiExcepcion{
       
        if(nombre.isEmpty() || nombre == null){
              throw new MiExcepcion("El nombre no puede estar vacío");
        }
      
    }
      
       public Editorial getOne(String id){
        return editorialRepositorio.getOne(id);
    }
}
