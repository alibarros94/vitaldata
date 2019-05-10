package com.example.goodjoob.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.goodjoob.R;
import com.example.goodjoob.TecnicoPerfil;
import com.example.goodjoob.db.TecnicoAdapter;
import com.example.goodjoob.model.Handle;
import com.example.goodjoob.model.Tecnico;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListarMainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    //conexao
    ListView listView;
    List<Tecnico> tecnicoList;
    private ArrayAdapter<Tecnico> adapter;

    private RequestQueue requestQueue;
    private static final String URL = "http://conect.vitaldata.com.br/tentativa5/listarusuarios2.php";
    //private StringRequest request;

    //conexao



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_main);
        listView = (ListView) findViewById(R.id.listaTecnicos);
        tecnicoList = new ArrayList<>();
        showList();
    }

    private void showList()
    {
        //requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("ListaTecnicos");
                    for (int i = 0; i <array.length(); i++){
                        JSONObject provObj = array.getJSONObject(i);
                        Tecnico t = new Tecnico(provObj.getString("id_usuario"),provObj.getString("nome"),provObj.getString("email"),provObj.getString("telefone"),provObj.getInt("qtd_avaliacao"),provObj.getDouble("avaliacao"));
                        tecnicoList.add(t);
                    }
                    adapter = new TecnicoAdapter(tecnicoList,getApplication());
                    listView.setAdapter(adapter);
                    listView.setOnItemClickListener(ListarMainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){

        };
        //requestQueue.add(request);
        Handle.getInstance(getApplicationContext()).addToRequestQue(stringRequest);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(ListarMainActivity.this, TecnicoPerfil.class);
        intent.putExtra("posicao",position);
        startActivity(intent);
    }
}