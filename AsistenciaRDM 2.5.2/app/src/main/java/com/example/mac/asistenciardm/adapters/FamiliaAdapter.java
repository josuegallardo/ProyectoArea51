package com.example.mac.asistenciardm.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.modelos.AsistentesEvento;
import com.example.mac.asistenciardm.modelos.Combo;
import com.example.mac.asistenciardm.modelos.Familia;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JOSUE on 24/04/2017.
 */

public class FamiliaAdapter  extends BaseAdapter{
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
        FamiliaAdapter.ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_listar, parent, false);
            viewHolder = new FamiliaAdapter.ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (FamiliaAdapter.ViewHolder) convertView.getTag();
        viewHolder.tvFamilia.setText(lista.get(position).getFamilia());
        return convertView;
    }
    static class ViewHolder {
        @BindView(R.id.tvFamilia)
        TextView tvFamilia;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    private ArrayList<Familia> lista;

    public FamiliaAdapter(ArrayList<Familia> lista, Context context) {
        this.lista = lista;
        this.context = context;
    }

    private Context context;
}
