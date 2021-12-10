<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class OrderQuanAo extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'order_id';
    protected $table = 'orderquanao';
    protected $guarded = [];
}
