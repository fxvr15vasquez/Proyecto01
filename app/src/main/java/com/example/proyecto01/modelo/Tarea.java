package com.example.proyecto01.modelo;

public class Tarea {
    private int tar_id;
    private String tar_fech_entrega;
    private String tar_nombre;
    private String tar_descrip;
    private Byte[] tar_foto;
    private int mat_id;

    public Tarea() {
    }

    public int getTar_id() {
        return tar_id;
    }

    public void setTar_id(int tar_id) {
        this.tar_id = tar_id;
    }

    public String getTar_fech_entrega() {
        return tar_fech_entrega;
    }

    public void setTar_fech_entrega(String tar_fech_entrega) {
        this.tar_fech_entrega = tar_fech_entrega;
    }

    public String getTar_nombre() {
        return tar_nombre;
    }

    public void setTar_nombre(String tar_nombre) {
        this.tar_nombre = tar_nombre;
    }

    public String getTar_descrip() {
        return tar_descrip;
    }

    public void setTar_descrip(String tar_descrip) {
        this.tar_descrip = tar_descrip;
    }

    public int getMat_id() {
        return mat_id;
    }

    public Byte[] getTar_foto() {
        return tar_foto;
    }

    public void setTar_foto(Byte[] tar_foto) {
        this.tar_foto = tar_foto;
    }

    public void setMat_id(int mat_id) {
        this.mat_id = mat_id;
    }
}
