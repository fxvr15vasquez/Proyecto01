package com.example.proyecto01.modelo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAOPersona {
    Persona persona;
    ArrayList<Persona> listaPers;
    SQLiteDatabase sql;

    public boolean insertUser(){
        if (buscar(persona.getPer_corr_elec()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("per_id",persona.getPer_id());
            cv.put("per_nomb",persona.getPer_nombre());
            cv.put("per_apell",persona.getPer_apellido());
            cv.put("per_corr_elec",persona.getPer_corr_elec());
            cv.put("per_cel",persona.getPer_celular());
            return (sql.insert("Persona",null,cv)>0);
        }else{
            return false;
        }
    }

    public int buscar(String pr){
        int x = 0;
        listaPers = selecPers();
        for (Persona p:listaPers) {
            if (p.getPer_corr_elec().equals(pr)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Persona> selecPers(){
        ArrayList<Persona> lista = new ArrayList<Persona>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from Persona", null);
        if(cr != null && cr.moveToFirst()){
            do{
                Persona pe = new Persona();
                pe.setPer_id(cr.getInt(0));
                pe.setPer_nombre(cr.getString(1));
                pe.setPer_apellido(cr.getString(2));
                pe.setPer_corr_elec(cr.getString(3));
                pe.setPer_celular(cr.getString(4));
                lista.add(pe);
            }while (cr.moveToNext());
        }
        return lista;
    }
}
