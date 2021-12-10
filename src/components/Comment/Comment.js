import React from "react";
//Styles
import { Wrapper, Content } from "./Comment.styles";
export default function Comment({ username, avatar }) {
  return (
    <>
      <Wrapper>
        <Content className="row">
          <div className="col-sm-1">
            <img src={avatar} alt="avatar" />
          </div>
          <div className="col-sm-8">
            <h1>{username}</h1>
            <p>Very nice</p>
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
