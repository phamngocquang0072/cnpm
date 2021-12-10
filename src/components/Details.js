import React, { useState, useEffect } from "react";
import { useLocation } from "react-router-dom";
//Components
import Header from "./Header/Header";
import Footer from "./Footer/Footer";
import DetailsImage from "./DetailsImage/DetailsImage";
import DetailsInfo from "./DetailsInfo/DetailsInfo";
import Comment from "./Comment/Comment";
import api from "./api";
//Image
import Image from "../images/YuruCamp.jpg";
export default function Details() {
  const search = useLocation().search;
  const idpro = new URLSearchParams(search).get("idpro");
  const [Posts, setPosts] = useState([]);

  useEffect(() => {
    if (idpro !== null) {
      api.APIPost("shop/detail?idpro=" + idpro).then((res) => {
        setPosts(res.data.productdetail);
      });
    }
  }, [search]);
  return (
    <>
      <Header style={{ backgroundColor: "black" }} />
      <div style={{ maxWidth: "100%" }} className="container">
        <div class="row">
          <div class="col-sm-4">
            <DetailsImage srcImg={Image} alt="items" />
          </div>
          <div class="col-sm-8">
            {Posts.map((item) => {
              console.log(item);
              return <DetailsInfo title={item.name} price={item.price} />;
            })}
          </div>
        </div>
        <div style={{ border: "1px solid blue", margin: "0 20px 20px 10px" }}>
          <h1 style={{ paddingLeft: "20px" }}>Comment</h1>

          <Comment username="Test" avatar={Image} />
          <Comment username="Test" avatar={Image} />
          <Comment username="Test" avatar={Image} />
          <Comment username="Test" avatar={Image} />
        </div>
      </div>
      <Footer />
    </>
  );
}
