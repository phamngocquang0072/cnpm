<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class NhapDetail extends Model
{
    public $timestamps = false;

    protected $guarded = [];
    protected $primaryKey = 'nhap_id';
    protected $table = 'nhapdetail';
}
