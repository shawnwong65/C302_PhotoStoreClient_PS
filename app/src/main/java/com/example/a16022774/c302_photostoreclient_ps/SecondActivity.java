package com.example.a16022774.c302_photostoreclient_ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    PhotoAdapter ca;
    ArrayList<Photo> photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        photos = new ArrayList<Photo>();
    }

    @Override
    protected void onResume() {
        super.onResume();

        lv = (ListView) findViewById(R.id.lv);
        ca = new PhotoAdapter(SecondActivity.this, R.layout.photo_row, photos);
        lv.setAdapter(ca);

        Intent i = getIntent();
        int id = i.getIntExtra("id", 0);

        // Code for step 1 start
        HttpRequest request = new HttpRequest
                ("http://10.0.2.2/C302_P06_PhotoStoreWS/getPhotoStoreByCategory.php?category_id=" + id);
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

                            String title = jsonObj.getString("title");
                            String description = jsonObj.getString("description");
                            String creator = jsonObj.getString("created_by");
                            String image = jsonObj.getString("image");

                            Photo photo = new Photo(title, description, creator, image);
                            photos.add(photo);
                        }
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }

                    ca.notifyDataSetChanged();
                }
            };
    // Code for step 2 end
}
