import React from "react";
import { Image } from "./Card.styles";
import { Link } from "react-router-dom";
// import { FontAwesomeIcon } from '~@fortawesome/fontawesome-free/css/fontawesome.css';
// import { faCheckSquare, faCoffee } from '@fortawesome/fontawesome-free-solid';

const Card = ({ name, price }) => {
  return (
    <div className="container">
      <div className="row" style={{ paddingBottom: "30px" }}>
        <div className="col-sm-1 iconic">
          <i class="fas fa-trash-alt icon"></i>
        </div>
        <div className="col-sm-4">
          <Image
            src="https://order.tokago.vn/uploads/2021/2/6/11/9/ed522ead3745b1c0709f76081662ab8b.jpg"
            width="178px"
            height="178px"
          />
        </div>
        <div className="col-sm-3">
          <h2>{name}</h2>
          <br /> <br />
          <Link to="/" style={{ color: "#B6034E" }}>
            Details
          </Link>
        </div>
        <div className="col-sm-4">
          <div className="container">
            <div className="row">
              <div className="col-sm">
                <span class="i">Price</span>
                <br />
                <span>{price}</span>
              </div>

              <div className="col-sm">
                <span class="i">Quantity</span>
                <br />
                <input type="number" style={{ width: "100%" }}></input>
              </div>
              <div className="col-sm">
                <span class="i">Total</span>
                <br />
                <span>120$</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};
export default Card;
