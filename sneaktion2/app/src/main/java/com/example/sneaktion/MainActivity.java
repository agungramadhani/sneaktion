package com.example.sneaktion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    String url= "http://192.168.100.6/kelompok-2-TIF-Inter/sneaktion/android.php";
    TextInputEditText username;
    TextInputEditText password;
    Button btnlogin;
    String User,Pw;
    Boolean cekinput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.txtuser);
        password = findViewById(R.id.txtpw);
        btnlogin = findViewById(R.id.buttonlogin);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cekEditText();
                if(cekinput) {
                    ceklogin();
                }else {
                    Toast.makeText(MainActivity.this, "Harap Isi Kedua Text Box!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void ceklogin(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String ServerResponse) {
                        if (ServerResponse.equalsIgnoreCase("ada")) {
                            Toast.makeText(MainActivity.this, "Selamat Datang", Toast.LENGTH_LONG).show();
                            finish();
                            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                            intent.putExtra("username", User);
                            startActivity(intent);
                        } else {
                            Toast.makeText(MainActivity.this, ServerResponse, Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String>  params = new HashMap<>();
                params.put("username", User);
                params.put("pass",Pw);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(stringRequest);
    }

        public void cekEditText(){
            User =username.getText().toString().trim();
            Pw = password.getText().toString().trim();

            if(TextUtils.isEmpty(User) || TextUtils.isEmpty(Pw)){
                cekinput = false;
            }else {
                cekinput = true;
            }
        }
    }
