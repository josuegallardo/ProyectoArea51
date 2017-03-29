package com.example.mac.asistenciardm.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mac.asistenciardm.R;
import com.example.mac.asistenciardm.modelos.Usuarios;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by MAC on 17/03/17.
 */

public class UsuarioAdapter extends BaseAdapter {
    private Context context;
    public UsuarioAdapter(Context context, ArrayList<Usuarios> lista) {
        this.context = context;
        this.lista = lista;
    }


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
                    .inflate(R.layout.activity_main, parent, false);
            //View = new View(convertView);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        //viewHolder.tvNombreUsuario.setText(lista.get(position).getNombre());
        viewHolder.tvPassword.setText(lista.get(position).getPassword());
        return convertView;
    }

    private ArrayList<Usuarios> lista;
    static class ViewHolder {
        //@BindView(R.id.tvNombreUsuario)
        //TextView tvNombreUsuario;
      //  @BindView(R.id.tvPassword)
        TextView tvPassword;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

}
