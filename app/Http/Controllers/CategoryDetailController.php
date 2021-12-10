<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\CategoryDetail;
class CategoryDetailController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return CategoryDetail::all();
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $request -> validate([
            'catesex_id' => 'required',
            'age_id' => 'required',
            'created_at' => 'required',
            'updated_at' => 'required',
        ]);
        $cate_details = CategoryDetail::create($request->all());
        $cate_details ->timestamps = false;
        return $cate_details;
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        $cate_details = CategoryDetail::find($id);
        return $cate_details;
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        //
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, CategoryDetail $cate_details)
    {
        $cate_details -> update($request->all());
        return $cate_details;
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(CategoryDetail $cate_details)
    {
        $cate_details->delete();
    }
}
