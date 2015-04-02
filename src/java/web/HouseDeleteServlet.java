/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.HouseDb;
import entities.HouseEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HouseDeleteServlet extends HttpServlet {

/**
 *
 * Servlet can be called from Houseinfo.jsp it is used to delete the house that is
 * depicted in the HouseInfo.jsp page
 */
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ServletResponse="", url = "/WEB-INF/jsp/Response.jsp",title="";
        HouseEntity house = new HouseEntity();
        HttpSession session = request.getSession(true);
        String houseId = session.getAttribute("houseId").toString();
        request.setAttribute("close", "true");
        house.setUniqueCode(houseId);
        if (houseId != null && !houseId.equals("")) {
            if (HouseDb.Delete(house) > 0) {
                request.setAttribute("close", "true");
                ServletResponse = "House was Deleted";
                title="Success";
            }
            else 
            {
                ServletResponse = "Error Trying to delete House. Please do try again latter";
                title="Error";
            }
        }
        request.setAttribute("title", title);
        request.setAttribute("message", ServletResponse);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
