package com.example.goodjoob;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.goodjoob.db.TecnicoAdapter;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.model.Tecnico;

public class PerfilLogado extends AppCompatActivity {

    private TextView Nome;
    private TextView Email;
    private TextView Telefone;
    private TextView Login;
    private TextView Cpf;
    private TextView Tipo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_logado);

        Nome = (TextView) findViewById(R.id.nomePerfil);
        Email = (TextView) findViewById(R.id.emailPerfil);
        Telefone = (TextView) findViewById(R.id.telefonePerfil);
        Login = (TextView) findViewById(R.id.loginPerfil);
        Cpf = (TextView) findViewById(R.id.cpfPerfil);
        Tipo = (TextView) findViewById(R.id.tipoPerfil);
        ImageView image = (ImageView) findViewById(R.id.imageView2);

        image.setImageResource(R.drawable.ic_perfiltecnico);
        Nome.setText(Perfil.getNome());
        Email.setText(Perfil.getEmail());
        Telefone.setText(Perfil.getTelefone());
        Login.setText(Perfil.getLogin());
        Cpf.setText(Perfil.getCpf());
        Tipo.setText(Perfil.getTipo());
    }
}
