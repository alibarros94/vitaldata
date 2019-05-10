    package com.example.goodjoob.ui;

    import android.content.Intent;
    import android.net.Uri;
    import android.os.Bundle;
    import android.support.v7.app.AppCompatActivity;
    import android.view.View;
    import android.widget.Button;
    import android.widget.EditText;
    import android.widget.ProgressBar;
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
    import com.example.goodjoob.model.Perfil;
    import com.example.goodjoob.model.Tecnico;
    import com.example.goodjoob.util.Constantes;

    import org.json.JSONArray;
    import org.json.JSONException;
    import org.json.JSONObject;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

    public class ProjetoBradescoMainActivity extends AppCompatActivity {

        private EditText login,senha;
        List<Tecnico> perfilMenu;
        private String perfil;
        private RequestQueue requestQueue;
       // private static final String URL = "http://conect.vitaldata.com.br/tentativa5/user_control.php";
        private static final String URLemail = "" ;
        private StringRequest request;
        private Button meuBotaoCadastrar;
        private Button meuBotaoEsqu;
        private Button meuBotaoEntrar;
        private ProgressBar progress;



        @Override
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_projeto_bradesco_main);


            meuBotaoEntrar = (Button) findViewById(R.id.buttonEntrar);
            meuBotaoCadastrar = (Button) findViewById(R.id.buttonCadastrar);
            login = (EditText) findViewById(R.id.editTextLogin);
            senha = (EditText) findViewById(R.id.editTextSenha);
            requestQueue = Volley.newRequestQueue(this);
            progress = (ProgressBar) findViewById(R.id.progressBar);
            perfilMenu = new ArrayList<>();
            meuBotaoEsqu = (Button) findViewById(R.id.buttonEsqu);




            meuBotaoEntrar.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    //--------------------------------------------------------------------------------------------conexao
                    request = new StringRequest(Request.Method.POST, Constantes.LOGAR, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progress.setVisibility(View.GONE);
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray array = jsonObject.getJSONArray("success");

                                for (int i = 0; i <array.length(); i++){
                                    JSONObject provObj = array.getJSONObject(i);
                                    Perfil p = new Perfil(provObj.getString("id_usuario"),provObj.getString("nome"),provObj.getString("login"),provObj.getString("email"),provObj.getString("telefone"),provObj.getString("cpf"),provObj.getString("tipo"));
                                }

                                if (jsonObject.names().get(0).equals("success")){
                                    Toast.makeText(getApplicationContext(),"ATENÇÃO: Seja bem vindo ",Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), TelaCheckMainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "ATENÇÃO: " +jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),  e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(),  "Verifique sua conexão com a internet", Toast.LENGTH_SHORT).show();
                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap = new HashMap<String, String>();
                            hashMap.put("login",login.getText().toString());
                            hashMap.put("senha",senha.getText().toString());
                            return hashMap;
                        }
                    };
                    progress.setVisibility(View.VISIBLE);
                    requestQueue.add(request);
                    //--------------------------------------------------------------------------------------------conexao
                }
            });
            meuBotaoCadastrar.setOnClickListener(new View.OnClickListener() {



                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(ProjetoBradescoMainActivity.this, CadastrarUsuario.class);
                    startActivity(intent);

                }
            });
            meuBotaoEsqu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://conect.vitaldata.com.br/recuperar-senha-com-php/?pagina=recuperar"));
                    startActivity(intent);
                }
            });


        }
    }

