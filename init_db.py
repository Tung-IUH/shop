import sqlite3
import os

DB_NAME = 'myshop.db'

def init_db():
    # Xóa DB cũ nếu cần
    if os.path.exists(DB_NAME):
        os.remove(DB_NAME)

    conn = sqlite3.connect(DB_NAME)
    cursor = conn.cursor()

    # Tạo bảng sanpham
    cursor.execute('''
    CREATE TABLE sanpham (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        ten TEXT,
        gia REAL,
        hinhanh TEXT,
        mota TEXT
    )
    ''')

    # Tạo bảng khachhang
    cursor.execute('''
    CREATE TABLE khachhang (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        ten TEXT,
        sdt TEXT,
        diachi TEXT
    )
    ''')

    # Tạo bảng hoadon
    cursor.execute('''
    CREATE TABLE hoadon (
        id INTEGER PRIMARY KEY AUTOINCREMENT,
        id_khachhang INTEGER,
        id_sanpham INTEGER,
        thoigian TEXT,
        FOREIGN KEY(id_khachhang) REFERENCES khachhang(id),
        FOREIGN KEY(id_sanpham) REFERENCES sanpham(id)
    )
    ''')

    # Dữ liệu mẫu cho bảng sanpham
    products = [
        ("Điện thoại A", 3500000, "a.jpg", "Điện thoại pin trâu, màn hình 6.5 inch"),
        ("Laptop B", 15000000, "b.jpg", "Laptop văn phòng, RAM 16GB"),
        ("Tai nghe C", 550000, "c.jpg", "Tai nghe bluetooth, pin 12h"),
        ("Chuột D", 250000, "d.jpg", "Chuột không dây, DPI cao"),
        ("Bàn phím E", 500000, "e.jpg", "Bàn phím cơ RGB")
    ]

    cursor.executemany('''
    INSERT INTO sanpham (ten, gia, hinhanh, mota)
    VALUES (?, ?, ?, ?)
    ''', products)

    conn.commit()
    conn.close()
    print("✅ Đã tạo database và dữ liệu mẫu.")

if __name__ == '__main__':
    init_db()
