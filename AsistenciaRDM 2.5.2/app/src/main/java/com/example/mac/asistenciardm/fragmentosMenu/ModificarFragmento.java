package com.example.mac.asistenciardm.fragmentosMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mac.asistenciardm.MainAsistenciaActivity;
import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.adapters.AsistentesAdapter;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.AsistentesEvento;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ModificarFragmento extends Fragment {

    @BindView(R.id.svFiltrado)
    SearchView svFiltrado;
    @BindView(R.id.lvListaRegistrar)
    ListView lvListaRegistrar;
    Unbinder unbinder;

    public ModificarFragmento() {
        // Required empty public constructor
    }
private ArrayList<AsistentesEvento> lista = new ArrayList<>();
    private ArrayList<AsistentesEvento> lista1;
    public static int idFamilia = 0;
    public static int id_AsistenteEvento = 0;
    public static String familiDetalle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar, container, false);
        ButterKnife.bind(this, view);

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
        lista = sentenciaSQL.listarFamiliasAsistieronEvento(MainAsistenciaActivity.idEventoo);
        final AsistentesAdapter asistentesAdapter = new AsistentesAdapter(getContext(), lista);
        lvListaRegistrar.setAdapter(asistentesAdapter);
        lvListaRegistrar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idFamilia = lista.get(position).getIdFamilia();
                familiDetalle = lista.get(position).getFamilia();
                id_AsistenteEvento = lista.get(position).getIdAsistenciaEvento();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ModificarAsistenciaFragment modificarAsistenciaFragment = new ModificarAsistenciaFragment();
                fragmentTransaction.replace(R.id.fgFragmento, modificarAsistenciaFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        svFiltrado.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                lista1 = sentenciaSQL.filtratListarFamiliasAsistieronEvento(newText, MainAsistenciaActivity.idEventoo);
                AsistentesAdapter asistentesAdapter1 = new AsistentesAdapter(getContext(), lista1);

                lvListaRegistrar.setAdapter(asistentesAdapter1);

                return true;
            }
        });
        return view;
    }

}
