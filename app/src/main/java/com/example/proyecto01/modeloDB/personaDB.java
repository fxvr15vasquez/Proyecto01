package com.example.proyecto01.modeloDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.proyecto01.modelo.Persona;
import com.example.proyecto01.modelo.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class personaDB {
    private static final String DATABASE="proyecto.db";
    ArrayList<Persona> listaPers;





    public ArrayList<Persona> selecPers(Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        ArrayList<Persona> lista = new ArrayList<Persona>();
        lista.clear();
        Cursor cr;
        String SQLC="select ROWID as _id,* from Persona";
        cr = conn.getReadableDatabase().rawQuery(SQLC,null);
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

    public int buscar(String pr,Context miContext){
        int x = 0;
        listaPers = selecPers(miContext);
        for (Persona p:listaPers) {
            if (p.getPer_corr_elec().equals(pr)){
                x++;
            }
        }
        return x;
    }

    public boolean insertPers(Persona pers,Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
        if (buscar(pers.getPer_corr_elec(),miContext) == 0){
            ContentValues cv = new ContentValues();
            cv.put("per_id",pers.getPer_id());
            cv.put("per_nomb",pers.getPer_nombre());
            cv.put("per_apell",pers.getPer_apellido());
            cv.put("per_corr_elec",pers.getPer_corr_elec());
            cv.put("per_cel",pers.getPer_celular());
            int ingrs = (int) db.insert("Persona","per_id",cv);
            db.close();
            return (ingrs>0);
        }else{
            return false;
        }
    }

    public int maxPers(Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        int max=0;
        Cursor cr;
        String SQLC="select MAX(per_id) from Persona";
        cr= conn.getReadableDatabase().rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            max = cr.getInt(0);
            System.out.println("codigo maximo de persona: "+max);
        }
        return max + 1;
    }
}
