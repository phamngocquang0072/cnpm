import api from "./api";
import {useState, useEffect} from 'react';
import { useNavigate,Navigate } from "react-router-dom";
export default function Logout (){
    const navigate = useNavigate();
    useEffect(() => {        
        window.sessionStorage.setItem('loginid',null);
        window.sessionStorage.setItem('loginname',null);  
        
    }, [])
    return (
        <>
        <h1>Loading..............</h1>
        <Navigate to={"/"}/>
        </>
    )
};