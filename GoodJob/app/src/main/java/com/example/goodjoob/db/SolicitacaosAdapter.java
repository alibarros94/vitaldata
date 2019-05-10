package com.example.goodjoob.db;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodjoob.R;
import com.example.goodjoob.model.Solicitacoes;
import com.example.goodjoob.model.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class SolicitacaosAdapter extends ArrayAdapter<Solicitacoes> {

    public static List<Solicitacoes> solicitacaoList;
    private Context mCtx;
    private String Status;

    public SolicitacaosAdapter(List<Solicitacoes> T, Context c){
        super(c, R.layout.listasolicitacoes,T);
        this.solicitacaoList = T;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listasolicitacoes,null,true);

        TextView data_orcamento = (TextView) view.findViewById(R.id.textData_orcamento);
        TextView nomeTecnicoOrc = (TextView) view.findViewById(R.id.textTecnicoOrc);
        TextView servico = (TextView) view.findViewById(R.id.textServicoOrca);
        TextView valor_orcamento = (TextView) view.findViewById(R.id.textValor_orcamento);
        ImageView imageSoli = (ImageView) view.findViewById(R.id.imagem_solicitacao);
        TextView statusSoli = (TextView) view.findViewById(R.id.textStatusSoli);

        Solicitacoes solicitacao = solicitacaoList.get(position);
        nomeTecnicoOrc.setText(solicitacao.getNomeTecnicoSoli());
        data_orcamento.setText(solicitacao.getData_orcamento());
        servico.setText(solicitacao.getId_nomeServico());
        valor_orcamento.setText(solicitacao.getValor_orcamento());
        imageSoli.setImageResource(R.drawable.planchetaeditar);
        statusSoli.setText("Não carregou");

        if (solicitacao.getStatus().equals("0")){
            imageSoli.setImageResource(R.drawable.planchetasolicitada);
            statusSoli.setText("Solicitado");
            statusSoli.setTextColor(Color.YELLOW);

        }else if (solicitacao.getStatus().equals("1")){
            imageSoli.setImageResource(R.drawable.planchetaemandamento);
            statusSoli.setText("Em Andamento");
            statusSoli.setTextColor(Color.CYAN);

        }else if (solicitacao.getStatus().equals("2")){
            imageSoli.setImageResource(R.drawable.planchetaconcluida);
            statusSoli.setText("Concluida");
            statusSoli.setTextColor(Color.GREEN);

        }else if (solicitacao.getStatus().equals("3")){
            imageSoli.setImageResource(R.drawable.planchetacenceleda);
            statusSoli.setText("Cancelado");
            statusSoli.setTextColor(Color.RED);


        }

        return view;
    }
}
