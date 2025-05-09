package com.example.myapplication;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ListView listView;
    DBHelper dbHelper;
    List<SanPham> list = new ArrayList<>();
    SanPhamAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listViewSanPham);
        dbHelper = new DBHelper(this);

        // Lấy toàn bộ sản phẩm từ cơ sở dữ liệu
        Cursor cursor = dbHelper.getAllSanPham();
        while (cursor.moveToNext()) {
            list.add(new SanPham(
                    cursor.getInt(0), // ID
                    cursor.getString(1), // Tên sản phẩm
                    cursor.getDouble(2), // Giá
                    cursor.getString(3), // Hình ảnh
                    cursor.getString(4)  // Mô tả
            ));
        }

        // Thiết lập adapter cho ListView
        adapter = new SanPhamAdapter(this, list);
        listView.setAdapter(adapter);

        // Sự kiện click vào một sản phẩm
        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Lấy sản phẩm chọn
            SanPham sanPham = list.get(position);
            // Mở Activity chi tiết sản phẩm
            Intent intent = new Intent(MainActivity.this, ChiTietActivity.class);
            intent.putExtra("sanpham", sanPham); // Truyền thông tin sản phẩm
            startActivity(intent);
        });
    }
}

