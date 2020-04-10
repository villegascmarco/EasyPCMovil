
package com.node.easypcmovil.modelo;

import com.google.gson.Gson;


public class Persona {

    private String idPersona;
    private String nombre;
    private String apellido;
    private int estatus;
    private String correo;
    private String contrasenia;
    private String foto;
    private String token;

    public Persona() {
    }

    public Persona(String idPersona, String token){
        this.idPersona = idPersona;
        this.token = token;
    }

    public Persona(String idPersona, String nombre, String apellido, int estatus, String correo, String contrasenia, String foto, String token) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.estatus = estatus;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.foto = foto;
        this.token = token;
    }

    public Persona(int estatus, String correo) {
        this.estatus = estatus;
        this.correo = correo;
    }

    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(String idPersona) {
        this.idPersona = idPersona;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getToken() {
        return token;
    }

    public void clearToken() {
        this.token = "";
    }



    @Override
    public String toString() {
        Gson gson = new Gson();
        Persona persona = new Persona(idPersona, nombre, apellido, estatus, correo, contrasenia, foto, token);

        return gson.toJson(persona);
    }

}
