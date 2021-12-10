import React, { useState } from "react";
import Form from "./LoginForm/Form";
import { useNavigate, Navigate } from "react-router";
export default function Login() {
  const [isSubmitted, setIsSubmitted] = useState("Thất Bại");
  const navigate = useNavigate();
  function submitForm(string) {
    setIsSubmitted(string);
  }
  return (
    <div>
      {isSubmitted == "Thất Bại" ? (
        <Form submittedForm={submitForm} />
      ) : (
        <Navigate to={"/"} />
      )}
    </div>
  );
}
