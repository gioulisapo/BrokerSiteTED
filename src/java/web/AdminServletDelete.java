/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.UserDb;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * The Servlet is used by AdminUsrOverview.jsp it is used to delete the selected
 * users
 */
public class AdminServletDelete extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "/WEB-INF/jsp/AdminUsrOverview.jsp";
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
        if back is pressed the cashed coppy of the page will appear allowing you to perform actions
        in that page*/
        if (session.getAttribute("Administrator") == null || (boolean) session.getAttribute("Administrator") == false) {
            url = "index.jsp";
        } else {
            String[] SelectedValues = request.getParameterValues("username");
            if (SelectedValues != null) {
                UserDb.Delete(SelectedValues);
            }
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
