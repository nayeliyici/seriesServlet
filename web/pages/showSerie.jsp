<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Detalles de la Serie</h1>
            <table class="table table-bordered">
                <tr>
                    <th>ID</th>
                    <td>${serieBean.id}</td>
                </tr>
                <tr>
                    <th>Nombre</th>
                    <td>${serieBean.nombre}</td>
                </tr>
                <tr>
                    <th>Temporadas</th>
                    <td>${serieBean.temporadas}</td>
                </tr>
                <tr>
                    <th>Género</th>
                    <td>${serieBean.genero}</td>
                </tr>
                <tr>
                    <th>Estado</th>
                    <td>${serieBean.estado}</td>
                </tr>
                <tr>
                    <th>Clasificación</th>
                    <td>${serieBean.clasificacion}</td>
                </tr>
                <tr>
                    <th>Calificación</th>
                    <td>${serieBean.calificacion}</td>
                </tr>
            </table>
            <a href="serie" class="btn btn-primary">Volver</a>
        </div>
    </body>
</html>
