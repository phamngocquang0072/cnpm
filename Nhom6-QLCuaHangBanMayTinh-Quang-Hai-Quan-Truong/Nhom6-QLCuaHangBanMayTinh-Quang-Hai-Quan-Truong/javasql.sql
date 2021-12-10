-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 17, 2021 at 09:41 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `javasql`
--

-- --------------------------------------------------------

--
-- Table structure for table `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `idhoadon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idsanpham` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` int(11) NOT NULL,
  `dongia` double NOT NULL,
  `thanhtien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`idhoadon`, `idsanpham`, `soluong`, `dongia`, `thanhtien`) VALUES
('HD1', 'SP17', 1, 851600, 75001600),
('HD1', 'SP4', 1, 39890000, 75001600),
('HD1', 'SP24', 1, 1790000, 75001600),
('HD1', 'SP23', 1, 5880000, 75001600),
('HD1', 'SP7', 1, 26590000, 75001600),
('HD2', 'SP21', 1, 14590000, 158977750),
('HD2', 'SP5', 1, 25990000, 158977750),
('HD2', 'SP25', 7, 5989000, 158977750),
('HD2', 'SP27', 1, 44890000, 158977750),
('HD2', 'SP18', 2, 19976000, 158977750);

-- --------------------------------------------------------

--
-- Table structure for table `chitietphieunhap`
--

CREATE TABLE `chitietphieunhap` (
  `idphieunhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idsanpham` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `soluong` int(10) UNSIGNED NOT NULL,
  `dongia` double UNSIGNED NOT NULL,
  `thanhtien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `chitietphieunhap`
--

INSERT INTO `chitietphieunhap` (`idphieunhap`, `idsanpham`, `soluong`, `dongia`, `thanhtien`) VALUES
('HD3', 'SP18', 1, 19976000, 449215000),
('HD3', 'SP22', 1, 4489000, 449215000),
('HD3', 'SP9', 25, 16990000, 449215000),
('PN2', 'SP11', 12, 25990000, 2770055000),
('PN2', 'SP14', 13, 62499000, 2770055000),
('PN2', 'SP26', 19, 41890000, 2770055000),
('PN2', 'SP3', 19, 41890000, 2770055000),
('PN2', 'SP22', 12, 4489000, 2770055000);

-- --------------------------------------------------------

--
-- Table structure for table `hoadon`
--

CREATE TABLE `hoadon` (
  `idhoadon` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idnhanvien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idkhachhang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idkhuyenmai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ngaylap` date NOT NULL,
  `tongtien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `hoadon`
--

INSERT INTO `hoadon` (`idhoadon`, `idnhanvien`, `idkhachhang`, `idkhuyenmai`, `ngaylap`, `tongtien`) VALUES
('HD1', 'NV3', 'KH10', 'KM2', '2021-05-17', 75001600),
('HD2', 'NV4', 'KH2', 'KM2', '2021-05-17', 158977750);

-- --------------------------------------------------------

--
-- Table structure for table `khachhang`
--

CREATE TABLE `khachhang` (
  `idkhachhang` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenkhachhang` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `sdt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `gmail` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khachhang`
--

INSERT INTO `khachhang` (`idkhachhang`, `tenkhachhang`, `diachi`, `sdt`, `gmail`) VALUES
('KH1', 'Tăng Trình Quang', 'TP HCM', '0123654951', 'trinhquang01@gmail.com'),
('KH10', 'Trần Đức Nguyên', 'Long An', '0841692241', 'nguyendhnam6@gmail.com'),
('KH11', 'Lê Thúc Quang', 'Quảng Ngãi', '0841692325', 'quangminhon@gmail.com'),
('KH12', 'Ngô Trường Khải', 'Quảng Nam', '095589751', 'khaigymer@gmail.com'),
('KH2', 'Trần Đại Phát', 'Bến Tre', '0456321789', 'daiphat@gmail.com'),
('KH3', 'Đỗ Huy Thông', 'Bến Tre', '0456327771', 'huit2k1@gmail.com'),
('KH4', 'Phạm Tất Thành', 'Bến Tre', '0456222171', 'bichengbt@gmail.com'),
('KH5', 'Hàng Hữu Lợi', 'Bạc Liêu', '0497589664', 'loibede@gmail.com'),
('KH6', 'Trần Đình Lâm', 'Đắk Lắk', '0498589967', 'lamcoghe@gmail.com'),
('KH7', 'Nguyễn Giáp Tài', 'Kon Tum', '0498111167', 'nguyen.Gtai@gmail.com'),
('KH8', 'Nguyễn Đức Vinh', 'Đồng Nai', '0498222267', 'vinhvape@gmail.com'),
('KH9', 'Ngô Công Lâm', 'Bình Thuận', '0841002267', 'lamngaongoe@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `khuyenmai`
--

CREATE TABLE `khuyenmai` (
  `idkhuyenmai` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenkhuyenmai` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dieukienkhuyenmai` float NOT NULL,
  `phantramkhuyenmai` float NOT NULL,
  `ngaybatdau` date DEFAULT NULL,
  `ngayketthuc` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `khuyenmai`
--

INSERT INTO `khuyenmai` (`idkhuyenmai`, `tenkhuyenmai`, `dieukienkhuyenmai`, `phantramkhuyenmai`, `ngaybatdau`, `ngayketthuc`) VALUES
('KM1', 'Không khuyến mãi', 0, 0, '2021-04-30', '2022-04-30'),
('KM2', 'Khuyến mãi thường niên', 5, 5, '2021-04-30', '2022-04-30'),
('KM3', 'Khuyến mãi ', 5, 3, '2021-04-30', '2022-04-30');

-- --------------------------------------------------------

--
-- Table structure for table `loaisanpham`
--

CREATE TABLE `loaisanpham` (
  `idloaisanpham` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenloaisanpham` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `mota` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `loaisanpham`
--

INSERT INTO `loaisanpham` (`idloaisanpham`, `tenloaisanpham`, `mota`) VALUES
('GEAR', 'Phụ kiện máy tính', 'bàn phím, màn hình, ...'),
('LAPTOP', 'Máy tính xách tay', 'Gọn nhẹ '),
('PC', 'Máy tính để bàn', 'Mạnh mẽ');

-- --------------------------------------------------------

--
-- Table structure for table `nhacungcap`
--

CREATE TABLE `nhacungcap` (
  `idnhacungcap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tennhacungcap` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `diachi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `sdt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `gmail` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhacungcap`
--

INSERT INTO `nhacungcap` (`idnhacungcap`, `tennhacungcap`, `diachi`, `sdt`, `gmail`) VALUES
('NCC1', 'Cty TNC Store', 'TP Hà Nội', '0123456789', 'tncstore@gmail.com'),
('NCC2', 'Cty GearVN', 'TP HCM', '0120728815', 'gearvn@gmail.com'),
('NCC3', 'Cty Phong Vũ', 'TP HCM', '0703192738', 'phongvu@gmail.com'),
('NCC4', 'Cty Phúc Anh Smart World', 'TP Hà Nội', '0501239237', 'phucanhsw@gmail.com'),
('NCC5', 'Cty An Phát PC', 'TP Hà Nội', '0901888869', 'anphatpro@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `idnhanvien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tennhanvien` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `ngaysinh` date NOT NULL,
  `diachi` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `sdt` varchar(15) COLLATE utf8_unicode_ci NOT NULL,
  `gmail` varchar(45) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`idnhanvien`, `tennhanvien`, `ngaysinh`, `diachi`, `sdt`, `gmail`) VALUES
('NV1', 'Phạm Ngọc Quang', '2001-04-05', 'Đà Nẵng', '0145647854', 'quang02@gmail.com'),
('NV2', 'Bùi Phước Hải', '2001-06-15', 'Kiên Giang', '0981578293', 'hai12211@gmail.com'),
('NV3', 'Trương Hồng Phát', '2001-08-13', 'TP HCM', '0805126735', 'phatng@gmail.com'),
('NV4', 'Phạm Duy Linh', '2001-08-15', 'Đắk Lắk', '0975128835', 'linhxike@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `phanquyen`
--

CREATE TABLE `phanquyen` (
  `idquyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tenquyen` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `chitietquyen` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phanquyen`
--

INSERT INTO `phanquyen` (`idquyen`, `tenquyen`, `chitietquyen`) VALUES
('admin', 'Admin', 'qlBanHang qlNhapHang qlSanPham qlLoaiSanPham qlHoaDon qlKhuyenMai qlNhanVien qlKhachHang qlPhieuNhap qlNCC qlTaiKhoan qlQuyen'),
('user', 'Nhân viên Bán hàng', 'qlBanHang xemSanPham xemLoaiSanPham xemHoaDon xemNhanVien xemKhachHang');

-- --------------------------------------------------------

--
-- Table structure for table `phieunhap`
--

CREATE TABLE `phieunhap` (
  `idphieunhap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idnhacungcap` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idnhanvien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `ngaynhap` date NOT NULL,
  `tongtien` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `phieunhap`
--

INSERT INTO `phieunhap` (`idphieunhap`, `idnhacungcap`, `idnhanvien`, `ngaynhap`, `tongtien`) VALUES
('PN1', 'NCC3', 'NV2', '2021-05-17', 449215000),
('PN2', 'NCC2', 'NV3', '2021-05-17', 2770055000);

-- --------------------------------------------------------

--
-- Table structure for table `sanpham`
--

CREATE TABLE `sanpham` (
  `idsanpham` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idloaisanpham` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `tensanpham` varchar(70) COLLATE utf8_unicode_ci NOT NULL,
  `dongia` double NOT NULL,
  `soluong` int(10) UNSIGNED NOT NULL DEFAULT 1,
  `mota` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `anh` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `sanpham`
--

INSERT INTO `sanpham` (`idsanpham`, `idloaisanpham`, `tensanpham`, `dongia`, `soluong`, `mota`, `anh`) VALUES
('SP1', 'LAPTOP', ' ASUS TUF Dash F15\r\n', 29990000, 20, 'i7-11370H/ 8GB/ 512GB/ RTX 3050Ti 4GB/ 15.6\' FHD 144Hz/ Win 10\r\n', 'SP1.png\r\n '),
('SP10', 'LAPTOP', 'LG Gram 17Z90N-V.AH75A5', 39500000, 19, 'i7-1065G7 /8GB/512GB/17″/FHD/IPS/WIN 10/GRA', 'SP10null'),
('SP11', 'PC', 'Asus ROG Huracan G21CN-D-VN001T', 25990000, 43, 'i5-9400F/ 8GB/ 256Gb-PCIE/ RTX2060', 'sp11-500x500.png'),
('SP12', 'PC', 'Asus ROG Strix GL10CS', 23990000, 21, 'i5-9400/ 8GB/ 512GB SSD/ RTX 2060/ 500W', 'sp12-500x500.png'),
('SP13', 'PC', 'Asus ROG Huracan G21CX', 49990000, 17, 'i7-9700K/ 16GB/ 512Gb-PCIE/ RTX2070 8G', 'sp13-500x500.png'),
('SP14', 'PC', 'MSI Trident X Plus 9SE-256XVN', 62499000, 36, 'i7 9700K/ Z390/ 16GB/ 256GB/ RTX 2080/ 650W', 'sp14-500x500.png'),
('SP15', 'PC', 'Intel NUC Kit NUC8i7HVK', 24980000, 9, ' i7-8809G/16GB/512GB/RX Vega M GH', 'sp15-500x500.png'),
('SP16', 'PC', 'Intel Nuc BOXNUC7PJYH', 5290000, 60, 'Silver J5005 Processor/4GB/124GB/HD Graphics/Free Dos', 'sp16-500x500.png'),
('SP17', 'PC', 'Alpha 10100', 851600, 17, 'i3 10100/ H410/ 8GB/ 120GB/ 450W', 'sp17-500x500.png'),
('SP18', 'PC', 'Sniper 1660', 19976000, 58, 'i3 10105F/ B460/ 8GB/ 128GB SSD/ GTX 1660/ 500W', 'sp18-500x500.png'),
('SP19', 'PC', 'Sniper 1660 Super', 22676000, 58, 'i5 10400F/ B460/ Ram 8GB/ SSD 128GB/ GTX 1660 Super/ 450W', 'sp19-500x500.png'),
('SP2', 'LAPTOP', 'MSI GF65 Thin 10UE\r\n', 29490000, 20, 'i5-10500H/ 16GB/ 512GB SSD/ RTX 3060 6GB/ 15.6 inch FHD/ Win 10\r\n', 'sp2-500x500.png'),
('SP20', 'PC', 'Lumen 2060', 25906000, 18, 'i3 10105F/B460/ 16GB/ 256GB/ RTX 2060/ 550W', 'sp20-500x500.png'),
('SP21', 'GEAR', 'Gaming Cooler Master GM34-CW', 14590000, 19, 'VA/ UWQHD/ 144Hz', 'SP21null'),
('SP22', 'GEAR', 'Gaming LG 27MP500-B', 4489000, 30, 'IPS/27\"/FullHD/75Hz', 'sp22-500x500.png'),
('SP23', 'GEAR', 'Asus TUF Gaming VG27VH1B', 5880000, 19, 'VA/FullHD/144Hz/1ms', 'sp23-500x500.png'),
('SP24', 'GEAR', 'Router Wifi ASUS RT-AX855', 1790000, 19, 'MU-MIMO, OFDMA, AiMesh, NitroQAM™', 'sp24-500x500.png'),
('SP25', 'GEAR', 'Router Wifi Gaming ASUS RT-AX82U', 5989000, 13, 'ASUS AiProtection Pro/ASUS AiMesh', 'sp25-500x500.png'),
('SP26', 'LAPTOP', 'ASUS ROG Strix G17', 41890000, 56, 'R7-5800H/16GB/512GB/RTX 3060 6GB/17.3 inch/WQHD/Win 10', 'SP26.jpg'),
('SP27', 'GEAR', 'Gaming LG 27MP500-B', 44890000, 52, 'IPS/27\"/FullHD/75Hz', 'SP27null'),
('SP3', 'LAPTOP', 'ASUS ROG Strix G17 ', 41890000, 36, 'R7-5800H/16GB/512GB/RTX 3060 6GB/17.3 inch/WQHD/Win 10', 'sp3-500x500.png'),
('SP4', 'LAPTOP', 'Asus ROG Zephyrus G15', 39890000, 19, 'R7 5800HS/ 16GB/ SSD 512GB/ RTX 3060/ 15.6\"/ 2K/ 165Hz/ Win 10', 'SP4null'),
('SP5', 'LAPTOP', 'Acer Gaming Nitro 5', 25990000, 17, ' i7-11370H/ 8GB/ 512GB/ GTX 1650 4GB/15.6 inch FHD/Win 10', 'sp5-500x500.png'),
('SP6', 'LAPTOP', 'MSI Modern 14 B10MW 483VN', 13490000, 19, ' i3 10110U/ 8GB RAM/ 256GB SSD/ 14.0inch FHD/ Win10', 'sp6-500x500.png'),
('SP7', 'LAPTOP', 'Acer Swift 3x', 26590000, 19, 'i5-1135G7/ Iris Xe Max/ 16GB RAM/ 1TB SSD NVME/ 14\" FHD', 'SP7null'),
('SP8', 'LAPTOP', 'MSI Prestige 14 EVO', 28790000, 20, 'I7 1185G7/16GB/512GB/14″/FHD/IPS/WIN 10/GRAY', 'sp8-500x500.png'),
('SP9', 'LAPTOP', 'Asus ExpertBook P2451FA-EK0229T', 16990000, 43, 'i5-10210U /8GB/512GB/14″/FHD/IPS/WIN 10/BLUE', 'SP9null');

-- --------------------------------------------------------

--
-- Table structure for table `taikhoan`
--

CREATE TABLE `taikhoan` (
  `taikhoan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `matkhau` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `idnhanvien` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `idquyen` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `hinh` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `taikhoan`
--

INSERT INTO `taikhoan` (`taikhoan`, `matkhau`, `idnhanvien`, `idquyen`, `hinh`) VALUES
('admin', '1', 'NV1', 'admin', 'vi5.jpg'),
('hai', '1', 'NV1', 'admin', 'shiba_inu.jpg'),
('havicute', '1', 'NV3', 'user', 'vi1.jpg'),
('lam', '1', 'NV1', 'user', 'vi5.jpg'),
('lam1', '1', 'NV1', 'admin', 'edchina.jpg'),
('quan', '1', 'NV1', 'admin', 'vi1.jpg'),
('quang1', '2', 'NV1', 'admin', 'vi5.jpg');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`idhoadon`);

--
-- Indexes for table `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`idkhachhang`);

--
-- Indexes for table `khuyenmai`
--
ALTER TABLE `khuyenmai`
  ADD PRIMARY KEY (`idkhuyenmai`);

--
-- Indexes for table `loaisanpham`
--
ALTER TABLE `loaisanpham`
  ADD PRIMARY KEY (`idloaisanpham`);

--
-- Indexes for table `nhacungcap`
--
ALTER TABLE `nhacungcap`
  ADD PRIMARY KEY (`idnhacungcap`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`idnhanvien`);

--
-- Indexes for table `phanquyen`
--
ALTER TABLE `phanquyen`
  ADD PRIMARY KEY (`idquyen`);

--
-- Indexes for table `phieunhap`
--
ALTER TABLE `phieunhap`
  ADD PRIMARY KEY (`idphieunhap`);

--
-- Indexes for table `sanpham`
--
ALTER TABLE `sanpham`
  ADD PRIMARY KEY (`idsanpham`),
  ADD KEY `LoaiSP` (`idloaisanpham`);

--
-- Indexes for table `taikhoan`
--
ALTER TABLE `taikhoan`
  ADD PRIMARY KEY (`taikhoan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
