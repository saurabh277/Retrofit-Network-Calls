package com.example.retrofitnetworkcalls;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ListView lvName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvName=findViewById(R.id.lvName);
        Retrofit r=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api=r.create(Api.class);
        Call<UserResponse> user = api.getUsers();
        user.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
            UserResponse res=response.body();
            String[] heroes = new String[res.getData().size()];
            for(int i=0;i<res.getData().size();i++){
                heroes[i]=res.getData().get(i).getFirst_name();
            }
            lvName.setAdapter(
                    new ArrayAdapter<String>(
                            getApplicationContext(),
                            android.R.layout.simple_list_item_1,
                            heroes
                    )
            );
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
