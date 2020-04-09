package com.example.proyecto01.modeloDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.proyecto01.modelo.Persona;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class personaDB extends SQLiteOpenHelper {
    private static final String DATABASE="proyecto.db";
    Context miContext;

    ArrayList<Persona> listaPers;

    public personaDB(Context context){
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


    public ArrayList<Persona> selecPers(){
        ArrayList<Persona> lista = new ArrayList<Persona>();
        lista.clear();
        Cursor cr;
        String SQLC="select ROWID as _id,* from Persona";
        cr= this.getReadableDatabase().rawQuery(SQLC,null);
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

    public boolean insertPers(Persona pers){
        if (buscar(pers.getPer_corr_elec()) == 0){
            String SQLi="";
            SQLi+="insert into Persona (per_id,per_nomb,per_apell,per_corr_elec,per_cel)";
            SQLi+=" values (";
            SQLi+=" "+pers.getPer_id()+" ";
            SQLi+=",'"+pers.getPer_nombre()+"'";
            SQLi+=", '"+pers.getPer_apellido()+"'";
            SQLi+=",'"+pers.getPer_corr_elec()+"'";
            SQLi+=",'"+pers.getPer_celular()+"'";
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

    public int maxPers(){
        int max=0;
        Cursor cr;
        String SQLC="select MAX(per_id) from Persona";
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
