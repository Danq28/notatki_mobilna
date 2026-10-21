package com.example.notatnik;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> zbiorTekstow;
    private ArrayAdapter<String> adapter;
    private ListView mojaLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        zbiorTekstow = new ArrayList<>();
        mojaLista = findViewById(R.id.listView);

        ViewCompat.setOnApplyWindowInsetsListener(mojaLista, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, zbiorTekstow);
        mojaLista.setAdapter(adapter);

        Retrofit klientRetrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Danq28/notatki_db/main/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolder apiInterfejs = klientRetrofit.create(JsonPlaceHolder.class);

        Call<List<Notatki>> zapytanieSieciowe = apiInterfejs.getNotatki();

        zapytanieSieciowe.enqueue(new Callback<List<Notatki>>() {
            @Override
            public void onResponse(Call<List<Notatki>> call, Response<List<Notatki>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    zbiorTekstow.clear();
                    for (Notatki elem : response.body()) {
                        String wynik = "Tytuł: " + elem.getTytul() + "\nTreść: " + elem.getTresc() + "\nData: " + elem.getData();
                        zbiorTekstow.add(wynik);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Notatki>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "nie działa", Toast.LENGTH_SHORT).show();
            }
        });
    }
}