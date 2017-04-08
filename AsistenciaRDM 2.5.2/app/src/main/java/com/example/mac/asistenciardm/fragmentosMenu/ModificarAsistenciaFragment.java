package com.example.mac.asistenciardm.fragmentosMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.adapters.HijosAdapter;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Hijos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ModificarAsistenciaFragment extends Fragment {


    @BindView(R.id.tvFamilia)
    TextView tvFamilia;
    @BindView(R.id.lvListaHijos)
    ListView lvListaHijos;
    @BindView(R.id.spEstado)
    Spinner spEstado;

    Unbinder unbinder;
    @BindView(R.id.btActualizar)
    TextView btActualizar;

    public ModificarAsistenciaFragment() {
        // Required empty public constructor
    }

    private ArrayList<Hijos> lista;
    private ArrayList<Combo> datoss = new ArrayList<>();
    private int idEstadoAsistencia = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_modificar_asistencia, container, false);
        unbinder = ButterKnife.bind(this, view);
        tvFamilia.setText(ModificarFragmento.familiDetalle);
        final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
        lista = sentenciaSQL.listarHijos(ModificarFragmento.idFamilia);
        final HijosAdapter hijosAdapter = new HijosAdapter(getContext(), lista);
        //Listando a los hijos
        lvListaHijos.setAdapter(hijosAdapter);
        datoss.clear();
        datoss = sentenciaSQL.obternerEstadoAsistencia();
        ArrayList<String> estadoAsistencia = new ArrayList<>();
        for (Combo item : datoss) {
            estadoAsistencia.add(item.getTexto());
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, estadoAsistencia);
        spEstado.setAdapter(arrayAdapter);


        spEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idEstadoAsistencia = datoss.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        return view;


    }


    @Override
    public void onResume() {
        super.onResume();
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
                sentenciaSQL.actualizarAsistencia(idEstadoAsistencia, ModificarFragmento.id_AsistenteEvento);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ModificarFragmento modificarFragmento = new ModificarFragmento();
                fragmentTransaction.replace(R.id.fgFragmento, modificarFragmento);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
