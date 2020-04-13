package com.example.proyecto01.modeloDB;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proyecto01.modelo.Materia;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class materiaDB extends SQLiteOpenHelper {
    private static final String DATABASE="proyecto.db";

    public materiaDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context miContext) {
        super(context, name, factory, version);
        this.miContext = miContext;
    }

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

    public String insertaMateria(Materia materia){
        String SQLi="";
        SQLi+="insert into materia(mat_id,mat_nombre,mat_nivel,mat_descrip,mat_profesor)";
        SQLi+=" values (1"+",'"+materia.getMat_nombre()+"'"+",'"+materia.getMat_nivel()+"'"+",'"+materia.getMat_descrip()+"'";
        SQLi+=",'"+materia.getMat_profesor()+"')";
        Log.d("materia",String.valueOf(materia.getMat_nombre()));
        Log.d("nivel",String.valueOf(materia.getMat_nivel()));
        Log.d("desc",String.valueOf(materia.getMat_descrip()));
        try {
            this.getWritableDatabase().execSQL(SQLi);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }

    public Cursor listaMaterias(int id){
        Cursor cursor;
        String SQLC="select ROWID as _id,* from materia where est_id = "+id;
        cursor= this.getReadableDatabase().rawQuery(SQLC,null);
        return cursor;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
