package com.example.goodjoob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjoob.R;
import com.example.goodjoob.db.ServicoAdapter;
import com.example.goodjoob.model.Handle;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.model.Servicos;
import com.example.goodjoob.util.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tela_Seus_Servicos extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    List<Servicos> servicosList;
    private ArrayAdapter<Servicos> adapter;
    private HashMap<String, String> hashMap;
    private RequestQueue requestQueue;
    private Button addServ;
    Bundle bundleExtra;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seus_servicos);

        addServ = (Button) findViewById(R.id.buttonAddServ);

        requestQueue = Volley.newRequestQueue(this);

        listView = (ListView) findViewById(R.id.listaSeusServicos);
        servicosList = new ArrayList<>();
        showList();

        addServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Tela_Seus_Servicos.this, List_Adicionar_Servico.class);
                intent.putExtra("posicao",position);
                startActivity(intent);
            }
        });

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
                    listView.setOnItemClickListener(Tela_Seus_Servicos.this);

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
                hashMap.put("tecnicoSelecionado", Perfil.getId_usuario());

                return hashMap;
            }
        };
        //requestQueue.add(request);
        Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Falta mudar aqui para visualizar os detalhes da solicitação
        Intent intent = new Intent(Tela_Seus_Servicos.this, Alterar_Servico.class);
        intent.putExtra("posicao",position);
        startActivity(intent);
    }

}