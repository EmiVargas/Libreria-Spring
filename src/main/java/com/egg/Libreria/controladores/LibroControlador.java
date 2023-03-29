/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.Libreria.controladores;

import com.egg.Libreria.entidades.Autor;
import com.egg.Libreria.entidades.Editorial;
import com.egg.Libreria.entidades.Libro;
import com.egg.Libreria.excepciones.MiExcepcion;
import com.egg.Libreria.servicios.AutorServicio;
import com.egg.Libreria.servicios.EditorialServicio;
import com.egg.Libreria.servicios.LibroServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/libro")
public class LibroControlador {

    @Autowired
    private LibroServicio libroServicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/registrar") //localhost:8080/libro/registrar
    public String registrar(ModelMap modelo) {
       
       inyectarListas(modelo);
        return "libro_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(required=false) Long isbn,
            @RequestParam String titulo, @RequestParam(required=false) Integer ejemplares,
            @RequestParam String idAutor, @RequestParam String idEditorial, ModelMap modelo) {
try{
    libroServicio.crearLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
    modelo.put("exito", "El libro fue cargado correctamente!");
} catch(MiExcepcion ex){
    inyectarListas(modelo);
   modelo.put("error", ex.getMessage());
    return "libro_form.html";
}
return "index.html";
    }
        
    @GetMapping("/lista")
    public String listar(ModelMap modelo){
        List<Libro> libros = libroServicio.listarLibros();
        modelo.addAttribute("libros", libros);
        return "libro_list.html";
    }
          
    private void inyectarListas(ModelMap modelo){
        List<Autor> autores = autorServicio.listarAutores();
       List<Editorial> editoriales = editorialServicio.listarEditoriales();
       
       modelo.addAttribute("autores", autores);
       modelo.addAttribute("editoriales", editoriales);
    }

 @GetMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, ModelMap modelo) {
      
        modelo.put("libro", libroServicio.getOne(isbn));
        
      inyectarListas(modelo);
        
        return "libro_modificar.html";
    }

    @PostMapping("/modificar/{isbn}")
    public String modificar(@PathVariable Long isbn, String titulo, Integer ejemplares, String idAutor, String idEditorial, ModelMap modelo) {
        try {
           inyectarListas(modelo);

            libroServicio.modificarLibro(isbn, titulo, ejemplares, idAutor, idEditorial);
            
                        
            return "redirect:../lista";

        } catch (MiExcepcion ex) {
           inyectarListas(modelo);
            modelo.put("error", ex.getMessage());
            
         
            
            return "libro_modificar.html";
        }
    }
    
}
