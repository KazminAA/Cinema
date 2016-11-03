package controllers.usercontroll;

import dao.impl.RoleDaoImpl;
import dto.UserDTO;
import dto.UserRoleDTO;
import models.UserRole;
import service.api.Service;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Alexandr on 11.10.2016.
 */
@WebServlet(name = "EditUserServlet", urlPatterns = "/admin/edituser")
public class EditUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Service<UserDTO> userService = UserServiceImpl.getInstance();
        UserDTO[] userDTOs = null;
        request.setCharacterEncoding("utf-8");
        if ((request.getParameter("edit") != null) && (request.getParameter("edit").equals("edit"))) {
            String[] idsStr = request.getParameterValues("id");
            String[] logins = request.getParameterValues("login");
            String[] pwds = request.getParameterValues("pwd");
            String[] emails = request.getParameterValues("email");
            String[] names = request.getParameterValues("userName");
            String[] surnames = request.getParameterValues("userSurname");
            String[] birthdays = request.getParameterValues("birthday");
            String[] roleids = request.getParameterValues("roleID");
            String[] sexs = request.getParameterValues("sex");
            if (idsStr != null) {
                for (int i = 0; i < idsStr.length; i++) {
                    UserDTO updatingUser = new UserDTO();
                    updatingUser.setId(Integer.parseInt(idsStr[i]));
                    updatingUser.setLogin(logins[i]);
                    updatingUser.setPwd(pwds[i]);
                    updatingUser.setEmail(emails[i]);
                    updatingUser.setUserName(names[i]);
                    updatingUser.setUserSurname(surnames[i]);
                    updatingUser.setBirthday(LocalDate.parse(birthdays[i]));
                    UserRoleDTO role = new UserRoleDTO();
                    role.setId(Integer.parseInt(roleids[i]));
                    updatingUser.setRole(role);
                    updatingUser.setSex(Boolean.parseBoolean(sexs[i]));
                    userService.update(updatingUser);
                }
                response.sendRedirect(request.getContextPath() + "/admin/usertodel");
            }
        } else {
            String[] ids = (String[]) request.getAttribute("selected");
            if (ids != null) {
                int count = ids.length;
                if (count != 0) {
                    userDTOs = new UserDTO[count];
                    for (int i = 0; i < count; i++) {
                        userDTOs[i] = userService.getById(Integer.parseInt(ids[i]));
                    }
                }
                List<UserRole> roles = RoleDaoImpl.getInstance().getAll();
                request.setAttribute("roles", roles);
                request.setAttribute("userToEdit", userDTOs);
                request.getRequestDispatcher("/pages/admin/usertoedit.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
