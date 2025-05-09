package com.example.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    // Lấy danh sách sản phẩm từ API Flask
    @GET("products")
    Call<List<SanPham>> getAllProducts();

}