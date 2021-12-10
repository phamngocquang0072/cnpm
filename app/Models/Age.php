<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Age extends Model
{
    public $timestamps = false;
    protected $guarded = [];
    protected $primaryKey = 'age_id';
    protected $table = 'age';

}
