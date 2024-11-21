<%-- 
    Document   : resultado
    Created on : 21/09/2024, 08:30:03 PM
    Author     : nayel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html> 
<html> 
    <head> 
        <meta charset="UTF-8"> 
        <title>Resultado del Registro</title> 
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    </head> 
    <body class="container mt-4"> 
        <h3><%= request.getAttribute("mensaje")%></h3> 
        <button type="button" class="btn btn-warning mt-3 ">
            <a href="pages/addSerie.jsp" style="text-decoration: none; color:#000">Regresar al formulario</a> 
        </button>
        <button type="button" class="btn btn-warning mt-3 ">
            <a href="./serie" style="text-decoration: none; color:#000">Listado de series</a> 
        </button>
    </body> 
</html>