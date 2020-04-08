package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto01.modelo.DAOEstudiante;
import com.example.proyecto01.modelo.DAOPersona;
import com.example.proyecto01.modelo.DAOUsuario;
import com.example.proyecto01.modelo.Estudiante;
import com.example.proyecto01.modelo.Persona;
import com.example.proyecto01.modelo.Usuario;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    EditText nomb, apell, correo, telef, user, pass, pass2;
    Button btnguar, btncanc , btningre, btnregis;
    DAOUsuario daoUsuario;
    DAOPersona daoPersona;
    DAOEstudiante daoEstudiante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        daoUsuario = new DAOUsuario(getApplicationContext());
        daoPersona = new DAOPersona(getApplicationContext());
        daoEstudiante = new DAOEstudiante(getApplicationContext());

        nomb=(EditText) findViewById(R.id.etxtRnomb);
        apell=(EditText) findViewById(R.id.etxtRapell);
        correo=(EditText) findViewById(R.id.etxtRcorelec);
        telef=(EditText) findViewById(R.id.etxtRtelef);
        user=(EditText) findViewById(R.id.etxtRuser);
        pass=(EditText) findViewById(R.id.edtxtRpass);
        pass2=(EditText) findViewById(R.id.edtxtRpasscnf);
        btnguar= (Button) findViewById(R.id.btnRguard);
        btncanc= (Button) findViewById(R.id.btnRcanc);

        btnguar.setOnClickListener(this);
        btncanc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRguard:
                if (pass.getText().toString().equals(pass2.getText().toString())){
                    Persona pe = new Persona();
                    pe.setPer_id(daoUsuario.maxUser());
                    pe.setPer_nombre(nomb.getText().toString());
                    pe.setPer_apellido(apell.getText().toString());
                    pe.setPer_corr_elec(correo.getText().toString());
                    pe.setPer_celular(telef.getText().toString());

                    Usuario us = new Usuario();
                    us.setUsu_id(daoUsuario.maxUser());
                    us.setUsu_nomb(user.getText().toString());
                    us.setUsu_pass(pass.getText().toString());
                    us.setPer_id(pe.getPer_id());

                    Estudiante est = new Estudiante();
                    est.setEst_id(daoEstudiante.maxEst());
                    est.setUsu_id(us.getUsu_id());

                    if (!us.isNull()){
                        Toast.makeText(this,"Error:  campos bacios",Toast.LENGTH_SHORT).show();
                    }else if(daoPersona.insertPers(pe) && daoUsuario.insertUser(us) && daoEstudiante.insertEst(est)){
                        Toast.makeText(this,"Usuario ingresado",Toast.LENGTH_SHORT).show();
                        Intent inlogin2 = new Intent(Registro.this,MainActivity.class);
                        startActivity(inlogin2);
                        finish();
                    }else{
                        Toast.makeText(this,"Usuario ya existe",Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this,"Error:  las contraseñas no son inguales",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnRcanc:
                Intent inlogin = new Intent(Registro.this,MainActivity.class);
                startActivity(inlogin);
                finish();
                break;
        }
    }
}
