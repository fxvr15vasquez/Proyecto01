package com.example.proyecto01.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.proyecto01.R;
import com.example.proyecto01.modeloDB.materiaDB;
import com.example.proyecto01.Inicio;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private ListView listaMaterias;
    private SimpleCursorAdapter cursorAdapter;
    private materiaDB conecta;
    private Inicio in = new Inicio();
    int id = in.usuID;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        conecta = new materiaDB(getContext());

        muestraMateria(root);
        return root;
    }

    @Override
    public void onResume() {
        cursorAdapter.swapCursor(conecta.listaMaterias(id));
        listaMaterias.setAdapter(cursorAdapter);
        super.onResume();
    }
    private void muestraMateria(final View root){
        final Cursor cursorp = conecta.listaMaterias(id);
        String[] desde = new String[]{"mat_id","mat_nombre mat_nivel \n mat_profesor,","mat_descrip"};
        int[] hasta=new int[]{R.id.txtLMid,R.id.txtLMnomb,R.id.txtLMdescrip};
        cursorAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.listadematerias ,cursorp,desde,hasta,0);
        listaMaterias = (ListView)root.findViewById(R.id.listMaterias);
        listaMaterias.setAdapter(cursorAdapter);
        //Implentacion de long click en lista
        listaMaterias.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {

                                Cursor itemp = (Cursor) listaMaterias.getItemAtPosition(position);
                                String Ruc = itemp.getString(1);

                return false;
            }

        });
    }
}
