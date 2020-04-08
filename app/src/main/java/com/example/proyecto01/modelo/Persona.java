package com.example.proyecto01.modelo;

public class Persona {
    private int per_id;
    private String per_nombre;
    private String per_apellido;
    private String per_corr_elec;
    private String per_celular;

    public Persona() {
    }

    public Persona(String per_nombre, String per_apellido, String per_corr_elec, String per_celular) {
        this.per_nombre = per_nombre;
        this.per_apellido = per_apellido;
        this.per_corr_elec = per_corr_elec;
        this.per_celular = per_celular;
    }

    public String toString() {
        return "Usuario{ per_id = " + per_id +
                ", per_nomb = '" + per_nombre + '\'' +
                ", per_apell ='" + per_apellido + '\'' +
                ", per_corr_elec ='" + per_corr_elec +'\''+
                ", per_cel ='" +  per_celular +'\''+
                '}';
    }

    public int getPer_id() {
        return per_id;
    }

    public void setPer_id(int per_id) {
        this.per_id = per_id;
    }

    public String getPer_nombre() {
        return per_nombre;
    }

    public void setPer_nombre(String per_nombre) {
        this.per_nombre = per_nombre;
    }

    public String getPer_apellido() {
        return per_apellido;
    }

    public void setPer_apellido(String per_apellido) {
        this.per_apellido = per_apellido;
    }

    public String getPer_corr_elec() {
        return per_corr_elec;
    }

    public void setPer_corr_elec(String per_corr_elec) {
        this.per_corr_elec = per_corr_elec;
    }

    public String getPer_celular() {
        return per_celular;
    }

    public void setPer_celular(String per_celular) {
        this.per_celular = per_celular;
    }
}
