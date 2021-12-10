<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Customer extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'customer_id';
    protected $table = 'customer';
    protected $guarded = [];
}
