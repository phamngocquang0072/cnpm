import React, {useEffect, useState} from "react";
//Components
import Header from "./Header/Header";
import HeroImage from "./HeroImage/HeroImage";
import GridLayout from "./GridLayout/GridLayout";
import Items from "./Items/Items";
import Footer from "./Footer/Footer";
import Banner from "./Banner/Banner";
import Pagination from "./Pagination/Pagination";

//DUMMY DATA
import sale from "../images/sale.jpg";
import mod1 from "../images/mod1.jpg";
import mod2 from "../images/mod2.jpg";
import mod3 from "../images/mod3.jpg";
import mod4 from "../images/mod4.jpg";
import { DATA } from "./DUMMY_DATA";
//Styles
import "../../src/style.css";
import productApi from "../api/productApi";
//slide
const image = [
  { a: mod3, bg: "black" },
  { a: mod4, bg: "#d2b052" },
  { a: mod2, bg: "black" },
  { a: mod1, bg: "#d2b052" },
];
export default function Home() {
  document.title = "Home";
  const [productList, setProductList] = useState([]);
  useEffect(() => {
    const productList = async () => {
      try{
        const p = {
          _pages: 1,
          _limit: 1,
        }
        const res = await productApi.getAll(p);
        console.log(res);
        setProductList(res);
      }catch(err){
        console.error(err);
        console.log('hiiiiiiiiiiiiiii')
      }
    }
    productList();
  }, []);
  

  return (
    <>
      <Header
        style={{
          background: "none",
          position: "absolute",
          zIndex: 9999,
          width: "100%",
        }}
      />
      <HeroImage image={image} />
      <Banner image={sale} />
      <GridLayout header="New items">
        {productList.slice(0, 4).map((item) => {
         
          console.log(item.product_id)
          return (
            <Items
              key={item.product_id}
              image={item.image}
              
              name= {item.name}
              price={item.price}
              dis= "20%"
              classi= "discount"
            />
          );
        })}
      </GridLayout>
      <Pagination arrItems={productList} itemPerPage={4} header={"Popular items"} />
      <Footer />
    </>
  );
}
