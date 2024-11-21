<%@page import="java.util.ArrayList"%>
<%@page import="model.SerieModel"%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            a {
                text-decoration: none;
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <button type="button" class="btn btn-outline-primary"><a href="./pages/addSerie.jsp">Agregar Serie</a></button>
            <h4 class="mt-4">Listado de series</h4>
            <table class="table mt-5">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Temporadas</th>
                        <th scope="col">Género</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Clasificación</th>
                        <th scope="col">Calificación</th>
                        <th scope="col" colspan="2">Acción</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        ArrayList<SerieModel> listaSeries = (ArrayList<SerieModel>) request.getAttribute("series");

                        if (listaSeries != null && !listaSeries.isEmpty()) {
                            for (SerieModel serie : listaSeries) {
                    %>
                    <tr>
                        <th scope="row"><%= serie.getId()%></th>
                        <td><%= serie.getNombre()%></td>
                        <td><%= serie.getTemporadas()%></td>
                        <td><%= serie.getGenero()%></td>
                        <td><%= serie.getEstado()%></td>
                        <td><%= serie.getClasificacion()%></td>
                        <td><%= serie.getCalificacion()%></td>
                        <td>
                            <form action="${pageContext.request.contextPath}/crear_xml_servlet" method="post">
                                <input type="hidden" name="id" value="<%= serie.getId()%>"> 
                                <input type="hidden" name="accion" value="verDatos">
                                <button type="submit" class="btn btn-outline-primary">Ver Datos</button>
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/crear_xml_servlet" method="post">
                                <input type="hidden" name="id" value="<%= serie.getId()%>"> 
                                <button type="submit" name="accion" value="mostrar" class="btn btn-outline-primary">Xml</button>
                                <button type="submit" name="accion" value="descargar" class="btn btn-outline-primary">Descargar</button>
                            </form> 
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <tr>
                        <td colspan="7">No hay series registrados.</td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
