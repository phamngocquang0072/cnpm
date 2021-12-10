<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PhieuXuat extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'xuat_id';
    protected $table = 'phieuxuat';
    protected $guarded = [];
}
