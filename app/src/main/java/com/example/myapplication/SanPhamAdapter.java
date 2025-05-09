package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    private List<SanPham> list;

    public SanPhamAdapter(Context context, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_sanpham, parent, false);
        }

        // Lấy đối tượng SanPham từ danh sách
        SanPham sp = list.get(position);

        // Ánh xạ các thành phần giao diện
        ImageView img = convertView.findViewById(R.id.imgSanPham);
        TextView txtTen = convertView.findViewById(R.id.txtTen);
        TextView txtGia = convertView.findViewById(R.id.txtGia);
        Button btnChiTiet = convertView.findViewById(R.id.btnChiTiet);
        Button btnMua = convertView.findViewById(R.id.btnMua);

        // Cập nhật thông tin sản phẩm lên giao diện
        txtTen.setText(sp.getTen());
        txtGia.setText(sp.getGia() + " đ");

        // Lấy tên hình ảnh từ cơ sở dữ liệu
        String imageName = sp.getHinhanh();  // Ví dụ: "a.jpg", "b.png", ...

        // Dùng getResources().getIdentifier() để lấy tài nguyên hình ảnh từ thư mục drawable
        int imageResource = context.getResources().getIdentifier(imageName.split("\\.")[0], "drawable", context.getPackageName());

        // Kiểm tra xem tài nguyên có hợp lệ không
        if (imageResource != 0) {
            img.setImageResource(imageResource);
        } else {
            // Nếu không tìm thấy hình ảnh tương ứng, có thể dùng hình ảnh mặc định
            img.setImageResource(R.drawable.ic_launcher_background);  // Hình ảnh mặc định
        }

        // Xử lý nút "Chi tiết"
        btnChiTiet.setOnClickListener(v -> {
            // Mở Activity chi tiết sản phẩm
            Intent intent = new Intent(context, ChiTietActivity.class);
            intent.putExtra("sanpham", sp);
            context.startActivity(intent);
        });

        // Xử lý nút "Mua"
        btnMua.setOnClickListener(v -> {
            // Mở Activity mua sản phẩm
            Intent intent = new Intent(context, MuaActivity.class);
            intent.putExtra("sanpham", sp);
            context.startActivity(intent);
        });

        return convertView;
    }
}
