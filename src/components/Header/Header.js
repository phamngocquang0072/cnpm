import React from "react";
import { Container, LogoImage } from "./Header.styles";
//Routing
import { Link } from "react-router-dom";
//Image

import Logo2 from "../../images/logo2.png";
export default function Header({ state, style }) {
  return (
    <>
      <nav style={style} className="navbar navbar-expand-lg ">
        <Container className="container-fluid">
          <LogoImage className="navbar-brand" src={Logo2} />
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <Link class="nav-link text-light" to="/">
                  Home
                </Link>
              </li>
              <li class="nav-item">
                <Link class="nav-link text-light" to="/cart">
                  Category
                </Link>
              </li>
              <li class="nav-item">
                <Link class="nav-link text-light" to="/shop">
                  Shop
                </Link>
              </li>
              <li class="nav-item">
                <Link class="nav-link text-light" to="/cart">
                  Card
                </Link>
              </li>
              <li class="nav-item">
                <Link class="nav-link text-light" to="/cart">
                  Wishlist
                </Link>
              </li>
              <li class="nav-item">
                <input
                  type="search"
                  class="form-control  search"
                  placeholder="Search"
                  aria-label="Search"
                  style={{
                    paddingLeft: "20px",
                    width: "350px",
                    marginLeft: "20px",
                  }}
                />
              </li>
            </ul>
          </div>                  
          <div className="d-flex">
            <span className="nav-item me-5">              
              {window.sessionStorage.getItem('loginid') !== 'null' 
              ? 
              <Link
                style={{ textDecoration: "none", color: "white" }}
                to=""
              >
                { window.sessionStorage.getItem('loginname')}
              </Link>
              :
              <Link
                style={{ textDecoration: "none", color: "white" }}
                to="/login"
              >
                Login
              </Link>}
            </span>
            {window.sessionStorage.getItem('loginid') != 'null' ?<Link
                style={{ textDecoration: "none", color: "white" }}
                to="/logout"
              >
                Logout
              </Link>  :null}
            
          </div>
        </Container>
      </nav>
    </>
  );
}
