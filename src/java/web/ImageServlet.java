package web;

import db.HouseDb;
import entities.House_PictureEintity;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

    /**
     *  Used by various pages. It allows dynamic presentation of houses pictures
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String picInfo = (request.getParameter("picInfo"));
        String[] parts = picInfo.split(",");
        String HouseId = parts[0];   
        int PicNo = Integer.parseInt(parts[1]);
        ArrayList<House_PictureEintity> ResultsArray = (ArrayList) HouseDb.SelectPicturesOfHouse(HouseId);
        byte[] content = ResultsArray.get(PicNo).getPictureOut();
        response.setContentType(getServletContext().getMimeType(HouseId));
        response.setContentLength(content.length);
        response.getOutputStream().write(content);
    }
}
