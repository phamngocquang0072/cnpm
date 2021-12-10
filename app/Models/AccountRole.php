<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class AccountRole extends Model
{
    public $timestamps = false;
    protected $guarded = [];
    protected $primaryKey = 'chucvu_id';
    protected $table = 'accountrole';
}
