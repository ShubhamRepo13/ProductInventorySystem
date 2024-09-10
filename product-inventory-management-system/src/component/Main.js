import { useState,useEffect } from "react"
import {useFormik} from "formik"
import $, { data } from "jquery"

export default function PostDemo()
{
    const [product,setProduct]=useState([]);
    const [productError,setproductError]=useState('');

    const formik=useFormik({
        initialValues : {
            product_id:0,
            product_name:'',
            product_description:'',
            product_quantity:0,
            product_price:0
        },
        onSubmit:values=>{
            //alert(JSON.stringify(values));
            $.ajax({
                method:"POST",
                url:"http://localhost:8080/product/save",
                headers: { 
                    'Accept': 'application/json',
                    'Content-Type': 'application/json' 
                },
                data: JSON.stringify(values),
                'dataType': 'json'
            })

            alert("data Submitted Successfully!!!");
        }

    });

    useEffect(()=>{

    },[])
    
    return(


        <div className="container-fluid">
            <div className="container-fluid bg-success ">
               <h1 className="text-center text-white"> <b>PRODUCT INVENTORY MANAGEMENT SYSTEM</b></h1>
            </div>
            <h2>Register Product</h2>
            <form onSubmit={formik.handleSubmit}>
                <dl>
                    <dt>Product Id</dt>
                    <dd><input type="text" name="product_id" onChange={formik.handleChange} value={formik.values.product_id}/></dd>
                    <dt>Product Name</dt>
                    <dd><input type="text" name="product_name" onChange={formik.handleChange} value={formik.values.product_name}/></dd>
                    <dt>Product Description</dt>
                    <dd><input type="text" name="product_description" onChange={formik.handleChange} value={formik.values.product_description}/></dd>
                    <dt>Product Quantity</dt>
                    <dd><input type="text" name="product_quantity" onChange={formik.handleChange} value={formik.values.product_quantity}/></dd>
                    <dt>Product Price</dt>
                    <dd><input type="text" name="product_price" onChange={formik.handleChange} value={formik.values.product_price}/></dd>
                </dl>
                <button className="btn btn-success " type="submit">Submit</button>
                
                <button className="btn btn-danger ms-4" type="reset" onClick={formik.resetForm}>Reset</button>
            </form>
        </div>
    )
        
    
}