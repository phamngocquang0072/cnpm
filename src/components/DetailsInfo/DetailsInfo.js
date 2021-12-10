import React from "react";
//Styles
import { Wrapper, Content } from "./DetailsInfo.styles";
export default function DetailsInfo({ title, price,description, quantity}) {
  return (
    <>
      <Wrapper>
        <h1>{title}</h1>
        <Content>
          <p>
            {description}
          </p>
          <div className="row info-control">
            <div className="col-8">Total</div>
            <div className="col-4">{`${price} VNĐ`}</div>
          </div>          
          <div className="info-control title">Color: </div>
          <div className="info-control title">
            Rating:
            <br />
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
          </div>
        </Content>
      </Wrapper>
    </>
  );
}
