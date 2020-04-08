package com.example.proyecto01.modelo;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;
import java.util.ArrayList;

public class DAOUsuario {
    Usuario user;
    Context c;
    ArrayList<Usuario> lista;
    SQLiteDatabase sql;
    private static final String DATABASE = "proyecto.db";


    public DAOUsuario(Context context) {
        this.c=context;
        user = new Usuario();
        sql = c.openOrCreateDatabase(DATABASE,c.MODE_PRIVATE,null);
    }

    public boolean insertUser(Usuario usu){
        if (buscar(usu.getUsu_nomb()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("usu_id",usu.getUsu_id());
            cv.put("usu_nomb",usu.getUsu_nomb());
            cv.put("usu_cont",usu.getUsu_pass());
            cv.put("per_id",usu.getPer_id());
            return (sql.insert("Usuario",null,cv)>0);
        }else{
            return false;
        }
    }

    public int buscar(String us){
        int x = 0;
        lista = selecUsers();
        for (Usuario usr:lista) {
            if (usr.getUsu_nomb().equals(us)){
                x++;
            }
        }
        return x;
    }

    public ArrayList<Usuario> selecUsers(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from Usuario", null);
        if(cr != null && cr.moveToFirst()){
            do{
                Usuario us = new Usuario();
                us.setUsu_id(cr.getInt(0));
                us.setUsu_nomb(cr.getString(1));
                us.setUsu_pass(cr.getString(2));
                us.setPer_id(cr.getInt(3));
                lista.add(us);
            }while (cr.moveToNext());
        }
        return lista;
    }

    public int maxUser(){
        int max=0;
        Cursor cr = sql.rawQuery("select MAX(usu_id) from Usuario", null);
        if(cr != null && cr.moveToFirst()){
            max = cr.getInt(0);
        }
        return max + 1;
    }
}
