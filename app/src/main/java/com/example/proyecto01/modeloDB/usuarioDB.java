package com.example.proyecto01.modeloDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyecto01.modelo.Persona;
import com.example.proyecto01.modelo.Usuario;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class usuarioDB extends SQLiteOpenHelper {

    private static final String DATABASE="proyecto.db";
    Context miContext;
    SQLiteDatabase sql;
    ArrayList<Usuario> listaUsu;

    public usuarioDB(Context context){
        super(context, DATABASE,null,1);
        miContext=context;
        File pathArchivo=miContext.getDatabasePath(DATABASE);
        //VERIFICAR ARCHIVO
        if(!verificaBase(pathArchivo.getAbsolutePath())){
            //COPIAR ARCHIVO
            try {
                copiarBase(pathArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        sql = context.openOrCreateDatabase(DATABASE, context.MODE_PRIVATE, null);
    }
    //verpru
    private boolean verificaBase(String ruta){
        SQLiteDatabase miBase=null;
        try {

        }catch (Exception ex) {
            miBase = SQLiteDatabase.openDatabase(ruta, null, SQLiteDatabase.OPEN_READONLY);
        }
        if (miBase!=null){
            miBase.close();
        }

        return miBase!=null;
    }

    private  void copiarBase(File rutaBase) throws IOException {
        InputStream miInput = miContext.getAssets().open(DATABASE);
        OutputStream miOutput= new FileOutputStream(rutaBase);
        byte[] buffer=new byte[1024];
        int largo;
        while ((largo=miInput.read(buffer))>0){
            miOutput.write(buffer,0,largo);
        }
        miOutput.flush();
        miOutput.close();
        miInput.close();
    }


    public ArrayList<Usuario> selecUsers(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr;
        String SQLC="select ROWID as _id,* from Usuario";
        cr= this.getReadableDatabase().rawQuery(SQLC,null);
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

    public int buscar(String pr){
        int x = 0;
        listaUsu = selecUsers();
        for (Usuario u:listaUsu) {
            if (u.getUsu_nomb().equals(pr)){
                x++;
            }
        }
        return x;
    }

    public boolean insertUser(Usuario usu){
        if (buscar(usu.getUsu_nomb()) == 0){
            ContentValues cv = new ContentValues();
            cv.put("usu_id",usu.getUsu_id());
            cv.put("usu_nomb",usu.getUsu_nomb());
            cv.put("usu_cont",usu.getUsu_pass());
            cv.put("per_id",usu.getPer_id());
            try {
                return (sql.insert("Usuario",null,cv)>0);
            }catch (Exception ex){
                System.out.println("ERROR usuario: "+ex.getMessage());
                return false;
            }
        }else{
            return false;
        }
    }

    public int maxUser(){
        int max=0;
        Cursor cr;
        String SQLC="select MAX(usu_id) from Usuario";
        cr= this.getReadableDatabase().rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            max = cr.getInt(0);
        }
        return max + 1;
    }

    //login
    public int login(String u, String p){
        int acep=0;
        Cursor cr;
        String SQLC="select usu_id from Usuario where usu_nomb = '" + u + "' AND usu_cont = '"+ p +"'" ;

        cr= this.getReadableDatabase().rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            acep = cr.getInt(0);
        }else{
            acep = 0;
        }
        return acep;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
