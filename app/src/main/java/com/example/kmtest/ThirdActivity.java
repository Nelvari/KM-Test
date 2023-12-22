package com.example.kmtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.kmtest.APIClient.APIClient;
import com.example.kmtest.Adapter.ListAdapter;
import com.example.kmtest.Model.ListModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<ListModel> listModels;
    ListAdapter listAdapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Third Screen");
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "Selected User Name";
                Intent intent = new Intent();
                intent.putExtra("name", message);
                setResult(1,intent);
                finish();
            }
        });

        recyclerView = findViewById(R.id.rvList);

        progressDialog = new ProgressDialog(ThirdActivity.this);
        progressDialog.setTitle("Process");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();
        progressDialog.setCancelable(false);

        getList();

    }

    private void getList() {

        listModels = new ArrayList<>();
        AndroidNetworking.get(APIClient.BASE_URL)
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray data = response.getJSONArray("data");
                            Log.e("test", "onResponse: " + data );
                            for (int i = 0; i < data.length(); i++) {

                                ListModel modelList = new ListModel();
                                JSONObject object = data.getJSONObject(i);

                                modelList.setId(object.getInt("id"));
                                modelList.setEmail(object.getString("email"));
                                modelList.setFirst_name(object.getString("first_name"));
                                modelList.setLast_name(object.getString("last_name"));
                                modelList.setAvatar(object.getString("avatar"));
                                listModels.add(modelList);

                            }

                            progressDialog.dismiss();

                            ListAdapter adapter = new ListAdapter(listModels, ThirdActivity.this);

                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ThirdActivity.this);
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        progressDialog.dismiss();
                        if (anError.getErrorCode() != 0) {
                            // received error from server
                            // error.getErrorCode() - the error code from server
                            // error.getErrorBody() - the error body from server
                            // error.getErrorDetail() - just an error detail
                            Log.d("testing", "onError errorCode : " + anError.getErrorCode());
                            Log.d("testing", "onError errorBody : " + anError.getErrorBody());
                            Log.d("testing", "onError errorDetail : " + anError.getErrorDetail());
                            // get parsed error object (If ApiError is your class)
                        } else {
                            // error.getErrorDetail() : connectionError, parseError, requestCancelledError
                            Log.d("testing", "onError errorDetail : " + anError.getErrorDetail());
                        }
                    }
                });

    }

}