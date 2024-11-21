/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author nayel
 */
public class SerieModel {
    private int id;
    private String nombre;
    private int temporadas;
    private String genero;
    private String estado;
    private String clasificacion;
    private int calificacion;

    public SerieModel() {
    }

    public SerieModel(int id, String nombre, int temporadas, String genero, String estado, String clasificacion, int calificacion) {
        this.id = id;
        this.nombre = nombre;
        this.temporadas = temporadas;
        this.genero = genero;
        this.estado = estado;
        this.clasificacion = clasificacion;
        this.calificacion = calificacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    @Override
    public String toString() {
        return "SerieModel{" + "id=" + id + ", nombre=" + nombre + ", temporadas=" + temporadas + ", genero=" + genero + ", estado=" + estado + ", clasificacion=" + clasificacion + ", calificacion=" + calificacion + '}';
    }
}
