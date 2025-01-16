package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/*")
public class AuthenticationFilter extends HttpFilter implements Filter {
    private List<String> excludeRequests;

    public AuthenticationFilter() {
        super();
    }

    public void init(FilterConfig fConfig) throws ServletException {
        excludeRequests = new ArrayList<>();
        excludeRequests.add("/login");
    }

    private boolean isValidRequest(String request) {
        for (String excludeRequest : excludeRequests) {
            if (request.contains(excludeRequest)) {
                return true;
            }
        }
        return false;
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();

        boolean loggedIn = session != null && session.getAttribute("userLogin") != null;
        String userRequest = request.getRequestURI();

        if (loggedIn || isValidRequest(userRequest)) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

    public void destroy() {
    }
}
