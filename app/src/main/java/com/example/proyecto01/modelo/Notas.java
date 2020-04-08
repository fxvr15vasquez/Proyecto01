package com.example.proyecto01.modelo;

public class Notas {
    private int not_id;
    private Byte not_foto;
    private String not_descrip;
    private String not_fech;
    private int mat_id;

    public int getNot_id() {
        return not_id;
    }

    public void setNot_id(int not_id) {
        this.not_id = not_id;
    }

    public Byte getNot_foto() {
        return not_foto;
    }

    public void setNot_foto(Byte not_foto) {
        this.not_foto = not_foto;
    }

    public String getNot_descrip() {
        return not_descrip;
    }

    public void setNot_descrip(String not_descrip) {
        this.not_descrip = not_descrip;
    }

    public String getNot_fech() {
        return not_fech;
    }

    public void setNot_fech(String not_fech) {
        this.not_fech = not_fech;
    }

    public int getMat_id() {
        return mat_id;
    }

    public void setMat_id(int mat_id) {
        this.mat_id = mat_id;
    }
}
