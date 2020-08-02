package cn.michael.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AServlet")
public class AServlet extends BaseServlet {

    public String forward(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("forward");
        return "f:/index.jsp";
    }

    public String redirect(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("redirect");
        return "r:/index.jsp";
    }

    public String test(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("test");
        return null;
    }
}
