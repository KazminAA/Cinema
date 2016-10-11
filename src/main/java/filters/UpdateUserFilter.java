package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexandr on 05.10.2016.
 */
@WebFilter(filterName = "UpdateUserFilter", urlPatterns = "/personalarea", servletNames = {"UpdateUserServlet", "ExitServlet"})
public class UpdateUserFilter implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("message", "Вы еще не вошли.");
            response.sendRedirect(request.getContextPath() + "/pages/common/login.jsp");
        } else {
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void init(FilterConfig config) throws ServletException {

    }

}
