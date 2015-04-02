package web;

import db.HouseDb;
import entities.House_PictureEintity;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB

public class HouseUploadPhotoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // gets values of text fields
        String message, url = "/WEB-INF/jsp/HouseInfo.jsp";
        HttpSession session = request.getSession(true);
        /*Reason for session attributes check is that if you have logged in as an Admin and you log out
         if back is pressed the cashed coppy of the page will appear allowing you to perform actions
         in that page*/
        if (session.getAttribute("username") == null) {
            url = "index.jsp";
        } else {
            String description = request.getParameter("description");
            String houseId = session.getAttribute("houseId").toString();
            Part filePart = request.getPart("photo");
            House_PictureEintity pic = new House_PictureEintity();
            pic.setDescription(description);
            pic.setHouseId(houseId);
            if (filePart != null) {
                pic.setPicture(filePart.getInputStream());
            }
            pic.GenerateUniqueId();
            if (HouseDb.insertPicture(pic)) {
                message = "Picture successfully uploaded";
            } else {
                message = "Problem while uploading picture. Please try again latter";
            }
            request.setAttribute("message", message);
        }
        getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
