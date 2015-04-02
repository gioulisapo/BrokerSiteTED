/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import db.UserDb;
import entities.UserEntity;
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
public class ServletLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String message="";
        String username = request.getParameter("InputUsername");
        String password = request.getParameter("InputPass");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            request.setAttribute("url", "index.jsp");
            request.setAttribute("title", "Enable Javascript");
            message = "Please Enable Javascript and fill all fields nesecary";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
        }
        HttpSession session = request.getSession(true);
        UserEntity user = new UserEntity();
        switch (UserDb.LoginAttemt(username, password)) {
            case -2:
                message = "Wrong password please try again";
                request.setAttribute("url", "index.jsp");
                request.setAttribute("title", "Wrong Password");
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                break;
            case -1:
                message = "Database Error Please try again later";
                request.setAttribute("url", "index.jsp");
                request.setAttribute("title", "Error");
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                break;
            case 0:
                message = "Username is not available please register";
                request.setAttribute("url", "index.jsp");
                request.setAttribute("title", "Please register");
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                break;
            case 1:
                message = "Your Registration is still being processed by our administrators"
                        + " Please try again later";
                request.setAttribute("url", "index.jsp");
                request.setAttribute("title", "Please register");
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                break;
            case 2:
                user = UserDb.ReturnUser(username);
                session = request.getSession(true);
                if (session != null) {
                    session.setAttribute("fistName", user.getFirstName());
                    session.setAttribute("phone", user.getPhone());
                    String values = user.getRole();
                    session.setAttribute("Lessor", false);
                    session.setAttribute("Buyer", false);
                    session.setAttribute("Seller", false);
                    session.setAttribute("Tenant", false);
                    session.setAttribute("Visitor", false);
                    session.setAttribute("Administrator", false);
                    for (String retval : values.split(",")) {
                        if (retval.equals("Lessor")) {
                            session.setAttribute("Lessor", true);
                        }
                        if (retval.equals("Buyer")) {
                            session.setAttribute("Buyer", true);
                        }
                        if (retval.equals("Seller")) {
                            session.setAttribute("Seller", true);
                        }
                        if (retval.equals("Tenant")) {
                            session.setAttribute("Tenant", true);
                        }
                        if (retval.equals("Visitor")) {
                            session.setAttribute("Visitor", true);
                        }
                        if (retval.equals("Administrator")) {
                            session.setAttribute("Administrator", true);
                        }
                    }
                    session.setAttribute("lastName", user.getLastName());
                    session.setAttribute("country", user.getCountry());
                    session.setAttribute("class", user.getClass());
                    session.setAttribute("mail", user.getMail());
                    session.setAttribute("username", user.getUsername());
                }
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/UserHomePage.jsp");
                rd.forward(request, response);
                break;
            case 3:
                user = UserDb.ReturnUser(username);
                session = request.getSession(true);
                if (session != null) {
                    session.setAttribute("fistName", user.getFirstName());
                    session.setAttribute("phone", user.getPhone());
                    String values = user.getRole();
                    session.setAttribute("Lessor", false);
                    session.setAttribute("Buyer", false);
                    session.setAttribute("Seller", false);
                    session.setAttribute("Tenant", false);
                    session.setAttribute("Visitor", false);
                    session.setAttribute("Administrator", false);
                    for (String retval : values.split(",")) {
                        if (retval.equals("Lessor")) {
                            session.setAttribute("Lessor", true);
                        }
                        if (retval.equals("Buyer")) {
                            session.setAttribute("Buyer", true);
                        }
                        if (retval.equals("Seller")) {
                            session.setAttribute("Seller", true);
                        }
                        if (retval.equals("Tenant")) {
                            session.setAttribute("Tenant", true);
                        }
                        if (retval.equals("Visitor")) {
                            session.setAttribute("Visitor", true);
                        }
                        if (retval.equals("Administrator")) {
                            session.setAttribute("Administrator", true);
                        }
                    }
                    session.setAttribute("lastName", user.getLastName());
                    session.setAttribute("country", user.getCountry());
                    session.setAttribute("class", user.getClass());
                    session.setAttribute("mail", user.getMail());
                    session.setAttribute("username", user.getUsername());
                }
                request.getRequestDispatcher("/WEB-INF/jsp/AdminHomePage.jsp").forward(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

}
