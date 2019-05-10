package com.example.goodjoob;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjoob.db.ServicoAdapter;
import com.example.goodjoob.db.TecnicoAdapter;
import com.example.goodjoob.model.Handle;
import com.example.goodjoob.model.Servicos;
import com.example.goodjoob.model.Tecnico;
import com.example.goodjoob.ui.Activity_PreencherServico;
import com.example.goodjoob.util.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TecnicoPerfil extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //int posicaoExtra;
    ListView listView;
    List<Servicos> servicosList;
    private ArrayAdapter<Servicos> adapter;
    private HashMap<String, String> hashMap;
    private static String IdTecnico;
    private TextView Nome;
    private TextView Email;
    private TextView Telefone;
    private double avaliacao;
    private String calculo;

    Tecnico t;
    Bundle bundleExtra;
    int posicaoExtra;
    private RequestQueue requestQueue;

    private StringRequest request;
    private Object Drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfiltecnico);

        bundleExtra = getIntent().getExtras();
        posicaoExtra = Integer.parseInt(bundleExtra.get("posicao").toString());
        t = TecnicoAdapter.tecnicoList.get(posicaoExtra);

        Nome = (TextView) findViewById(R.id.nomeTecnicos);
        Email = (TextView) findViewById(R.id.emailTecnicos);
        Telefone = (TextView) findViewById(R.id.telefoneTecnicos);
        ImageView image = (ImageView) findViewById(R.id.imagem_pessoa);
        ImageView imagem_rating = (ImageView) findViewById(R.id.imagem_rating);
        TextView numAvaList02 = (TextView) findViewById(R.id.textNumAvaList02);

        IdTecnico = t.getId_usuario();
        Nome.setText(t.getNome());
        Email.setText(t.getEmail());
        Telefone.setText(t.getTelefone());
        image.setImageResource(R.drawable.ic_perfiltecnico);
        imagem_rating.setImageResource(R.drawable.star5_0);

        if (t.getQtd_avaliacao() == 0){
            avaliacao = 5.00;
        }else{
            avaliacao = t.getAvaliacao() / t.getQtd_avaliacao();
            numAvaList02.setTextColor(Color.WHITE);
        }

        if (avaliacao > 0 && avaliacao <= 0.89){
            imagem_rating.setImageResource(R.drawable.star0_5);

        }else if (avaliacao > 0.89 && avaliacao <= 1){
            imagem_rating.setImageResource(R.drawable.star1_0);

        }else if (avaliacao > 1 && avaliacao <= 1.89){
            imagem_rating.setImageResource(R.drawable.star1_5);

        }else if (avaliacao > 1.89 && avaliacao <= 2){
            imagem_rating.setImageResource(R.drawable.star2_0);

        }else if (avaliacao > 2 && avaliacao <= 2.89){
            imagem_rating.setImageResource(R.drawable.star2_5);

        }else if (avaliacao > 2.89 && avaliacao <= 3){
            imagem_rating.setImageResource(R.drawable.star3_0);

        }else if (avaliacao > 3 && avaliacao <= 3.89){
            imagem_rating.setImageResource(R.drawable.star3_5);

        }else if (avaliacao > 3.89 && avaliacao <= 4){
            imagem_rating.setImageResource(R.drawable.star4_0);

        }else if (avaliacao > 4 && avaliacao <= 4.89){
            imagem_rating.setImageResource(R.drawable.star4_5);

        }else if (avaliacao > 4.89 && avaliacao <= 5 || avaliacao == 0){
            imagem_rating.setImageResource(R.drawable.star5_0);

        }

        calculo = Double.toString(avaliacao);
        numAvaList02.setText(calculo);

        //conexao
        requestQueue = Volley.newRequestQueue(this);

        listView = (ListView) findViewById(R.id.listaServicos);
        servicosList = new ArrayList<>();
        showList();

    }
    private void showList()
    {
        //requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.SERVICOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("ListaServicos");
                    for (int i = 0; i <array.length(); i++){
                        JSONObject provObj = array.getJSONObject(i);
                        Servicos s = new Servicos(provObj.getString("id_tecnicoServico"),provObj.getString("id_tecnico"),provObj.getString("id_servico"),provObj.getString("preco"),provObj.getString("tipo"));
                        servicosList.add(s);
                    }
                    adapter = new ServicoAdapter(servicosList,getApplication());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(TecnicoPerfil.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                hashMap = new HashMap<String, String>();
                hashMap.put("tecnicoSelecionado", t.getId_usuario());

                return hashMap;
            }
        };
        //requestQueue.add(request);
        Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Falta mudar aqui para visualizar os detalhes da solicitação
        Intent intent = new Intent(TecnicoPerfil.this, Activity_PreencherServico.class);
        intent.putExtra("posicao",position);
        intent.putExtra("posicao2",posicaoExtra);
        startActivity(intent);
        finish();
    }

}
