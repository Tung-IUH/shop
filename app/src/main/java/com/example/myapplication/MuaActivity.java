package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MuaActivity extends Activity {

    EditText edtTen, edtSdt, edtDiachi;
    Button btnGoiHoaDon;
    SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mua);

        edtTen = findViewById(R.id.edtTen);
        edtSdt = findViewById(R.id.edtSdt);
        edtDiachi = findViewById(R.id.edtDiachi);
        btnGoiHoaDon = findViewById(R.id.btnGoiHoaDon);

        // Nhận dữ liệu sản phẩm từ Intent
        sanPham = (SanPham) getIntent().getSerializableExtra("sanpham");

        btnGoiHoaDon.setOnClickListener(v -> {
            String ten = edtTen.getText().toString().trim();
            String sdt = edtSdt.getText().toString().trim();
            String diachi = edtDiachi.getText().toString().trim();

            if (ten.isEmpty() || sdt.isEmpty() || diachi.isEmpty()) {
                Toast.makeText(MuaActivity.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            } else {
                // Lấy thời gian hiện tại
                String thoigian = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // Ghi hóa đơn vào SQLite
                DBHelper dbHelper = new DBHelper(MuaActivity.this);
                dbHelper.insertHoaDon(ten, sdt, diachi, sanPham.getId(), thoigian);

                Toast.makeText(MuaActivity.this, "Đơn hàng đã được ghi nhận!", Toast.LENGTH_SHORT).show();

                // Mở giao diện hóa đơn sau khi đặt hàng
                Intent intent = new Intent(MuaActivity.this, HoaDonActivity.class);
                intent.putExtra("ten_khachhang", ten);
                intent.putExtra("sdt", sdt);
                intent.putExtra("dia_chi", diachi);
                intent.putExtra("thoigian", thoigian);
                intent.putExtra("sanpham", sanPham);

                startActivity(intent);
//                finish();  // Đóng activity mua sau khi chuyển
            }
        });
    }
}
