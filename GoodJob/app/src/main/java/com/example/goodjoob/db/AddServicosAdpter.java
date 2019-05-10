package com.example.goodjoob.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodjoob.R;
import com.example.goodjoob.model.AddServicos;
import com.example.goodjoob.model.Servicos;

import java.util.List;

public class AddServicosAdpter extends ArrayAdapter<AddServicos> {

    public static List<AddServicos> addservicosList;
    private Context mCtx;

    public AddServicosAdpter(List<AddServicos> T, Context c){
        super(c, R.layout.listaaddservico,T);
        this.addservicosList = T;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listaaddservico,null,true);

        TextView tipoServico = (TextView) view.findViewById(R.id.tipoServico2);
        TextView Valor = (TextView) view.findViewById(R.id.textValor2);
        ImageView imageServ = (ImageView) view.findViewById(R.id.imagem_servico2);


        AddServicos addservicos = addservicosList.get(position);
        tipoServico.setText(addservicos.getTipo());
        imageServ.setImageResource(R.drawable.ic_ferramenta1);


        return view;
    }
}