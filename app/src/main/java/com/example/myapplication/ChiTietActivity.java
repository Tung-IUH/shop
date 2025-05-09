package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ChiTietActivity extends Activity {

    TextView txtTen, txtGia, txtMoTa;
    ImageView imgSanPham;
    Button btnMua;
    SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);

        txtTen = findViewById(R.id.txtTen);
        txtGia = findViewById(R.id.txtGia);
        txtMoTa = findViewById(R.id.txtMoTa);
        imgSanPham = findViewById(R.id.imgSanPham);
        btnMua = findViewById(R.id.btnMua);

        // Nhận dữ liệu sản phẩm từ Intent
        Intent intent = getIntent();
        sanPham = (SanPham) intent.getSerializableExtra("sanpham");

        // Hiển thị thông tin chi tiết sản phẩm
        txtTen.setText(sanPham.getTen());
        txtGia.setText(sanPham.getGia() + " đ");
        txtMoTa.setText(sanPham.getMota());

        // Lấy tên hình ảnh từ đối tượng SanPham
        String imageName = sanPham.getHinhanh();

        if (imageName.contains(".")) {
            imageName = imageName.substring(0, imageName.indexOf("."));
        }

        int imageResource = getResources().getIdentifier(imageName, "drawable", getPackageName());

        if (imageResource != 0) {
            imgSanPham.setImageResource(imageResource);
        } else {
            imgSanPham.setImageResource(R.drawable.ic_launcher_background);
        }


        // Khi người dùng nhấn "Mua"
        btnMua.setOnClickListener(v -> {
            // Mở Activity Mua hàng
            Intent muaIntent = new Intent(ChiTietActivity.this, MuaActivity.class);
            muaIntent.putExtra("sanpham", sanPham);
            startActivity(muaIntent);
        });
    }
}
