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


public class UserInsertServlet extends HttpServlet
{

    /**
     *Called by UserUpdateProfile.jsp  Used to update the profile of a user. Once the profile is updated the user
     * must await for admin confirmation again.
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String message;
        String username = request.getParameter("InputUsername");
        String lastName = request.getParameter("InputLastName");
        String name = request.getParameter("InputName");
        String mail = request.getParameter("InputEmail");
        String country = request.getParameter("InputCountry");
        String phone = request.getParameter("InputTelephone");
        String password = request.getParameter("InputPass");
        String password2 = request.getParameter("InputPassSecond");
        String[] role = request.getParameterValues("InputRole");
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < role.length; i++)
        {
            result.append(role[i]);
            if (i != role.length - 1)
            {
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
        if (!password.equals(password2))
        { //In case of broken javascript
            message = "Please fill all required fields. Please enable your browsers javascript!!";
            request.setAttribute("url", "signup.jsp");
            request.setAttribute("title", "Enable Javascript!");
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
        }
        if (user.getCountry().isEmpty() || user.getFirstName().isEmpty() || user.getLastName().isEmpty() || user.getMail().isEmpty()
                || user.getPassword().isEmpty() || user.getPhone().isEmpty() || user.getRole().isEmpty() || user.getUsername().isEmpty())
        {
            request.setAttribute("url", "signup.jsp");
            request.setAttribute("title", "Enable Javascript!");
            message = "Please fill all required fields. Please enable your browsers javascript!!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
        }
        switch (UserDb.UsernameIsAvailable(user.getUsername()))
        {
            case 0:
                request.setAttribute("url", "signup.jsp");
                request.setAttribute("title", "Username not available");
                message = "Username " + user.getUsername() + " is already in use please use a different one";
                request.setAttribute("message", message);
                request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                break;
            case 1:
                if (UserDb.insertUser(user))
                {
                    request.setAttribute("url", "index.jsp");
                    request.setAttribute("title", "Success");
                    message = "Mr/Mrs " + user.getLastName() + " welcome to our service. Your registration will be reviewd by our"
                            + " Adminstrators within 24 hours.";
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/WEB-INF/jsp/Response.jsp").forward(request, response);
                    break;
                }
                else
                {
                    String url = "/error_html.html";
                    response.sendRedirect(url);
                }
                break;
            case -1:
                System.out.println("Sql ERRROR");//error while communicating with database
                break;
        }
    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        doPost(request, response);
    }

}
