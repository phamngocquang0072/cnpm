<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class GioiTinh extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'sex_id';
    protected $table = 'gioitinh';
    protected $guarded = [];
}
