package com.example.goodjoob.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.goodjoob.R;
import com.example.goodjoob.db.SolicitacaosAdapter;
import com.example.goodjoob.model.Handle;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.model.Solicitacoes;
import com.example.goodjoob.util.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Tela_Solicitacao_Cliente extends AppCompatActivity {
    private Button voltar;
    Bundle bundleExtra;
    int posicaoExtra;
    private TextView Data;
    private TextView Nome;
    private TextView Status;
    private Button avaliarServ;
    Solicitacoes s;
    private Button cancelar;
    private Button finalizar;
    private HashMap<String, String> hashMap;
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_de_solicitacao_cliente);


        bundleExtra = getIntent().getExtras();
        posicaoExtra = Integer.parseInt(bundleExtra.get("posicao").toString());
        s = SolicitacaosAdapter.solicitacaoList.get(posicaoExtra);

        Nome = (TextView) findViewById(R.id.textNomeTecNoServ);
        Data = (TextView) findViewById(R.id.textTextData);
        avaliarServ = (Button) findViewById(R.id.buttonAvaliarServ);
        cancelar = (Button) findViewById(R.id.buttonCanSoli);
        finalizar = (Button) findViewById(R.id.buttonFinalizarSoli);

        Nome.setText(s.getNomeTecnicoSoli());
        Data.setText(s.getData_orcamento());

        if(s.getStatus().equals("2") ){
            cancelar.setVisibility(View.INVISIBLE);
            finalizar.setVisibility(View.INVISIBLE);
        }
        if(s.getStatus().equals("3") ){
            cancelar.setVisibility(View.INVISIBLE);
            avaliarServ.setVisibility(View.INVISIBLE);
            finalizar.setVisibility(View.INVISIBLE);
        }
        if(s.getStatus().equals("0") || s.getStatus().equals("1")){
            avaliarServ.setVisibility(View.INVISIBLE);
        }

        avaliarServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Tela_Solicitacao_Cliente.this, Tela_Avaliando_Tecnico.class);
                intent.putExtra("posicao",posicaoExtra);
                startActivity(intent);

            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Tela_Solicitacao_Cliente.this).create();
                alertDialog.setTitle("Excluir a solicitação:");
                alertDialog.setMessage("Tem certeza que deseja deletar?" );
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.CANCELAR_SERV, new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);

                                            if (jsonObject.names().get(0).equals("success")) {
                                                Toast.makeText(getApplicationContext(), "ATENÇÃO: Cancelado com sucesso ", Toast.LENGTH_SHORT).show();
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
                                        hashMap.put("solicitante", Perfil.getId_usuario());
                                        hashMap.put("id_servico", s.getId_orcamento());
                                        return hashMap;
                                    }
                                };
                                //requestQueue.add(request);
                                Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
                            }
                        });

                alertDialog.show();
            }
        });

        finalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = new AlertDialog.Builder(Tela_Solicitacao_Cliente.this).create();
                alertDialog.setTitle("Concluir a solicitação:");
                alertDialog.setMessage("Tem certeza que a solicitação foi concluìda?" );
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Sim",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.FINALIZAR_SERV, new Response.Listener<String>() {

                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(response);

                                            if (jsonObject.names().get(0).equals("success")) {
                                                Toast.makeText(getApplicationContext(), "ATENÇÃO: Cancelado com sucesso ", Toast.LENGTH_SHORT).show();
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
                                        hashMap.put("solicitante", Perfil.getId_usuario());
                                        hashMap.put("id_servico", s.getId_orcamento());
                                        return hashMap;
                                    }
                                };
                                //requestQueue.add(request);
                                Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
                            }
                        });

                alertDialog.show();
            }
        });

    }
}