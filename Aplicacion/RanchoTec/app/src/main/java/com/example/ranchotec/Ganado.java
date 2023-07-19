package com.example.ranchotec;
public class Ganado {
    private String id;
    private String especie;
    private String raza;
    private String nombre;
    private String fechaNacimiento;
    private String sexo;
    private String color;
    private String historialVacunacion;

    public Ganado() {
    }

    public Ganado(String id, String especie, String raza, String nombre, String fechaNacimiento,
                  String sexo, String color, String historialVacunacion) {
        this.id = id;
        this.especie = especie;
        this.raza = raza;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.color = color;
        this.historialVacunacion = historialVacunacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getHistorialVacunacion() {
        return historialVacunacion;
    }

    public void setHistorialVacunacion(String historialVacunacion) {
        this.historialVacunacion = historialVacunacion;
    }
}
