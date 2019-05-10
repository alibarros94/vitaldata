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
import com.example.goodjoob.model.Trabalhos;

import java.util.List;

public class TrabalhosAdapter extends ArrayAdapter<Trabalhos> {
    public static List<Trabalhos> trabalhoList;
    private Context mCtx;
    private String Status;

    public TrabalhosAdapter(List<Trabalhos> T, Context c){
        super(c, R.layout.listatrabalhos,T);
        this.trabalhoList = T;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listatrabalhos,null,true);

        TextView data_orcamento = (TextView) view.findViewById(R.id.textData_orcamento);
        TextView nomeTecnicoOrc = (TextView) view.findViewById(R.id.textTecnicoOrc);
        TextView servico = (TextView) view.findViewById(R.id.textServicoOrca);
        TextView valor_orcamento = (TextView) view.findViewById(R.id.textValor_orcamento);
        ImageView imageSoli = (ImageView) view.findViewById(R.id.imagem_solicitacao);
        TextView statusSoli = (TextView) view.findViewById(R.id.textStatusSoli);

        Trabalhos tr = trabalhoList.get(position);
        nomeTecnicoOrc.setText(tr.getNomeTecnicoSoli());
        data_orcamento.setText(tr.getData_orcamento());
        servico.setText(tr.getId_nomeServico());
        valor_orcamento.setText(tr.getValor_orcamento());
        imageSoli.setImageResource(R.drawable.planchetaeditar);
        statusSoli.setText("Não carregou");

        if (tr.getStatus().equals("0")){
            imageSoli.setImageResource(R.drawable.planchetasolicitada);
            statusSoli.setText("Solicitado");
            statusSoli.setTextColor(Color.YELLOW);

        }else if (tr.getStatus().equals("1")){
            imageSoli.setImageResource(R.drawable.planchetaemandamento);
            statusSoli.setText("Em Andamento");
            statusSoli.setTextColor(Color.CYAN);

        }else if (tr.getStatus().equals("2")){
            imageSoli.setImageResource(R.drawable.planchetaconcluida);
            statusSoli.setText("Concluida");
            statusSoli.setTextColor(Color.GREEN);

        }else if (tr.getStatus().equals("3")){
            imageSoli.setImageResource(R.drawable.planchetacenceleda);
            statusSoli.setText("Cancelado");
            statusSoli.setTextColor(Color.RED);


        }

        return view;
    }
}
