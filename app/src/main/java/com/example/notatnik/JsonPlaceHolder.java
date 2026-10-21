package com.example.notatnik;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder {
    @GET("db.json")
    Call<List<Notatki>> getNotatki();
}
