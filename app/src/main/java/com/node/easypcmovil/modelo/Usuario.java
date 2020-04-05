package com.node.easypcmovil.modelo;

import com.google.gson.Gson;


/**
 *
 * @author logic
 */
public class Usuario {

    private String idUsuario;
    private String idToken;
    private Persona persona;

    public Usuario() {
    }

    public Usuario(String idUsuario, Persona persona) {
        this.idUsuario = idUsuario;
        this.persona = persona;
    }

    public Usuario(String JSONUsuario) {
        Usuario aux = new Gson().fromJson(JSONUsuario, getClass());
        this.idUsuario = aux.getIdUsuario();
        this.persona = aux.getPersona();
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public String toString() {
        Usuario aux = new Usuario(getIdUsuario(), getPersona());
        aux.setIdToken(idToken);
        return new Gson().toJson(aux);
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }



}
