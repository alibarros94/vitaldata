package com.example.goodjoob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.goodjoob.R;
import com.example.goodjoob.TecnicoPerfil;
import com.example.goodjoob.db.SolicitacaosAdapter;
import com.example.goodjoob.db.TecnicoAdapter;
import com.example.goodjoob.model.Handle;
import com.example.goodjoob.model.Orcamento;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.model.Solicitacoes;
import com.example.goodjoob.model.Tecnico;
import com.example.goodjoob.util.Constantes;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Activity_Trabalhos extends AppCompatActivity implements AdapterView.OnItemClickListener {


    ListView listView;
    List<Solicitacoes> solicitacaoList;
    private ArrayAdapter<Solicitacoes> adapter;
    private HashMap<String, String> hashMap;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_solicitacoes);
        listView = (ListView) findViewById(R.id.listaSolicitacoes);
        solicitacaoList = new ArrayList<>();
        showList();
    }

    private void showList()
    {
        //requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.TRABALHOS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("ListaTrabalhos");
                    for (int i = 0; i <array.length(); i++){
                        JSONObject provObj = array.getJSONObject(i);
                        Solicitacoes s = new Solicitacoes(provObj.getString("id_orcamento"),provObj.getString("id_tecnicoServico"),provObj.getString("id_usuario"),provObj.getString("data_orcamento"),provObj.getString("valor_orcamento"),provObj.getString("status"),provObj.getString("descricao"),provObj.getString("nome"),provObj.getString("telefone"),provObj.getString("email"),provObj.getString("preco"),provObj.getString("tipo"));
                        solicitacaoList.add(s);
                    }
                    adapter = new SolicitacaosAdapter(solicitacaoList,getApplication());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(Activity_Trabalhos.this);

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
                hashMap.put("solicitante", Perfil.getId_usuario());

                return hashMap;
            }
        };
        //requestQueue.add(request);
        Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Falta mudar aqui para visualizar os detalhes da solicitação
        Intent intent = new Intent(Activity_Trabalhos.this, Tela_SolicitacaoTrabalho_Tecnico.class);
        intent.putExtra("posicao",position);
        startActivity(intent);
    }
}
