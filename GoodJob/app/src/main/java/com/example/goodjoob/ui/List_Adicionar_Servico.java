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
import com.example.goodjoob.db.AddServicosAdpter;
import com.example.goodjoob.db.ServicoAdapter;
import com.example.goodjoob.model.AddServicos;
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

public class List_Adicionar_Servico extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    List<AddServicos> addservicosList;
    private ArrayAdapter<AddServicos> adapter;
    private HashMap<String, String> hashMap;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionar_um_servico);

        listView = (ListView) findViewById(R.id.listaAddServicos);
        addservicosList = new ArrayList<>();
        showList();

    }
    private void showList()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constantes.LISTA_ADD_SERV, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("ListaServicos");
                    for (int i = 0; i <array.length(); i++){
                        JSONObject provObj = array.getJSONObject(i);
                        AddServicos as = new AddServicos(provObj.getString("id_servico"),provObj.getString("tipo"));
                        addservicosList.add(as);
                    }
                    adapter = new AddServicosAdpter(addservicosList,getApplication());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(List_Adicionar_Servico.this);

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
        Intent intent = new Intent(List_Adicionar_Servico.this, Preencher_Seu_Servico.class);
        intent.putExtra("posicao",position);
        startActivity(intent);
        finish();
    }

}