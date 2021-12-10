<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class XuatDetail extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'xuat_id';
    protected $table = 'xuatdetail';
    protected $guarded = [];
}
