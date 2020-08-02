package cn.michael.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String methodName =  req.getParameter("method");

        if(methodName == null || methodName.trim().isEmpty()){
            throw new RuntimeException("The method parameter is null.");
        }
        Method method = null;
        try {
            method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("The method " + methodName + " doesn't exist");
        }
        try{
            String result = (String) method.invoke(this, req, resp);
            if(result == null || result.trim().isEmpty()){
                return;
            }

            if(result.contains(":")){
                int index =  result.indexOf(":");
                String prefix = result.substring(0, index);
                String suffix = result.substring(index+1);
                if(prefix.equalsIgnoreCase("r")){
                    resp.sendRedirect(req.getContextPath() + suffix);
                }else if(prefix.equalsIgnoreCase("f")){
                    req.getRequestDispatcher(suffix).forward(req, resp);
                }else{
                    throw new RuntimeException("THee operation: " + prefix +  "is not supported for now");
                }

            }else{
                req.getRequestDispatcher(result).forward(req,resp);
            }
        } catch (Exception e) {
            System.out.println("The method you call throw exception. Method :  "+ methodName);
            throw new RuntimeException(e);
        }
    }
}
