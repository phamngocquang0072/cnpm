<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
//NO CRUD: khong sai dc update va delete
//NO CRUD#1:  Khong sai dc delete
Route::resource('product', 'App\http\Controllers\ProductController');
Route::resource('account', 'App\http\Controllers\AccountController');
Route::resource('brand', 'App\http\Controllers\BrandController');
Route::resource('category_detail', 'App\http\Controllers\CategoryDetailController');//NO CRUD
Route::resource('category_product', 'App\http\Controllers\CategoryProductController');//NO CRUD
Route::resource('cate_sex', 'App\http\Controllers\CateSexController');
Route::resource('chucnang', 'App\http\Controllers\ChucNangController');//NO CRUD
Route::resource('comment', 'App\http\Controllers\CommentController');//NO CRUD
Route::resource('customer', 'App\http\Controllers\CustomerController');//NO CRUD#1
Route::resource('gioitinh', 'App\http\Controllers\GioiTinhController');//NO CRUD
Route::resource('nhap_detail', 'App\http\Controllers\NhapDetailController');//NO CRUD
Route::resource('order_detail', 'App\http\Controllers\OrderDetailController');//NO CRUD
Route::resource('phan_khuc', 'App\http\Controllers\PhanKhucController');//NO CRUD
Route::resource('order_quan_ao', 'App\http\Controllers\OrderQuanAoController');//NO CRUD
Route::resource('phieu_nhap', 'App\http\Controllers\PhieuNhapController');//NO CRUD
Route::resource('phieu_xuat', 'App\http\Controllers\PhieuXuatController');//NO CRUD
Route::resource('role', 'App\http\Controllers\RoleController');//NO CRUD
Route::resource('xuat_detail', 'App\http\Controllers\XuatDetailController');//NO CRUD
Route::resource('age', 'App\http\Controllers\AgeController');
Route::resource('accountrole', 'App\http\Controllers\AccountRoleController');//NO CRUD
