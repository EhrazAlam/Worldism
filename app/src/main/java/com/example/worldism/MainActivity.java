package com.example.worldism;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ClickInterface {

    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    LinearLayoutManager layoutManager;
    ArrayList<Data> arrayList = new ArrayList<>();

    String json_url = "https://restcountries.eu/rest/v2/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);


        JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, json_url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            Log.e("Response", response.toString());
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                String cname = object.getString("name");
                                String cimage = object.getString("flag");
                                String ccapital = object.getString("capital");
                                String ccontinent = object.getString("region");
                                String carea = object.getString("area");
                                String cpopulation = object.getString("population");
                                JSONArray currencies = object.getJSONArray("currencies");
                                JSONArray languages = object.getJSONArray("languages");
                                ArrayList<String> ccurrency = new ArrayList<>();
                                for (int j = 0; j < currencies.length(); j++) {
                                    JSONObject subobject1 = currencies.getJSONObject(j);
                                    ccurrency.add(subobject1.getString("name"));
                                }

                                ArrayList<String> clanguage = new ArrayList<>();
                                for (int k = 0; k < languages.length(); k++) {
                                    JSONObject subobject1 = languages.getJSONObject(k);
                                    clanguage.add(subobject1.getString("name"));
                                }

                                Data data = new Data(cname, cimage, ccapital, ccontinent, carea, cpopulation, ccurrency, clanguage);
                                arrayList.add(data);
                            }
                            populateData();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        MySingleTon.getInstance().addToRequestQueue(stringRequest);
    }

    private void populateData() {
        try {
            if (arrayList.size() > 0) {
                adapter = new RecyclerAdapter(arrayList, MainActivity.this, MainActivity.this);
                recyclerView.setAdapter(adapter);
                layoutManager = new LinearLayoutManager(MainActivity.this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setHasFixedSize(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void click(int position) {
        try {
            Data slectedData=arrayList.get(position);
            Intent intent = new Intent(MainActivity.this, FinalActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("data", slectedData);
            startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }
}
