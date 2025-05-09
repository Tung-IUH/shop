package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HoaDonActivity extends Activity {

    TextView txtTenKhach, txtDiaChi, txtTenSP, txtGia, txtSoLuong, txtTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoadon);

        // Ánh xạ
        txtTenKhach = findViewById(R.id.txtTenKhach);
        txtDiaChi = findViewById(R.id.txtDiaChi);
        txtTenSP = findViewById(R.id.txtTenSP);
        txtGia = findViewById(R.id.txtGia);
        txtTongTien = findViewById(R.id.txtTongTien);

        // Nhận dữ liệu từ Intent
        String tenKhach = getIntent().getStringExtra("ten_khachhang");
        String diaChi = getIntent().getStringExtra("dia_chi");
        int soLuong = getIntent().getIntExtra("so_luong", 1);
        SanPham sanPham = (SanPham) getIntent().getSerializableExtra("sanpham");

        double tongTien = sanPham.getGia() * soLuong;

        // Hiển thị lên giao diện
        txtTenKhach.setText("Tên khách hàng: " + tenKhach);
        txtDiaChi.setText("Địa chỉ: " + diaChi);
        txtTenSP.setText("Sản phẩm: " + sanPham.getTen());
        txtGia.setText("Giá: " + sanPham.getGia() + " đ");
        txtSoLuong.setText("Số lượng: " + soLuong);
        txtTongTien.setText("Tổng tiền: " + tongTien + " đ");
    }
}
