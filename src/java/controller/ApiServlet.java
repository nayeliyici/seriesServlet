/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SerieModel;
import utils.CustomResponse;

/**
 *
 * @author nayel
 */
@WebServlet(urlPatterns = "/api/data")
public class ApiServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    
    private Connection conn2;

    // Método para obtener la conexión a la base de datos (debes configurarlo según tu base de datos)
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // Configura la conexión a tu base de datos
        String jdbcURL = "jdbc:mysql://localhost:3306/prueba";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Entrando al método doGet del servlet");
        //Configuración del JSON que responderá la petición 
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        List<String> list = new ArrayList<>();

        List<SerieModel> listaSeries = new ArrayList<>();
        String sql = "SELECT id, nombre, temporadas, genero, estado, clasificacion, calificacion FROM series";

        try {
            conn2 = getConnection();
            PreparedStatement ps = conn2.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Itera sobre los resultados y crea objetos UsuarioModel
            while (rs.next()) {
                SerieModel serie = new SerieModel();
                serie.setId(rs.getInt("id"));
                serie.setNombre(rs.getString("nombre"));
                serie.setTemporadas(rs.getInt("temporadas"));
                serie.setGenero(rs.getString("genero"));
                serie.setEstado(rs.getString("estado"));
                serie.setClasificacion(rs.getString("clasificacion"));
                serie.setCalificacion(rs.getInt("calificacion"));
                listaSeries.add(serie);
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        CustomResponse cResponse = new CustomResponse(200, "Ok", listaSeries);

        String jsonResponse = new Gson().toJson(cResponse);
        response.getWriter().write(jsonResponse);
    }
}
