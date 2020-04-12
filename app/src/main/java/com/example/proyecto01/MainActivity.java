package com.example.proyecto01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.proyecto01.modeloDB.usuarioDB;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    EditText lguser, lgpass;
    Button btningre, btnregis;

    usuarioDB usuDB = new usuarioDB(getApplicationContext());
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
                break;
            case R.id.btnregis:
                Intent inregis = new Intent(MainActivity.this,Registro.class);
                startActivity(inregis);
                break;
        }
    }
}
