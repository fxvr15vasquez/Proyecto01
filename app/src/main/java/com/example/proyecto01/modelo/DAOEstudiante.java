package com.example.proyecto01.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOEstudiante {
    Estudiante estudiante;
    Context c;
    ArrayList<Estudiante> lista;
    SQLiteDatabase sql;
    String db = "proyecto.db";

    public DAOEstudiante(Context c) {
        this.c = c;
        estudiante = new Estudiante();

    }

    public boolean insertEst(Estudiante estd){
            ContentValues cv = new ContentValues();
            cv.put("est_id",estd.getEst_id());
            cv.put("usu_id",estd.getUsu_id());
            return (sql.insert("Estudiante",null,cv)>0);
    }

    public int maxEst(){
        int max=0;
        Cursor cr = sql.rawQuery("select MAX(est_id) from Estudiante", null);
        if(cr != null && cr.moveToFirst()){
            max = cr.getInt(0);
        }
        return max + 1;
    }
}
