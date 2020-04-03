import DAO.UserDAO;
import bean.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*User successUser = (User)request.getAttribute("User");
        while (successUser !=null){
            response.sendRedirect("/page/main.jsp");

        }*/
        request.setCharacterEncoding("utf-8");
        response.setHeader("content-type", "text/html;charset=utf-8");
        HttpSession session = request.getSession();
        Cookie cookie;

        /*if (!session.isNew()){
            response.sendRedirect("/Blog/page/main.jsp");
        }
        else {*/
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            User loginUser = new User();
            loginUser.setUsername(username);
            loginUser.setPassword(password);
            User successUser = null;
            try {
                successUser = new UserDAO().UserInstance(loginUser);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (successUser != null) {
                session = request.getSession();
                cookie = new Cookie("userSession", session.getId());
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
//                request.setAttribute("User", successUser);
                request.setAttribute("LoginSessionID", session.getId());
                request.getRequestDispatcher("/page/main.jsp").forward(request, response);

            } else {
                PrintWriter pw = response.getWriter();
                pw.println("<body>");
                pw.println("登录失败！用户名或密码错误");
                pw.println("</body>");
                pw.close();

            }
        }
//        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/page/main.jsp").forward(request, response);
    }
}
