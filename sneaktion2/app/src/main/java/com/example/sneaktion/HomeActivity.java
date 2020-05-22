package com.example.sneaktion;

import android.graphics.ColorSpace;
import android.icu.math.BigDecimal;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private String Url=" https://jikan1.p.rapidapi.com/top/anime/1/airing";
    private ListView listView;
    ArrayList<DataHome> modelDataArrayList;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        listView = findViewById(R.id.lv1);



        retrieveJSON();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

    }


    private void retrieveJSON(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, Url,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onResponse(String response) {
                        Log.d("strrrr", ">>" + response);

                        try {
                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("request_cached").equals("false")) {

                                modelDataArrayList = new ArrayList<>();
                                JSONArray dataArray = obj.getJSONArray("top");

                                for (int i = 0; i < dataArray.length(); i++) {


                                    DataHome playerModel = new DataHome();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

                                    playerModel.setRank(dataobj.getInt("rank"));
                                    playerModel.setTitle(dataobj.getString("title"));
                                    playerModel.setImage_url(dataobj.getString("image_url"));
                                    playerModel.setScore(BigDecimal.valueOf(dataobj.getDouble("score")).toBigDecimal());

                                    modelDataArrayList.add(playerModel);

                                }
                                setupListView();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("JEMBOD" , "error" + error.getMessage());
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();

                headers.put("x-rapidapi-host","jikan1.p.rapidapi.com");
                headers.put("x-rapidapi-key","e7882f0380msh7e7b24b5e86f007p10582cjsne88a5cb34826");
                return headers;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void setupListView(){

        listAdapter = new ListAdapter(this, modelDataArrayList);
        listView.setAdapter(listAdapter);

    }

}
