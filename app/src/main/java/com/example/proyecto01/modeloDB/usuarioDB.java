package com.example.proyecto01.modeloDB;

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
            String SQLi="";
            SQLi+="insert into Usuario (usu_id,usu_nomb,usu_cont,per_id)";
            SQLi+=" values (";
            SQLi+=" "+usu.getUsu_id()+" ";
            SQLi+=",'"+usu.getUsu_nomb()+"'";
            SQLi+=", '"+usu.getUsu_pass()+"'";
            SQLi+=", "+usu.getPer_id()+" ";
            SQLi+=")";
            try {
                this.getWritableDatabase().execSQL(SQLi);
                return true;
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
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

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
