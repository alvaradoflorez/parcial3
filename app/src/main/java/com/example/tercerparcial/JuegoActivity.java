package com.example.tercerparcial;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class JuegoActivity extends AppCompatActivity {

    int poke1 = 100;
    int poke2 = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);


        final ImageView pokem1 = (ImageView) findViewById(R.id.pokem1);
        final ImageView pokem2 = (ImageView) findViewById(R.id.pokem2);
        final TextView name1 = (TextView) findViewById(R.id.namePokem1);
        final TextView name2 = (TextView) findViewById(R.id.namePokem2);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress1);
        final ProgressBar progressBar1 = (ProgressBar) findViewById(R.id.progress2);

        String num = Integer.toString((int) (Math.random() * 30) + 1);
        String num2 = Integer.toString((int) (Math.random() * 20) + 1);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://pokeapi.co/api/v2/pokemon/" + num, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Picasso.with(JuegoActivity.this).load(jsonObject.getJSONObject("sprites").getString("front_shiny")).into(pokem1);
                    name1.setText(jsonObject.getString("name").toUpperCase());


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(stringRequest);


        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, "http://pokeapi.co/api/v2/pokemon/" + num2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);

                    Picasso.with(JuegoActivity.this).load(jsonObject.getJSONObject("sprites").getString("back_shiny")).into(pokem2);
                    name2.setText(jsonObject.getString("name").toUpperCase());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(stringRequest2);

        pokem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                int desc1 = (int) (Math.random() * 10) + 1;
                int desc2 = (int) (Math.random() * 10) + 1;
                poke1 = poke1 - desc1;
                poke2 = poke2 - desc2;
                progressBar.setProgress(poke1);
                progressBar1.setProgress(poke2);
            }
        });
    }
}
