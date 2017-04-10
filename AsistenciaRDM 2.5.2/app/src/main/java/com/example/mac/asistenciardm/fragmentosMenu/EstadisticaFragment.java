package com.example.mac.asistenciardm.fragmentosMenu;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.mac.asistenciardm.MainAsistenciaActivity;
import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.database.SentenciaSQL;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class EstadisticaFragment extends Fragment {


    @BindView(R.id.tvNombreEvento)
    TextView tvNombreEvento;
    @BindView(R.id.tvInvitados)
    TextView tvInvitados;
    @BindView(R.id.tvAsistieron)
    TextView tvAsistieron;
    @BindView(R.id.tvNoAsistieron)
    TextView tvNoAsistieron;
    @BindView(R.id.btOK)
    Button btOK;

    PieChart pieChart;
    ArrayList<Entry> entries;
    ArrayList<String> PieEntryLabels;
    PieDataSet pieDataSet;
    PieData pieData;
    @BindView(R.id.chart1)
    PieChart chart1;


    public EstadisticaFragment() {
        // Required empty public constructor
    }

    private PieChart mChart;
    private int invitados = 0;
    private int asistieron = 0;
    private int noAsistieron = 0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_estadistica, container, false);
        ButterKnife.bind(this, view);

        final SentenciaSQL sentenciaSQL = new SentenciaSQL(getActivity());
        invitados = sentenciaSQL.familiasAsitieron(MainAsistenciaActivity.idEventoo);
        asistieron = sentenciaSQL.familiasAsitieronPorTipo(MainAsistenciaActivity.idEventoo, 2);
        noAsistieron = sentenciaSQL.familiasAsitieronPorTipo(MainAsistenciaActivity.idEventoo, 3);
        int noAsistieronn = invitados - asistieron;
        tvInvitados.setText(String.valueOf(invitados));
        tvAsistieron.setText(String.valueOf(asistieron));
        //tvAsistieron.setText(String.valueOf(asistieron));
        tvNoAsistieron.setText(String.valueOf(noAsistieronn));

      //  pieChart = (PieChart)getArguments(R.id.chart1);
pieChart = chart1;
        entries = new ArrayList<>();
        PieEntryLabels = new ArrayList<String>();
        AddValuesToPIEENTRY();
        AddValuesToPieEntryLabels();
        pieDataSet = new PieDataSet(entries, "");
        pieData = new PieData(PieEntryLabels, pieDataSet);
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setData(pieData);
        pieChart.animateY(3000);
        return view;

    }

    public void AddValuesToPIEENTRY() {
    int noAsistieronn = invitados - asistieron;
        entries.add(new BarEntry(asistieron, 0));
        entries.add(new BarEntry(noAsistieronn, 1));


    }

    public void AddValuesToPieEntryLabels() {

        PieEntryLabels.add("Asistieron");
        PieEntryLabels.add("No asistieron");


    }





}


