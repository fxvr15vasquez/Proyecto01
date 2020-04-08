package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto01.modelo.Materia;
import com.example.proyecto01.modeloDB.materiaDB;

public class IngresoMateria extends AppCompatActivity {
    private EditText txtNomMat;
    private EditText txtDes;
    private Spinner spnNiv;
    private EditText txtNomProf;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_materia);

        //Casting de elementos>
        txtNomMat=(EditText)findViewById(R.id.etxtMnomb);
        txtDes=(EditText)findViewById(R.id.edtTxtDes);
        spnNiv=(Spinner)findViewById(R.id.spnNiv);
        txtNomProf=(EditText)findViewById(R.id.txtNomProf);
        btnGuardar=(Button)findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                materiaDB materiadb = new materiaDB(getApplicationContext());
                Materia materia=new Materia(txtNomMat.getText().toString(),txtDes.getText().toString(),spnNiv.getSelectedItem().toString(),txtNomProf.getText().toString());

                String sentencia=materiadb.insertaMateria(materia);
                if(sentencia==null){
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR " +sentencia,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public void GuaMat(){

    }
}
