package com.example.proyecto01.modeloDB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyecto01.modelo.Estudiante;
import com.example.proyecto01.modelo.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class estudianteDB{

    private static final String DATABASE="proyecto.db";
    ArrayList<Usuario> listaUsu;

    public int  selecEst(int id,Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        int estdID = 0;
        Cursor cr;
        String SQLC="select est_id from Estudiante Where usu_id = "+id+" ";
        cr= db.rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            estdID= cr.getInt(0);
        }
        db.close();
        return estdID;
    }

    public boolean insertEst(Estudiante estd,Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        SQLiteDatabase db = conn.getWritableDatabase();
            String SQLi="";
            SQLi+="insert into Estudiante (est_id,usu_id)";
            SQLi+=" values (";
            SQLi+=" "+estd.getEst_id()+" ";
            SQLi+=", "+estd.getUsu_id()+" ";
            SQLi+=")";
            try {
                db.execSQL(SQLi);
                db.close();
                return true;
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                return false;
            }
    }

    public int maxEst(Context miContext){
        Conexion conn = new Conexion(miContext,DATABASE,null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        int max=0;
        Cursor cr;
        String SQLC="select MAX(est_id) from Estudiante";
        cr= db.rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            max = cr.getInt(0);
        }
        db.close();
        return max + 1;
    }
}
