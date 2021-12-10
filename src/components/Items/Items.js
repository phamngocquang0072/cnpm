import React, { useEffect } from "react";
import { Link } from "react-router-dom";
//Styles
import { Wrapper, Image } from "./Items.styles";
export default function Items({ id, image, name, price, dis, classi }) {
  const logo = `${process.env.PUBLIC_URL}/assets/images/${image}`
  return (
    <>
      <Wrapper>
        <div className={classi}>
          <span>{dis}</span>
        </div>
        <Image src={logo} alt="item-thumb" />
        <div className="name">
          <div>
            <h1 className="pro">
              <Link
                style={{ textDecoration: "none" }}
                to={`/details?idpro=${id}`}
              >
                {name}
              </Link>
            </h1>
            <label className="price">{`${price}$`}</label>
          </div>
          <div className="iconn">
            <div style={{ paddingBottom: "8px", paddingTop: "23px" }}>
              <i
                class="fas fa-heart"
                style={{ paddingtop: "40px", paddingbottom: "12px" }}
              ></i>
            </div>
            <span style={{ textAlign: "right" }}>sold 2.5K</span>
          </div>
        </div>
      </Wrapper>
    </>
  );
}
