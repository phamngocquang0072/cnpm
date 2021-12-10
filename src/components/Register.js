import React, { useState } from "react";
import { useNavigate,Navigate } from "react-router-dom";
//Components
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import Form from "./RegisterForm/Form";
//Navigate

export default function Register() {
  const [isSubmitted, setIsSubmitted] = useState("Trùng email");
  const navigate = useNavigate();
  function submitForm(string) {
    setIsSubmitted(string);
  }
  return (
    <>
      <Header style={{ backgroundColor: "black" }} />
      {isSubmitted == "Trùng email"? <Form submittedForm={submitForm} /> :<Navigate to={"/login"}/>}
      <Footer />
    </>
  );
}
