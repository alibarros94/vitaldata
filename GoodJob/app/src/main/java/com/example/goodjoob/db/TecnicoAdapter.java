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
import com.example.goodjoob.model.Tecnico;

import java.util.ArrayList;
import java.util.List;

public class TecnicoAdapter extends ArrayAdapter<Tecnico> {

    public static List<Tecnico> tecnicoList;
    private Context mCtx;
    private String calculo;
    private double avaliacao;

    public TecnicoAdapter(List<Tecnico> P, Context c){
        super(c, R.layout.listatecnico,P);
        this.tecnicoList = P;
        this.mCtx = c;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.listatecnico,null,true);

        TextView nome = (TextView) view.findViewById(R.id.nomeTecnicos);
        TextView email = (TextView) view.findViewById(R.id.emailTecnicos);
        TextView telefone = (TextView) view.findViewById(R.id.telefoneTecnicos);
        ImageView image = (ImageView) view.findViewById(R.id.imagem_pessoa);
        ImageView imagem_ratinglist = (ImageView) view.findViewById(R.id.imagem_ratingList);
        TextView numSoliList = (TextView) view.findViewById(R.id.textNumSolList);
        TextView numAvaList = (TextView) view.findViewById(R.id.textNumAvaList);

        Tecnico t = tecnicoList.get(position);
        nome.setText(t.getNome());
        email.setText(t.getEmail());
        telefone.setText(t.getTelefone());
        image.setImageResource(R.drawable.ic_perfiltecnico);
        if (t.getQtd_avaliacao() == 0){
            avaliacao = 5.00;
        }else{
            avaliacao = t.getAvaliacao() / t.getQtd_avaliacao();
            numAvaList.setTextColor(Color.YELLOW);
            numSoliList.setTextColor(Color.CYAN);
        }

        if (avaliacao > 0 && avaliacao <= 0.89){
            imagem_ratinglist.setImageResource(R.drawable.star0_5);

        }else if (avaliacao > 0.89 && avaliacao <= 1){
            imagem_ratinglist.setImageResource(R.drawable.star1_0);

        }else if (avaliacao > 1 && avaliacao <= 1.89){
            imagem_ratinglist.setImageResource(R.drawable.star1_5);

        }else if (avaliacao > 1.89 && avaliacao <= 2){
            imagem_ratinglist.setImageResource(R.drawable.star2_0);

        }else if (avaliacao > 2 && avaliacao <= 2.89){
            imagem_ratinglist.setImageResource(R.drawable.star2_5);

        }else if (avaliacao > 2.89 && avaliacao <= 3){
            imagem_ratinglist.setImageResource(R.drawable.star3_0);

        }else if (avaliacao > 3 && avaliacao <= 3.89){
            imagem_ratinglist.setImageResource(R.drawable.star3_5);

        }else if (avaliacao > 3.89 && avaliacao <= 4){
            imagem_ratinglist.setImageResource(R.drawable.star4_0);

        }else if (avaliacao > 4 && avaliacao <= 4.89){
            imagem_ratinglist.setImageResource(R.drawable.star4_5);

        }else if (avaliacao > 4.89 && avaliacao <= 5 || avaliacao == 0){
            imagem_ratinglist.setImageResource(R.drawable.star5_0);

        }

        calculo = Double.toString(avaliacao);
        numAvaList.setText(calculo);
        numSoliList.setText("("+t.getQtd_avaliacao()+")");


        return view;
    }
}
