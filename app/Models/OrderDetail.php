<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class OrderDetail extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'order_id';
    protected $table = 'orderdetail';
    protected $guarded = [];
}
