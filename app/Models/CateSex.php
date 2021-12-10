<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class CateSex extends Model
{
    public $timestamps = false;
    protected $primaryKey = 'catesex_id';
    protected $table = 'catesex';
    protected $guarded = [];
}
