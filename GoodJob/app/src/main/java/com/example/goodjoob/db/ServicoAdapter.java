package com.example.goodjoob.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodjoob.R;
import com.example.goodjoob.model.Servicos;
import com.example.goodjoob.model.Tecnico;


import java.util.List;

public class ServicoAdapter extends ArrayAdapter<Servicos> {

    public static List<Servicos> servicosList;
    private Context mCtx;

    public ServicoAdapter(List<Servicos> T, Context c){
        super(c, R.layout.listaservicos,T);
        this.servicosList = T;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listaservicos,null,true);

        TextView tipoServico = (TextView) view.findViewById(R.id.tipoServico);
        TextView Valor = (TextView) view.findViewById(R.id.textValor);
        ImageView imageServ = (ImageView) view.findViewById(R.id.imagem_servico);


        Servicos servicos = servicosList.get(position);
        tipoServico.setText(servicos.getTipo());
        Valor.setText(servicos.getPreco());
        imageServ.setImageResource(R.drawable.ic_ferramenta1);


        return view;
    }
}