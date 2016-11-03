package filters;

import dto.UserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 13.10.2016.
 */
@WebFilter(filterName = "AdminPagesFilter", urlPatterns = "/pages/*")
public class AdminPagesFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getRequestURI().contains("admin")) {
            UserDTO userDTO = (UserDTO) request.getSession().getAttribute("user");
            if ((userDTO == null) || !(userDTO.getRole().getName().equals("Admin"))) {
                request.getSession().setAttribute("message", "Вы должны быть администратором.");
                response.sendRedirect(request.getContextPath() + "/pages/common/login.jsp");
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
