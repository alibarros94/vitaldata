package com.example.goodjoob.webservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.goodjoob.ui.ProjetoBradescoMainActivity;
import com.example.goodjoob.util.Constantes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.support.v4.content.ContextCompat.startActivity;

public class HttpRequest {

    private RequestQueue requestQueue;

    private StringRequest request;



//    public void conexao(final HashMap<String, String> hashMap, final Context context, final Activity activity){
//
//        requestQueue = Volley.newRequestQueue(context);
//
//        request = new StringRequest(Request.Method.POST, Constantes.CADASTRAR, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//
//                    if (jsonObject.names().get(0).equals("success")) {
//                        Toast.makeText(context, "ATENÇÃO: Conta cadastrada com sucesso ", Toast.LENGTH_SHORT).show();
//                        context.startActivity(new Intent(context, activity.class));
//                        finish();
//                    } else {
//                        Toast.makeText(context, "ATENÇÃO: " + jsonObject.getString("error"), Toast.LENGTH_SHORT).show();
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Toast.makeText(context,  e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                return hashMap;
//            }
//        };
//        requestQueue.add(request);
//
//    }

}
