/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.HouseDb;
import entities.HouseEntity;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Apostolis
 */
public class HousePresentationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String url = "/WEB-INF/jsp/HousePresentationPage.jsp", ServletResponse;
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String houseId = request.getParameter("house");
            HouseEntity house = new HouseEntity();
            house = HouseDb.ReturnHouse(houseId);
            if (house != null) {
                session.setAttribute("owner", house.getUsername());
                session.setAttribute("houseId", house.getUniqueCode());
                session.setAttribute("x", house.getLongitude());
                session.setAttribute("y", house.getLatitude());
                session.setAttribute("timeAvailable", house.getDateAvailable());
                session.setAttribute("heat", house.getHeat());
                session.setAttribute("squares", house.getSquares());
                session.setAttribute("region", house.getRegion());
                session.setAttribute("priceBuy", house.getPriceBuy());
                session.setAttribute("priceLent", house.getPriceLent());
                session.setAttribute("expenses", house.getExpenses());
                session.setAttribute("name", house.getName());
                session.setAttribute("address", house.getAddress());
                session.setAttribute("description", house.getDescription());
                session.setAttribute("username", session.getAttribute("username"));
            } else {
                request.setAttribute("close", "true");
                ServletResponse = "Error trying to find House. Try again later";
                url = "/WEB-INF/jsp/Response.jsp";
                request.setAttribute("message", ServletResponse);
            }
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
