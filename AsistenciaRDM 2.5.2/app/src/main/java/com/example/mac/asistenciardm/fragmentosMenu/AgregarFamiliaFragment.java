package com.example.mac.asistenciardm.fragmentosMenu;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mac.asistenciardm.EventoActivity;
import com.example.mac.asistenciardm.MainAsistenciaActivity;
import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.adapters.AsistentesAdapter;
import com.example.mac.asistenciardm.adapters.FamiliaAdapter;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Familia;
import com.example.mac.asistenciardm.modelos.Familia;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AgregarFamiliaFragment extends Fragment {


    @BindView(R.id.svFiltrado)
    SearchView svFiltrado;
    @BindView(R.id.lvListaFamilias)
    ListView lvListaFamilias;
    Unbinder unbinder;

    private ArrayList<Familia> lista;
    public static int idFamilia = 0;
    public static String familia;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_agregar_familia, container, false);
        ButterKnife.bind(this, view);
        final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
        lista = sentenciaSQL.listarFamilia();
        final FamiliaAdapter familiaAdapter = new FamiliaAdapter(lista, getContext());
        lvListaFamilias.setAdapter(familiaAdapter);
        lvListaFamilias.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idFamilia = lista.get(position).getId();
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("AGREGAR A INVITADOS");
                alertDialog.setMessage("¿Esta seguro de agregar a esta familia?");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Ahh, pos si",

                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        int id_evento = EventoActivity.idEvento;

                        sentenciaSQL.registrarParaAsistencia(idFamilia, id_evento);
                        Toast.makeText(getActivity(), "Se agrego la familia", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });


        // Inflate the layout for this fragment
        //View view = inflater.inflate(R.layout.fragment_agregar_familia, container, false);
        //unbinder = ButterKnife.bind(this, view);
        return view;
    }

}
