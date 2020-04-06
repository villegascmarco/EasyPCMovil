package com.node.easypcmovil.modelo;

public class Estacionamiento {
    private String idEstacionamiento;
    private int estatus;
    private double latitud;
    private double longitud;
    private String nombre;
    private String foto;
    private String domicilio;
    private int capacidad;
    private double costo;
    private Administrador administrador;

    public Estacionamiento()
    {
    }

    public String getId()
    {
        return idEstacionamiento;
    }

    public void setId(String idEstacionamiento)
    {
        this.idEstacionamiento = idEstacionamiento;
    }

    public int getEstatus()
    {
        return estatus;
    }

    public void setEstatus(int estatus)
    {
        this.estatus = estatus;
    }

    public double getLatitud()
    {
        return latitud;
    }

    public void setLatitud(double latitud)
    {
        this.latitud = latitud;
    }

    public double getLongitud()
    {
        return longitud;
    }

    public void setLongitud(double longitud)
    {
        this.longitud = longitud;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getFoto()
    {
        return foto;
    }

    public void setFoto(String foto)
    {
        this.foto = foto;
    }

    public String getDomicilio()
    {
        return domicilio;
    }

    public void setDomicilio(String domicilio)
    {
        this.domicilio = domicilio;
    }

    public int getCapacidad()
    {
        return capacidad;
    }

    public void setCapacidad(int capacidad)
    {
        this.capacidad = capacidad;
    }

    public double getCosto()
    {
        return costo;
    }

    public void setCosto(double costo)
    {
        this.costo = costo;
    }

    public Administrador getAdministrador()
    {
        return administrador;
    }

    public void setAdministrador(Administrador administrador)
    {
        this.administrador = administrador;
    }



    @Override
    public String toString()
    {
        return "Estacionamiento{" + "idEstacionamiento=" + idEstacionamiento + ", estatus=" + estatus + ", latitud=" + latitud + ", longitud=" + longitud + ", nombre=" + nombre + ", foto=" + foto + ", domicilio=" + domicilio + ", capacidad=" + capacidad + ", costo=" + costo + ", administrador=" + administrador + '}';
    }

}
