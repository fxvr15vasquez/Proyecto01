package com.example.proyecto01.modeloDB;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.proyecto01.modelo.Persona;
import com.example.proyecto01.modelo.Tarea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class tareaDB extends SQLiteOpenHelper {

    private static final String DATABASE="proyecto.db";
    Context miContext;

    ArrayList<Tarea> listaTar;

    public tareaDB(Context context){
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


    public ArrayList<Tarea> selecTars(){
        ArrayList<Tarea> lista = new ArrayList<Tarea>();
        lista.clear();
        Cursor cr;
        String SQLC="select ROWID as _id,* from Tarea";
        cr= this.getReadableDatabase().rawQuery(SQLC,null);
        if(cr != null && cr.moveToFirst()){
            do{
                Tarea ta= new Tarea();
                ta.setTar_id(cr.getInt(0));
                ta.setTar_fech_entrega(cr.getString(1));
                ta.setTar_nombre(cr.getString(2));
                ta.setTar_descrip(cr.getString(3));
                //ta.setTar_foto(cr.getBlob(4));
                ta.setMat_id(cr.getInt(5));
                lista.add(ta);
            }while (cr.moveToNext());
        }

        return lista;
    }

    public boolean insertTars(Tarea tar){
            String SQLi="";
            SQLi+="insert into Tarea (tar_id,tar_fech_entrega,tar_nomb,tar_descrip,tar_foto,mat_id)";
            SQLi+=" values (";
            SQLi+=" "+tar.getTar_id()+" ";
            SQLi+=",'"+tar.getTar_fech_entrega()+"'";
            SQLi+=",'"+tar.getTar_nombre()+"'";
            SQLi+=", '"+tar.getTar_descrip()+"'";
            SQLi+=", "+tar.getTar_foto()+" ";
            SQLi+=", "+tar.getMat_id()+" ";
            SQLi+=")";
            try {
                this.getWritableDatabase().execSQL(SQLi);
                return true;
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                return false;
            }
    }

    public int maxTars(){
        int max=0;
        Cursor cr;
        String SQLC="select MAX(tar_id) from Tarea";
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
