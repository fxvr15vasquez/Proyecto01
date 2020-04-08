package com.example.proyecto01.modelo;

public class Materia {
    private int mat_id;
    private String mat_nombre;
    private String mat_nivel;
    private String mat_descrip;
    private String mat_profesor;
    private int est_id;

    public Materia(int mat_id, String mat_nombre, String mat_nivel, String mat_descrip, String mat_profesor, int est_id) {
        this.mat_id = mat_id;
        this.mat_nombre = mat_nombre;
        this.mat_nivel = mat_nivel;
        this.mat_descrip = mat_descrip;
        this.mat_profesor = mat_profesor;
        this.est_id = est_id;
    }

    public Materia(String mat_nombre, String mat_nivel, String mat_descrip, String mat_profesor) {
        this.mat_nombre = mat_nombre;
        this.mat_nivel = mat_nivel;
        this.mat_descrip = mat_descrip;
        this.mat_profesor = mat_profesor;
    }
    public Materia() {

    }

    public int getMat_id() {
        return mat_id;
    }

    public void setMat_id(int mat_id) {
        this.mat_id = mat_id;
    }

    public String getMat_nombre() {
        return mat_nombre;
    }

    public void setMat_nombre(String mat_nombre) {
        this.mat_nombre = mat_nombre;
    }

    public String getMat_nivel() {
        return mat_nivel;
    }

    public void setMat_nivel(String mat_nivel) {
        this.mat_nivel = mat_nivel;
    }

    public String getMat_descrip() {
        return mat_descrip;
    }

    public void setMat_descrip(String mat_descrip) {
        this.mat_descrip = mat_descrip;
    }

    public String getMat_profesor() {
        return mat_profesor;
    }

    public void setMat_profesor(String mat_profesor) {
        this.mat_profesor = mat_profesor;
    }

    public int getEst_id() {
        return est_id;
    }

    public void setEst_id(int est_id) {
        this.est_id = est_id;
    }
}
