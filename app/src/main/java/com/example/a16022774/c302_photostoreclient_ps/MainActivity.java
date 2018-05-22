package com.example.a16022774.c302_photostoreclient_ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    CategoryAdapter ca;
    ArrayList<Category> categories;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.lv);
        categories = new ArrayList<Category>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        categories.clear();
        lv = (ListView) findViewById(R.id.lv);
        ca = new CategoryAdapter(MainActivity.this, R.layout.row, categories);
        lv.setAdapter(ca);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getCategories.php");
        request.setOnHttpResponseListener(mHttpResponseListener);
        request.setMethod("GET");
        request.execute();
        // Code for step 1 end


    }

    // Code for step 2 start
    private HttpRequest.OnHttpResponseListener mHttpResponseListener =
            new HttpRequest.OnHttpResponseListener() {
                @Override
                public void onResponse(String response){

                    // process response here
                    try {
                        JSONArray jsonArray = new JSONArray(response);

                        for (int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObj = jsonArray.getJSONObject(i);

                            int categoryId = jsonObj.getInt("category_id");
                            String categoryName = jsonObj.getString("name");
                            String description = jsonObj.getString("description");
                            Category category = new Category(categoryId, categoryName, description);
                            categories.add(category);
                        }

                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int
                                    position, long identity) {
                                Intent i = new Intent(MainActivity.this,
                                        SecondActivity.class);
                                Category data = categories.get(position);
                                int id = data.getId();

                                i.putExtra("id", id);
                                Log.i("id", id + "");
                                startActivity(i);
                            }
                        });
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    ca.notifyDataSetChanged();
                }
            };
    // Code for step 2 end
}
