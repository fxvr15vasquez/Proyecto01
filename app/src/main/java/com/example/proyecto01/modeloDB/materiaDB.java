package com.example.proyecto01.modeloDB;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyecto01.modelo.Materia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class materiaDB extends SQLiteOpenHelper {
    private static final String DATABASE="proyecto.db";
    Context miContext;

    public materiaDB(Context context){
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

    public String insertaMateria(Materia materia){
        String SQLi="";
        SQLi+="insert into materia(2,mat_nombre,mat_nivel,mat_descrip,mat_profesor)";
        SQLi+=" values (";
        SQLi+="'"+materia.getMat_id()+"'";
        SQLi+=",'"+materia.getMat_nombre()+"'";
        SQLi+=",'"+materia.getMat_nivel()+"'";
        SQLi+=",'"+materia.getMat_descrip()+"'";
        SQLi+=",'"+materia.getMat_profesor()+"'";
        SQLi+=")";
        try {
            this.getWritableDatabase().execSQL(SQLi);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;


    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
