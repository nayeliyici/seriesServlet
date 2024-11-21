/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import beans.SerieBean;
import configuration.ConnectionBD;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.SerieModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author RUFINA RUIZ
 */
@WebServlet(name = "CrearXMLServlet", urlPatterns = {"/crear_xml_servlet"})
public class CrearXMLServlet extends HttpServlet {

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
            out.println("<title>Servlet CrearXMLServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CrearXMLServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        String accion = request.getParameter("accion");

        if ("verDatos".equalsIgnoreCase(accion)) {
            // Lógica para mostrar datos
            String serieId = request.getParameter("id");

            // Obtener conexión y datos desde la base de datos
            ConnectionBD conexion = new ConnectionBD();
            SerieBean serieBean = null;
            String sql = "SELECT * FROM series WHERE id = ?";

            try {
                Connection conn = conexion.getConnectionBD();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(serieId));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    serieBean = new SerieBean();
                    serieBean.setId(rs.getInt("id"));
                    serieBean.setNombre(rs.getString("nombre"));
                    serieBean.setTemporadas(rs.getInt("temporadas"));
                    serieBean.setGenero(rs.getString("genero"));
                    serieBean.setEstado(rs.getString("estado"));
                    serieBean.setClasificacion(rs.getString("clasificacion"));
                    serieBean.setCalificacion(rs.getInt("calificacion"));
                }

                if (serieBean == null) {
                    response.setContentType("text/plain");
                    response.getWriter().println("No se encontró la serie con el ID: " + serieId);
                    return;
                }

                request.setAttribute("serieBean", serieBean);

                RequestDispatcher dispatcher = request.getRequestDispatcher("pages/showSerie.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
            }
        } else {
            request.setCharacterEncoding("UTF-8");

            String serieId = request.getParameter("id");

            ConnectionBD conexion = new ConnectionBD();
            SerieModel serie = null;
            String sql = "SELECT * FROM series WHERE id = ?";

            try {
                Connection conn = conexion.getConnectionBD();
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(serieId));

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    serie = new SerieModel();
                    serie.setId(rs.getInt("id"));
                    serie.setNombre(rs.getString("nombre"));
                    serie.setTemporadas(rs.getInt("temporadas"));
                    serie.setGenero(rs.getString("genero"));
                    serie.setEstado(rs.getString("estado"));
                    serie.setClasificacion(rs.getString("clasificacion"));
                    serie.setCalificacion(rs.getInt("calificacion"));
                }

                if (serie == null) {
                    response.setContentType("text/plain");
                    response.getWriter().println("No se encontró la serie con el ID: " + serieId);
                    return;
                }

                // Crear el documento XML en memoria
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.newDocument();

                Element root = document.createElement("Serie");
                document.appendChild(root);

                Element nombreElement = document.createElement("Nombre");
                nombreElement.appendChild(document.createTextNode(serie.getNombre()));
                root.appendChild(nombreElement);

                Element generoElement = document.createElement("Genero");
                generoElement.appendChild(document.createTextNode(serie.getGenero()));
                root.appendChild(generoElement);

                Element temporadasElement = document.createElement("Temporadas");
                temporadasElement.appendChild(document.createTextNode(String.valueOf(serie.getTemporadas())));
                root.appendChild(temporadasElement);

                Element estadoElement = document.createElement("Estado");
                estadoElement.appendChild(document.createTextNode(serie.getEstado()));
                root.appendChild(estadoElement);

                if ("descargar".equals(accion)) {
                    // Descargar el archivo XML
                    response.setContentType("application/xml");
                    response.setHeader("Content-Disposition", "attachment; filename=serie_" + serie.getId() + ".xml");

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(response.getOutputStream());
                    transformer.transform(source, result);
                } else {
                    response.setContentType("application/xml;charset=UTF-8");
                    PrintWriter out = response.getWriter();

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(document);
                    StreamResult result = new StreamResult(out);
                    transformer.transform(source, result);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al procesar la solicitud.");
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
