package com.example;//defines the project package

import jakarta.servlet.*;//Used for servlet Functionality
import jakarta.servlet.http.*;//Used for servlet Functionality
import java.io.IOException;
import java.util.*;//This is used for list and the array list

public class CartServlet extends HttpServlet {//it handles https request (get and post)

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)//used for when a user submit a form
            throws ServletException, IOException {

        String item = request.getParameter("item");//that reads the item name from the input

        HttpSession session = request.getSession();//this stores the data per user

      
        List<String> cart = (List<String>) session.getAttribute("cart");//helps in retrieving the cart and also type casting is required where the object is changed to the string

        if (cart == null) {//if there is a first-time user the cart does not exist
            cart = new ArrayList<>();//if there is a first-time user the cart does not exist
        }

       
        if (item != null && !item.trim().isEmpty()) {//helps you to prevent the null items
            cart.add(item);
        }

     
        session.setAttribute("cart", cart);//helps you to update the session data


        response.sendRedirect("cart");//will redirect to /Cart and also triggers the do get method
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)//It is called when the user visit
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");

        request.setAttribute("cartItems", cart);//passes the cart data to the jsp page

        RequestDispatcher rd = request.getRequestDispatcher("/cart.jsp");//helps you send the request to the cart.jsp and the jsp will display the cart items
        rd.forward(request, response);
    }
}