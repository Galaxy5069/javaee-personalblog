import DAO.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/SortServlet")
public class SortServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("get");
//        System.out.println(request.;
//        System.out.println( request.getRequestURL().substring(request.getRequestURL().indexOf("?")+1) );
        Map<String, List<Map<String, String>>> sort_article_map = null;
        try {
            sort_article_map = UserDAO.SortedServlet(param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        /*if(param.equals("all")) {

            Map<String, List<Map<String, String>>> sort_article_map = UserDAO.SortedServlet(param);

        }
        else {
            Map<String, List<Map<String, String>>> sort_article_map = UserDAO.SortedServlet(param);

        }*/
        request.setAttribute("sort_article_map", sort_article_map);
        request.getRequestDispatcher("/page/sort.jsp").forward(request, response);
            /*List<String> list = new ArrayList<>();
            Iterator<String> iterator = list.iterator();
            System.out.println(iterator.next());*/

    }
}
