package com.example.mac.asistenciardm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.fragmentosMenu.VerificarFragment;
import com.example.mac.asistenciardm.modelos.AsistentesEvento;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JOSUE on 5/04/2017.
 */

public class AsistentesAdapter extends BaseAdapter {


    private Context context;

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
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_listar, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }

        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tvFamilia.setText(lista.get(position).getFamilia());
        //int id = lista.get(position).getIdFamilia();
       // viewHolder.tvIdFamilia.setText(String.valueOf(id));
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tvFamilia)
        TextView tvFamilia;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public AsistentesAdapter(Context context, ArrayList<AsistentesEvento> lista) {
        this.context = context;
        this.lista = lista;
    }

    private ArrayList<AsistentesEvento> lista;
}
