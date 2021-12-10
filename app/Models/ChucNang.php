<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class ChucNang extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'chucnang_id';
    protected $table = 'chucnang';
    protected $guarded = [];
}
