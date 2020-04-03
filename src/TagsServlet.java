import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/TagsServlet")
public class TagsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, List<Map<String, String>>> id_tag_map = null;
        try {
            id_tag_map = UserDAO.TagsServlet(request.getParameter("get"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.setAttribute("id_tag_map", id_tag_map);
        request.getRequestDispatcher("/page/tags.jsp").forward(request,response);
    }
}
