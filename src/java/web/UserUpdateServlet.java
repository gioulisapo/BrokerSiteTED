/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.UserDb;
import entities.UserEntity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Called by UserUpdateProfile.jsp to update profile once updated user must be re-accepted by admins
 *
 */
public class UserUpdateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        String url, message, title;
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String username = request.getParameter("InputUsername");
            String lastName = request.getParameter("InputLastName");
            String name = request.getParameter("InputName");
            String mail = request.getParameter("InputEmail");
            String country = request.getParameter("InputCountry");
            String phone = request.getParameter("InputTelephone");
            String password = request.getParameter("InputPass");
            String[] role = request.getParameterValues("InputRole");
            StringBuffer result = new StringBuffer();
            for (int i = 0; i < role.length; i++) {
                result.append(role[i]);
                if (i != role.length - 1) {
                    result.append(",");
                }
            }
            String roles = result.toString();
            UserEntity user = new UserEntity();

            user.setFirstName(name);
            user.setLastName(lastName);
            user.setCountry(country);
            user.setMail(mail);
            user.setRole(roles);
            user.setPassword(password);
            user.setUsername(username);
            user.setPhone(phone);
            if (UserDb.updateUser(user)) {
                url = "/WEB-INF/jsp/Response.jsp";
                message = "Your changes have been submited. Your account will be revaluated shortly by our Administrators";
                title = "Changes Submited";
            } else {
                message = "Something went wrong! Our Administrators will be notified Thank you for your pacience";
                title = "Update Error";
                url = "/WEB-INF/jsp/Response.jsp";
            }
            request.setAttribute("url", "index.jsp");
            request.setAttribute("title", title);
            request.setAttribute("message", message);
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
