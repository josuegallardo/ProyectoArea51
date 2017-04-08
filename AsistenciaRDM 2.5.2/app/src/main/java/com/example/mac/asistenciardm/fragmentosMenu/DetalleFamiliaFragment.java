package com.example.mac.asistenciardm.fragmentosMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.adapters.HijosAdapter;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.example.mac.asistenciardm.modelos.Hijos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class DetalleFamiliaFragment extends Fragment {
    @BindView(R.id.lvListaHijos)
    ListView lvListaHijos;
    Unbinder unbinder;
    @BindView(R.id.tvFamilia)
    TextView tvFamilia;
    @BindView(R.id.btAsisio)
    TextView btAsisio;

    public DetalleFamiliaFragment() {

    }

    private ArrayList<Hijos> lista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalle_familia, container, false);
        ButterKnife.bind(this, view);
        tvFamilia.setText(VerificarFragment.familiDetalle);
        final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
        lista = sentenciaSQL.listarHijos(VerificarFragment.idFamilia);
        final HijosAdapter hijosAdapter = new HijosAdapter(getContext(), lista);
        //Listando a los hijos
        lvListaHijos.setAdapter(hijosAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btAsisio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
                sentenciaSQL.actualizarAsistencia(2, VerificarFragment.id_AsistenteEvento);
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                VerificarFragment verificarFragment = new VerificarFragment();
                fragmentTransaction.replace(R.id.fgFragmento, verificarFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
    }

}
