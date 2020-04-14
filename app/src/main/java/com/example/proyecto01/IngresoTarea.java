package com.example.proyecto01;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class IngresoTarea extends AppCompatActivity implements View.OnClickListener{

    ImageView imgtarea;
    Button btningre_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingreso_tarea);

        imgtarea = (ImageView) findViewById(R.id.imvtarea);
        btningre_img = (Button) findViewById(R.id.btnTingreImg);

        btningre_img.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTingreImg:
                Intent intimg = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intimg.setType("image/");
                startActivityForResult(intimg.createChooser(intimg,"Seleccione la Aplicación"),10);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            imgtarea.setImageURI(path);
        }
    }
}
