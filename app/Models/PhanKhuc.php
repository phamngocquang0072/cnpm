<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class PhanKhuc extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'phankhuc_id';
    protected $table = 'phankhuc';
    protected $guarded = [];
}
