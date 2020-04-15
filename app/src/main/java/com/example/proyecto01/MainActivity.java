package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto01.modeloDB.Conexion;
import com.example.proyecto01.modeloDB.usuarioDB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText lguser, lgpass;
    Button btningre, btnregis;

    Conexion con;
    usuarioDB usuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //con = new Conexion(this);
        usuDB = new usuarioDB();

        btningre= (Button) findViewById(R.id.btningre);
        btnregis= (Button) findViewById(R.id.btnregis);

        lguser=(EditText) findViewById(R.id.etxtluser);
        lgpass=(EditText) findViewById(R.id.etxtlpass);

        btningre.setOnClickListener(this);
        btnregis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String user = lguser.getText().toString();
        String pass = lgpass.getText().toString();
        int id_usu = usuDB.login(user, pass,this);
        switch (v.getId()){
            case R.id.btningre:
                if (user.equals("") && pass.equals("")){
                    Toast.makeText(this,"Campos vacios",Toast.LENGTH_SHORT).show();
                }else if(id_usu !=0){
                    Intent inlogin = new Intent(MainActivity.this,Inicio.class);
                    inlogin.putExtra("id_usu",id_usu);
                    startActivity(inlogin);
                }else{
                    Toast.makeText(this,"Usuario o contraseña incorrectas",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnregis:
                Intent inregis = new Intent(MainActivity.this,Registro.class);
                startActivity(inregis);
                break;
        }
    }
}
