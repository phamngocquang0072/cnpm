<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CategoryProduct extends Model
{
    public $timestamps = false;

    protected $guarded = [];
    protected $primaryKey = 'category_id';
    protected $table = 'category_product';
}
