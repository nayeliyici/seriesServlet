<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
        <style>
            .rating {
                direction: rtl; 
                unicode-bidi: bidi-override;
                display: inline-flex;
            }

            .rating input {
                display: none; 
            }

            .rating label {
                font-size: 2em;
                color: #ccc;
                cursor: pointer;
            }

            .rating input:checked ~ label {
                color: #ffc700;
            }

            .rating label:hover,
            .rating label:hover ~ label {
                color: #ffc700; 
            }
        </style>
    </head>
    <body>
        <div class="container mt-4">
            <h3>Agregar Serie</h3>
            <form action="${pageContext.request.contextPath}/serie" method="POST" style="width: 600px; margin-top: 25px;">
                <div class="form-floating mb-3">
                    <input type="text" name="txt_name" class="form-control" id="floatingName" placeholder="">
                    <label for="floatingName">Nombre</label>
                </div>
                <div class="form-floating">
                    <input type="number" name="txt_season" class="form-control" id="floatingSeason" placeholder="">
                    <label for="floatingSeason">Temporadas</label>
                </div>
                <select name="txt_genre" class="form-select mt-3" style="height: 55px;">
                    <option selected>Seleccione un género</option>
                    <option value="Acción">Acción</option>
                    <option value="Aventura">Aventura</option>
                    <option value="Ciencia Ficción">Ciencia Ficción</option>
                    <option value="Comedia">Comedia</option>
                    <option value="Drama">Drama</option>
                    <option value="Fantasía">Fantasía</option>
                    <option value="Misterio">Misterio</option>
                    <option value="Terror">Terror</option>
                    <option value="Romance">Romance</option>
                </select>
                <select name="txt_status" class="form-select mt-3" style="height: 55px;">
                    <option selected>Seleccione un estado</option>
                    <option value="En Emisión">En Emisión</option>
                    <option value="Finalizada">Finalizada</option>
                    <option value="Cancelada">Cancelada</option>
                    <option value="En Pausa">En Pausa</option>
                </select>
                <select name="txt_classification" class="form-select mt-3" style="height: 55px;">
                    <option selected>Seleccione una clasificación</option>
                    <option value="AA">AA (Todo público, menores de 7 años)</option>
                    <option value="A">A (Todo público)</option>
                    <option value="B">B (Mayores de 12 años)</option>
                    <option value="B15">B15 (Mayores de 15 años)</option>
                    <option value="C">C (Mayores de 18 años)</option>
                    <option value="D">D (Solo adultos)</option>
                </select>
                <div class="form-control mt-3">
                    <label>Calificación</label>
                    <div class="rating ms-5">  
                        <input type="radio" id="star5" name="txt_qualification" value="5" required>
                        <label for="star5" title="5 estrellas">★</label>
                        <input type="radio" id="star4" name="txt_qualification" value="4">
                        <label for="star4" title="4 estrellas">★</label>
                        <input type="radio" id="star3" name="txt_qualification" value="3">
                        <label for="star3" title="3 estrellas">★</label>
                        <input type="radio" id="star2" name="txt_qualification" value="2">
                        <label for="star2" title="2 estrellas">★</label>
                        <input type="radio" id="star1" name="txt_qualification" value="1">
                        <label for="star1" title="1 estrella">★</label>
                    </div>
                </div>
                <button type="submit" name="accion" class="btn btn-outline-success" style="margin-top: 70px; margin-left: 250px;">Agregar</button>
            </form>
        </div>
    </body>
</html>
