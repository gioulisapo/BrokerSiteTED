/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.HouseDb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * Servlet can be called from Houseinfo.jsp it is used to delete selected
 * pictures of a house by calling HouseDb.DeletePictures(SelectedValues)
 */
public class HouseImageDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/jsp/HouseInfo.jsp";
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String[] SelectedValues = request.getParameterValues("picture");
            if (SelectedValues != null) {
                HouseDb.DeletePictures(SelectedValues);
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
