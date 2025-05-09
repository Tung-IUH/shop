package com.example.myapplication;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.ContentValues;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "myshop.db";
    public static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sanpham (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "gia REAL," +
                "hinhanh TEXT," +
                "mota TEXT)");

        db.execSQL("CREATE TABLE khachhang (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "ten TEXT," +
                "sdt TEXT," +
                "diachi TEXT)");

        db.execSQL("CREATE TABLE hoadon (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_khachhang INTEGER," +
                "id_sanpham INTEGER," +
                "thoigian TEXT," +
                "FOREIGN KEY(id_khachhang) REFERENCES khachhang(id)," +
                "FOREIGN KEY(id_sanpham) REFERENCES sanpham(id))");

        // Dữ liệu mẫu
        insertSampleProducts(db);
    }

    private void insertSampleProducts(SQLiteDatabase db) {
        insertProduct(db, "Máy ảnh Canon", 5000000, "a", "Máy ảnh Canon 3000D");
        insertProduct(db, "Máy ảnh Canon", 40000000, "b", "Máy ảnh Canon RS");
        insertProduct(db, "Máy ảnh Instax", 5500000, "c", "Máy ảnh Instax chụp lấy liền");
        insertProduct(db, "Máy ảnh Nikon", 7000000, "d", "Máy ảnh Nikon Z");
        insertProduct(db, "Máy ảnh Sony", 40000000, "e", "Máy ảnh Sony A6500");
    }

    private void insertProduct(SQLiteDatabase db, String ten, double gia, String hinhanh, String mota) {
        ContentValues values = new ContentValues();
        values.put("ten", ten);
        values.put("gia", gia);
        values.put("hinhanh", hinhanh);
        values.put("mota", mota);
        db.insert("sanpham", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        db.execSQL("DROP TABLE IF EXISTS sanpham");
        db.execSQL("DROP TABLE IF EXISTS khachhang");
        db.execSQL("DROP TABLE IF EXISTS hoadon");
        onCreate(db);
    }

    // Hàm lấy toàn bộ sản phẩm
    public Cursor getAllSanPham() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM sanpham", null);
    }

    // Hàm thêm hóa đơn
    public void insertHoaDon(String ten, String sdt, String diachi, int idSanPham, String thoigian) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues kh = new ContentValues();
        kh.put("ten", ten);
        kh.put("sdt", sdt);
        kh.put("diachi", diachi);
        long khachHangId = db.insert("khachhang", null, kh);

        ContentValues hd = new ContentValues();
        hd.put("id_khachhang", khachHangId);
        hd.put("id_sanpham", idSanPham);
        hd.put("thoigian", thoigian);
        db.insert("hoadon", null, hd);
    }
}
