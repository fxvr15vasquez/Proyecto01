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

public class estudianteDB extends SQLiteOpenHelper {

    private static final String DATABASE="proyecto.db";
    Context miContext;

    ArrayList<Usuario> listaUsu;

    public estudianteDB(Context context){
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


    public int  selecEst(int id){
        int estdID = 0;
        Cursor cr;
        String SQLC="select est_id from Estudiante Where usu_id = "+id+" ";
        cr= this.getReadableDatabase().rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            estdID= cr.getInt(0);
        }

        return estdID;
    }

    public boolean insertEst(Estudiante estd){

            String SQLi="";
            SQLi+="insert into Estudiante (est_id,usu_id)";
            SQLi+=" values (";
            SQLi+=" "+estd.getEst_id()+" ";
            SQLi+=", "+estd.getUsu_id()+" ";
            SQLi+=")";
            try {
                this.getWritableDatabase().execSQL(SQLi);
                return true;
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                return false;
            }
    }

    public int maxEst(){
        int max=0;
        Cursor cr;
        String SQLC="select MAX(est_id) from Estudiante";
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
