package com.example.felipe.clienteintegraponto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Felipe on 26/04/2017.
 */

public class TelaModuloCadastroAdapter extends BaseAdapter {
    private static ArrayList<TelaModuloCadastroClasse> dadosCadastroArrayList;
    private LayoutInflater mInflater;

    public TelaModuloCadastroAdapter(Context context, ArrayList<TelaModuloCadastroClasse> dadosUsuario) {
        dadosCadastroArrayList = dadosUsuario;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dadosCadastroArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dadosCadastroArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.modulo_cadastro, null);
            holder = new ViewHolder();

            holder.txtNomFun = (TextView) convertView.findViewById(R.id.txtNomFun);
            holder.txtCarFun = (TextView) convertView.findViewById(R.id.txtCarFun);
            holder.imgFotEmp = (ImageView) convertView.findViewById(R.id.imgFotEmp);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Bitmap bitmap = BitmapFactory.decodeByteArray(dadosCadastroArrayList.get(position).getFotEmp(), 0, dadosCadastroArrayList.get(position).getFotEmp().length);
        holder.txtNomFun.setText(dadosCadastroArrayList.get(position).getNomFun());
        holder.txtCarFun.setText(dadosCadastroArrayList.get(position).getDesCar());
        holder.imgFotEmp.setImageBitmap(bitmap);
        return convertView;
    }

    static class ViewHolder {
        TextView txtNomFun, txtCarFun;
        ImageView imgFotEmp;
    }
}
