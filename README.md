# BIBLIOTECA

Este proyecto es una aplicación de biblioteca desarrollada utilizando Java y Spring MVC. Proporciona una interfaz web para que los usuarios puedan buscar libros, autores y editoriales de la biblioteca.

CARACTERÍSTICAS
  -Registro de usuarios: Los usuarios pueden registrarse en la aplicación para acceder a funcionalidades adicionales.
  
  -Búsqueda de libros: Los usuarios pueden buscar libros por título, autor u otra información relevante.
  
  -Gestión de libros: Los administradores pueden agregar, editar y eliminar libros de la biblioteca.
  
  -Gestión de autores:Los administradores pueden agregar, editar y eliminar libros de la biblioteca.
  
  -Gestión de editoriales: Los administradores pueden agregar, editar y eliminar libros de la biblioteca.

TECNOLOGÍAS UTILIZADAS

  Java 11: Lenguaje de programación utilizado en el backend.
  
  Spring MVC: Framework de desarrollo web utilizado para crear la aplicación.
  
  Maven: Herramienta de gestión de dependencias utilizada para manejar las dependencias del proyecto.
  
  Thymeleaf: Motor de plantillas utilizado para generar las vistas HTML.
  
  MySQL: Base de datos relacional utilizada para almacenar la información de la aplicación.

CONFIGURACIÓN DEL PROYECTO

  -Clona este repositorio en tu máquina local.
  
  -Abre el archivo application.properties en la carpeta src/main/resources.
  
  -Configura las propiedades de conexión a la base de datos con tus propias credenciales de MySQL:
  
  
        spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
        spring.datasource.username=your-username
        spring.datasource.password=your-password

  -Ejecuta el siguiente comando para compilar el proyecto y resolver las dependencias:
        
        mvn compile

  -Ejecuta el siguiente comando para iniciar la aplicación:
       
        mvn spring-boot:run
      
  -La aplicación estará disponible en http://localhost:8080. Abre esta URL en tu navegador para acceder a la aplicación de biblioteca.
