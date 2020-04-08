package com.example.proyecto01.modelo;

public class Horario_Estudiante {
    private int h_e_id;
    private byte h_e_foto;
    private int hor_id;
    private int est_id;

    public int getH_e_id() {
        return h_e_id;
    }

    public void setH_e_id(int h_e_id) {
        this.h_e_id = h_e_id;
    }

    public byte getH_e_foto() {
        return h_e_foto;
    }

    public void setH_e_foto(byte h_e_foto) {
        this.h_e_foto = h_e_foto;
    }

    public int getHor_id() {
        return hor_id;
    }

    public void setHor_id(int hor_id) {
        this.hor_id = hor_id;
    }

    public int getEst_id() {
        return est_id;
    }

    public void setEst_id(int est_id) {
        this.est_id = est_id;
    }
}
