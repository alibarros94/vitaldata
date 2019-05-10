package com.example.goodjoob.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjoob.R;
import com.example.goodjoob.TecnicoPerfil;
import com.example.goodjoob.db.ServicoAdapter;
import com.example.goodjoob.model.Orcamento;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.model.Servicos;
import com.example.goodjoob.util.Constantes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

public class Activity_PreencherServico extends Activity {


    private ArrayAdapter<Servicos> adapter;
    private HashMap<String, String> hashMap;
    private RequestQueue requestQueue;
    private StringRequest request;
    private Button butaoSolicitar;
    private Button butaoVoltar;
    private String status = "1";

    private EditText data;
    private EditText hora;
    private EditText descricao;
    private EditText proposta;

    int posicaoExtra2;
    int posicaoExtra3;
    Bundle bundleExtra;
    Servicos s;
    private TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__listar_servicos);

        data = (EditText) findViewById(R.id.editTextData);
        hora = (EditText) findViewById(R.id.editTextHora);
        proposta = (EditText) findViewById(R.id.editProposta);
        descricao = (EditText) findViewById(R.id.editTextDesc);

        bundleExtra = getIntent().getExtras();
        posicaoExtra2 = Integer.parseInt(bundleExtra.get("posicao").toString());
        posicaoExtra3 = Integer.parseInt(bundleExtra.get("posicao2").toString());
        s = ServicoAdapter.servicosList.get(posicaoExtra2);

        //String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        //data.setText(currentDateTimeString);
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = sdf.format(date);
        data.setText(dateString);

        requestQueue = Volley.newRequestQueue(this);

        butaoVoltar = (Button) findViewById(R.id.buttonVoltar);
        butaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Activity_PreencherServico.this, TecnicoPerfil.class);
                intent.putExtra("posicao",posicaoExtra3);
                startActivity(intent);
                finish();

            }
        });

        butaoSolicitar = (Button) findViewById(R.id.buttonSoli);
        butaoSolicitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Orcamento orcamento = new Orcamento();

                //--------------------------------------------------------------------------------------------conexao
                request = new StringRequest(Request.Method.POST, Constantes.ORCAMENTO, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if (jsonObject.names().get(0).equals("success")) {
                                Toast.makeText(getApplicationContext(), "ATENÇÃO: Solicitação  efetuada com sucesso ", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ListarSolicitacoes.class));
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "ATENÇÃO: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),  e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        hashMap = new HashMap<String, String>();
                        hashMap.put("id_tecnicoServico",String.valueOf(s.getId_tecnicoServico()));
                        hashMap.put("id_usuario", String.valueOf(Perfil.getId_usuario()));
                        hashMap.put("data_orcamento", data.getText().toString());
                        hashMap.put("hora", hora.getText().toString());
                        hashMap.put("valor_orcamento", proposta.getText().toString());
                        hashMap.put("descricao", descricao.getText().toString());

                        return hashMap;
                    }
                };
                requestQueue.add(request);
            }
        });
    }
}

