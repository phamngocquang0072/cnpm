<?php

namespace App\Http\Controllers;
use App\Models\OrderQuanAo;
use Illuminate\Http\Request;

class OrderQuanAoController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return OrderQuanAo::all();
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
            'customer_id' => 'required',
            'user_id' => 'required',
            'address' => 'required',
            'total' => 'required',
            'status' => 'required',
            'created_at' => 'required',
            'updated_at' => 'required',
        ]);
        $product = OrderQuanAo::create($request->all());
        $product->timestamps = false;
        return $product;
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        return OrderQuanAo::find($id);
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
    public function update(Request $request, OrderQuanAo $idd)
    {
        $idd -> update($request->all());
        return $idd;
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy(OrderQuanAo $idd)
    {
        $idd -> delete();
    }
}
