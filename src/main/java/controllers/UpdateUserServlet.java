package controllers;

import dto.UserDTO;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

/**
 * Created by Alexandr on 05.10.2016.
 */
@WebServlet(name = "UpdateUserServlet", urlPatterns = "/updateuser")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
        if (!userDTO.getPwd().equals(request.getParameter("oldpwd"))) {
            setReqErr(request, response, "Неверный пароль.");
        } else {
            userDTO.setLogin(request.getParameter("login"));
            userDTO.setPwd(request.getParameter("pwd"));
            userDTO.setEmail(request.getParameter("email"));
            userDTO.setUserName(request.getParameter("userName"));
            userDTO.setSex(Boolean.parseBoolean(request.getParameter("sex")));
            userDTO.setBirthday(LocalDate.parse(request.getParameter("birthday")));
            UserServiceImpl.getInstance().update(userDTO);
            request.getSession().setAttribute("user", userDTO);
            request.getRequestDispatcher("/cinema").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void setReqErr(HttpServletRequest request, HttpServletResponse response, String message) throws IOException {
        request.getSession().setAttribute("message", message);
        response.sendRedirect(request.getContextPath() + "/pages/common/register.jsp");
    }
}
