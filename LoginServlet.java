package com.firstservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
        description = "Login Servlet Testing",
        urlPatterns = { "/LoginServlet" },
        initParams = {
                @WebInitParam(name = "user", value = "Krutika"),
                @WebInitParam(name = "password", value = "Kruti@77")
        }
)
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Regex for validation
        String nameRegex = "^[A-Z]{1}[a-z]{3,}$";
        String passRegex = "(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%]).{8,20}";

        // Getting user input
        String user = req.getParameter("user");
        String pwd = req.getParameter("pwd");

        // Fetching stored credentials from servlet init parameters
        String userID = getServletConfig().getInitParameter("user");
        String password = getServletConfig().getInitParameter("password");

        // Validation & Authentication
        if (userID.equals(user) && password.equals(pwd) &&
                Pattern.matches(nameRegex, user) && Pattern.matches(passRegex, pwd)) {

            req.setAttribute("user", user);
            req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
        } else {
            PrintWriter out = resp.getWriter();
            out.println("<html><body>");
            out.println("<font color='red'> Either username or password is wrong.</font>");
            out.println("<br><a href='login.jsp'>Go Back</a>");
            out.println("</body></html>");
        }
    }
}
