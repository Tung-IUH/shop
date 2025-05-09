from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///myshop.db'  # Cấu hình cơ sở dữ liệu
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

# Mô hình bảng sản phẩm
class SanPham(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    ten = db.Column(db.String(100), nullable=False)
    gia = db.Column(db.Float, nullable=False)
    hinhanh = db.Column(db.String(100))
    mota = db.Column(db.String(200))

# Mô hình bảng khách hàng
class KhachHang(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    ten = db.Column(db.String(100), nullable=False)
    sdt = db.Column(db.String(15), nullable=False)
    diachi = db.Column(db.String(200))

# Mô hình bảng hóa đơn
class HoaDon(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    id_khachhang = db.Column(db.Integer, db.ForeignKey('khach_hang.id'))
    id_sanpham = db.Column(db.Integer, db.ForeignKey('san_pham.id'))
    thoigian = db.Column(db.String(50))

# Tạo cơ sở dữ liệu
@app.before_request
def create_tables():
    db.create_all()

# API: Lấy tất cả sản phẩm
@app.route('/api/sanpham', methods=['GET'])
def get_sanpham():
    products = SanPham.query.all()
    product_list = []
    for product in products:
        product_list.append({
            'id': product.id,
            'ten': product.ten,
            'gia': product.gia,
            'hinhanh': product.hinhanh,
            'mota': product.mota
        })
    return jsonify(product_list)

# API: Thêm sản phẩm mới
@app.route('/api/sanpham', methods=['POST'])
def add_sanpham():
    data = request.get_json()
    new_product = SanPham(
        ten=data['ten'],
        gia=data['gia'],
        hinhanh=data['hinhanh'],
        mota=data['mota']
    )
    db.session.add(new_product)
    db.session.commit()
    return jsonify({'message': 'Sản phẩm đã được thêm thành công!'}), 201

# API: Thêm hóa đơn
@app.route('/api/hoadon', methods=['POST'])
def add_hoadon():
    data = request.get_json()
    new_hoadon = HoaDon(
        id_khachhang=data['id_khachhang'],
        id_sanpham=data['id_sanpham'],
        thoigian=data['thoigian']
    )
    db.session.add(new_hoadon)
    db.session.commit()
    return jsonify({'message': 'Hóa đơn đã được tạo thành công!'}), 201

if __name__ == '__main__':
    app.run(debug=True)
