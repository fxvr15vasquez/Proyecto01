package com.example.proyecto01.modelo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Estudiante {
    private int est_id;
    private int usu_id;

    public Estudiante() {
    }

    public String toString() {
        return "Estudiante { est_id = " + est_id +
                ", usu_id = " + usu_id +
                '}';
    }

    public int getEst_id() {
        return est_id;
    }

    public void setEst_id(int est_id) {
        this.est_id = est_id;
    }

    public int getUsu_id() {
        return usu_id;
    }

    public void setUsu_id(int usu_id) {
        this.usu_id = usu_id;
    }
}


