package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto01.modelo.DAOUsuario;
import com.example.proyecto01.modelo.Persona;
import com.example.proyecto01.modelo.Usuario;

public class Registro extends AppCompatActivity implements View.OnClickListener{
    EditText nomb, apell, correo, telef, user, pass, pass2;
    Button btnguar, btncanc , btningre, btnregis;
    DAOUsuario daoUsuario = new DAOUsuario();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

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
                    Usuario us = new Usuario();
                    us.setUsu_id(daoUsuario.maxUser());
                    us.setUsu_nomb(user.getText().toString());
                    us.setUsu_pass(pass.getText().toString());
                    us.setPer_id(daoUsuario.maxUser());

                    Persona pe = new Persona();
                    pe.setPer_id(daoUsuario.maxUser());
                    pe.setPer_nombre(nomb.getText().toString());
                    pe.setPer_apellido(apell.getText().toString());
                    pe.setPer_corr_elec(correo.getText().toString());
                    pe.setPer_celular(telef.getText().toString());

                }else{

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
