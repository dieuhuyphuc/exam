package com.example.sqlinjection.servlet;


import com.example.sqlinjection.dao.UserDao;
import com.example.sqlinjection.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = dao.getUser(username, password); //'' or 1=1--
        PrintWriter out = response.getWriter();

        if (user != null) {
            out.append(user.toString());
            out.close();
            response.sendRedirect("index.jsp");
        }else {
            out.append("error");
            out.close();
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
