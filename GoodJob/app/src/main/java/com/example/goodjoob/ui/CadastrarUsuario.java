package com.example.goodjoob.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjoob.R;
import com.example.goodjoob.model.Perfil;
import com.example.goodjoob.util.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CadastrarUsuario extends AppCompatActivity {


    //conexao

    private RequestQueue requestQueue;
    //private static final String URL = "http://conect.vitaldata.com.br/tentativa5/cadaster.php";
    private StringRequest request;

    //conexao

    private Button Cadastrar;
    private EditText txtNome;
    private EditText txtEmail;
    private EditText txtCpf;
    private EditText txtTelefone;
    String txtTipo;
    private EditText txtLogin;
    private EditText txtSenha;
    private EditText txtConfSenha;

    Bundle bundleExtra;
    int posicaoExtra;
    private HashMap<String, String> hashMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_usuario);

        Cadastrar = (Button) findViewById(R.id.buttonCadastrarBD);
        txtNome = (EditText) findViewById(R.id.editTextCNome);
        txtEmail = (EditText) findViewById(R.id.editTextCEmail);
        txtCpf = (EditText) findViewById(R.id.editTextCCpf);
        txtTelefone = (EditText) findViewById(R.id.editTextCTelefone);

        //txtTipo = (RadioGroup) findViewById(R.id.radiogroup_map1);
        txtLogin = (EditText) findViewById(R.id.editTextCLogin);
        txtSenha = (EditText) findViewById(R.id.editTextCSenha);
        txtConfSenha = (EditText) findViewById(R.id.editTextCSenha2);
        bundleExtra = getIntent().getExtras();

        //conexao
        requestQueue = Volley.newRequestQueue(this);
        //conexao

        Cadastrar.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {




                String senhaDigitada = txtSenha.getText().toString();
                String confSenhaDigitada = txtConfSenha.getText().toString();

                if (senhaDigitada.equals(confSenhaDigitada)) {
                    RadioGroup radiogroup_map1 = (RadioGroup) findViewById(R.id.radiogroup_map1);
                    switch (radiogroup_map1.getCheckedRadioButtonId()) {
                        case R.id.radioButton1:
                            txtTipo = "Cliente";
                            break;
                        case R.id.radioButton2:
                            txtTipo = "Tecnico";
                            break;
                    }
                    //--------------------------------------------------------------------------------------------conexao
                    request = new StringRequest(Request.Method.POST, Constantes.CADASTRAR, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);

                                if (jsonObject.names().get(0).equals("success")) {
                                    Toast.makeText(getApplicationContext(), "ATENÇÃO: Conta cadastrada com sucesso ", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), ProjetoBradescoMainActivity.class));
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
                            hashMap.put("nome", txtNome.getText().toString());
                            hashMap.put("login", txtLogin.getText().toString());
                            hashMap.put("senha", txtSenha.getText().toString());
                            hashMap.put("telefone", txtTelefone.getText().toString());
                            hashMap.put("cpf", txtCpf.getText().toString());
                            hashMap.put("email", txtEmail.getText().toString());
                            hashMap.put("tipo", txtTipo);

                            return hashMap;
                        }
                    };
                    requestQueue.add(request);

                } else {
                    AlertDialog alertDialog = new AlertDialog.Builder(CadastrarUsuario.this).create();
                    alertDialog.setTitle("Alerta");
                    alertDialog.setMessage("Senha não corresponde");
                    alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                }
            }
        });



    }

}


