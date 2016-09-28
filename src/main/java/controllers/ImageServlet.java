package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Alexandr on 27.09.2016.
 */
@WebServlet(name = "ImageServlet", urlPatterns = "/image")
public class ImageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (InputStream is = ImageServlet.class.getClassLoader().getResourceAsStream("pictures/" + request.getParameter("file"));
             BufferedInputStream in = new BufferedInputStream(is);) {
            int ch;
            while ((ch = in.read()) != -1) {
                response.getOutputStream().write(ch);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
