/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.servicios;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.entidades.Libro;
import com.egg.Libreria.excepciones.MiExcepcion;
import com.egg.Libreria.repositorios.AutorRepositorio;
import com.egg.Libreria.repositorios.EditorialRepositorio;
import com.egg.Libreria.repositorios.LibroRepositorio;
import java.util.ArrayList;
import java.util.Date;
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
public class LibroServicio {

    @Autowired
    private LibroRepositorio libroRepositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    @Transactional
    public void crearLibro(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {
        
        validar(isbn,titulo,ejemplares,idAutor,idEditorial);
        
        Libro libro = new Libro();
        Autor autor = autorRepositorio.findById(idAutor).get();
        Editorial editorial = editorialRepositorio.findById(idEditorial).get();

        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setEjemplares(ejemplares);
        libro.setAlta(new Date());
        libro.setAutor(autor);
        libro.setEditorial(editorial);

        libroRepositorio.save(libro);
    }

    public List<Libro> listarLibros() {
        List<Libro> libros = new ArrayList();

        libros = libroRepositorio.findAll();

        return libros;
    }

    public void modificarLibro(Long isbn, String titulo,Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion {
 
        validar(isbn,titulo,ejemplares,idAutor,idEditorial);
        
        Optional<Libro> respuesta = libroRepositorio.findById(isbn);
        Optional<Autor> respAutor = autorRepositorio.findById(idAutor);
        Optional<Editorial> respEditorial = editorialRepositorio.findById(idEditorial);

        Autor autor = new Autor();
        Editorial editorial = new Editorial();

        if (respAutor.isPresent()) {
            autor = respAutor.get();
        }
        
        if(respEditorial.isPresent()){
            editorial = respEditorial.get();
        }
        
        if (respuesta.isPresent()) {
            Libro libro = respuesta.get();

            libro.setTitulo(titulo);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            libro.setEjemplares(ejemplares);
            libroRepositorio.save(libro);
        }


    }
    
    private void validar(Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial) throws MiExcepcion{
          if(isbn == null){
            throw new MiExcepcion("Debe ingresar el isbn");
        }
        if(titulo.isEmpty() || titulo == null){
              throw new MiExcepcion("El título no puede estar vacío ni ser nulo");
        }
          if( ejemplares == null){
              throw new MiExcepcion("Los ejemplares no pueden estar vacíos");
        }
           if(idAutor.isEmpty() || idAutor == null){
              throw new MiExcepcion("El id del autor no puede estar vacío ni ser nulo");
        }
             if(idEditorial.isEmpty() || idEditorial == null){
              throw new MiExcepcion("El id de la editorial no puede estar vacío ni ser nulo");
        }
    }
    
     public Libro getOne(Long id){
        return libroRepositorio.getOne(id);
    }
    
    
}
