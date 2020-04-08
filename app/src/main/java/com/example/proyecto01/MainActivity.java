package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto01.modeloDB.usuarioDB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText lguser, lgpass;
    Button btningre, btnregis;

    usuarioDB usuDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuDB = new usuarioDB(getApplicationContext());

        btningre= (Button) findViewById(R.id.btningre);
        btnregis= (Button) findViewById(R.id.btnregis);

        lguser=(EditText) findViewById(R.id.etxtluser);
        lgpass=(EditText) findViewById(R.id.etxtlpass);

        btningre.setOnClickListener(this);
        btnregis.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btningre:
                String user = lguser.getText().toString();
                String pass = lgpass.getText().toString();
                if (user.equals("") && lgpass.equals("")){
                    Toast.makeText(this,"Error:  campos bacios",Toast.LENGTH_SHORT).show();
                }else if (usuDB.login(user,pass) != 0){
                    Intent innav = new Intent(MainActivity.this,Inicio.class);
                    innav.putExtra("user_id",usuDB.login(user,pass));
                    startActivity(innav);
                }else {
                    Toast.makeText(this,"Error:  usuario o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnregis:
                Intent inregis = new Intent(MainActivity.this,Registro.class);
                startActivity(inregis);
                break;
        }
    }
}
