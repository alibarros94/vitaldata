package com.example.goodjoob.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.goodjoob.R;
import com.example.goodjoob.db.SolicitacaosAdapter;
import com.example.goodjoob.model.Solicitacoes;

public class Tela_Avaliando_Tecnico extends AppCompatActivity {
    private Button voltar;
    Bundle bundleExtra;
    int posicaoExtra;
    private TextView Nome;
    private TextView Data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_dados_de_avaliacao_tecnico);

        bundleExtra = getIntent().getExtras();
        posicaoExtra = Integer.parseInt(bundleExtra.get("posicao").toString());
        Solicitacoes s = SolicitacaosAdapter.solicitacaoList.get(posicaoExtra);

        Nome = (TextView) findViewById(R.id.textNomeTecAvaliando);
        Data = (TextView) findViewById(R.id.textDataAvaliando);

        Nome.setText(s.getNomeTecnicoSoli());
        Data.setText(s.getData_orcamento());
    }
}
