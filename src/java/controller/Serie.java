/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import configuration.ConnectionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.SerieModel;

/**
 *
 * @author nayel
 */
@WebServlet("/serie")
public class Serie extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

    Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Serie</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Serie at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        System.out.println("Se ejecuta el doGet");
        ConnectionBD conexion = new ConnectionBD();
        List<SerieModel> listaSeries = new ArrayList<>();
        String sql = "SELECT * FROM series";
        
        try {
            conn = conexion.getConnectionBD();
            PreparedStatement ps = conn.prepareStatement(sql);
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
            System.out.println(listaSeries);
            request.setAttribute("series", listaSeries);
            request.getRequestDispatcher("/pages/viewSeries.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener las series" + e);
        } finally {
            // Close resources
            // Close resources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        ConnectionBD conexion = new ConnectionBD();
        
        String nombre = URLDecoder.decode(request.getParameter("txt_name"), "UTF-8");
        String temporadas = URLDecoder.decode(request.getParameter("txt_season"), "UTF-8");
        String genero = URLDecoder.decode(request.getParameter("txt_genre"), "UTF-8");
        String estado = URLDecoder.decode(request.getParameter("txt_status"), "UTF-8");
        String clasificacion = URLDecoder.decode(request.getParameter("txt_classification"), "UTF-8");
        String calificacion = URLDecoder.decode(request.getParameter("txt_qualification"), "UTF-8");

        int temporadasFinal = 0;
        temporadasFinal = Integer.parseInt(temporadas); 
        
        int calificacionFinal = 0;
        calificacionFinal = Integer.parseInt(calificacion); 

        try {
            // Crear la consulta SQL para insertar el usuario 
            String sql = "INSERT INTO series (nombre, temporadas, genero, "
                    + "estado, clasificacion, calificacion) VALUES (?, ?, ?, ?, ?, ?)";
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setInt(2, temporadasFinal);
            ps.setString(3, genero);
            ps.setString(4, estado);
            ps.setString(5, clasificacion);
            ps.setInt(6, calificacionFinal);

            // Ejecutar la consulta 
            int filasInsertadas = ps.executeUpdate();
            if (filasInsertadas > 0) {
                request.setAttribute("mensaje", "Serie registrada con éxito!");
                request.getRequestDispatcher("/pages/resultado.jsp").forward(request, response);
            } else {
                // Si falló, redirigir a una página de error 
                request.setAttribute("mensaje", "Error al registrar animal.");
                request.getRequestDispatcher("/pages/resultado.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("mensaje", "Ocurrió un error: " + e.getMessage());
            request.getRequestDispatcher("/pages/resultado.jsp").forward(request, response);
        } finally {
            // Cerrar los recursos 
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
