package com.example.goodjoob.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.goodjoob.R;

public class Preencher_Seu_Servico extends AppCompatActivity {
    private Button addaltserv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alterar_seu_servico);

        addaltserv = (Button) findViewById(R.id.buttonConcluirAddServ);

        addaltserv.setText("Adicionar");
    }
}
