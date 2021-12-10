<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PhieuNhap extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'nhap_id';
    protected $table = 'phieunhap';
    protected $guarded = [];
}
