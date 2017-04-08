package com.example.mac.asistenciardm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.modelos.Hijos;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alumno on 4/5/17.
 */

public class HijosAdapter extends BaseAdapter {
    public HijosAdapter(Context context, ArrayList<Hijos> lista) {
        this.context = context;
        this.lista = lista;
    }

    private Context context;
    private ArrayList<Hijos> lista;

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HijosAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_lista_hijos, parent, false);
            viewHolder = new HijosAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (HijosAdapter.ViewHolder) convertView.getTag();
        viewHolder.tvNombre.setText(lista.get(position).getNombre());
        viewHolder.tvNivel.setText(lista.get(position).getNivel());
        viewHolder.tvGrado.setText(lista.get(position).getGrado());
        viewHolder.tvSeccion.setText(lista.get(position).getSeccion());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.etNombre)
        TextView tvNombre;
        @BindView(R.id.etNivel)
        TextView tvNivel;
        @BindView(R.id.etGrado)
        TextView tvGrado;
        @BindView(R.id.etSeccion)
        TextView tvSeccion;
        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }





}
