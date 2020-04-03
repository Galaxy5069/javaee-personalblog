import DAO.ArticleContentDAO;
import DAO.UserDAO;
import bean.Article;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/ArticleServlet")
public class ArticleServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int param = Integer.parseInt(request.getParameter("id"));
        Article article = null;
        try {
            article = ArticleContentDAO.GetContent(Integer.toString(param));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Article article_next = ArticleContentDAO.GetNextArticle(param + 1);
        Article article_pre = ArticleContentDAO.GetPreArticle(param - 1);
        request.setAttribute("article",article);
        request.setAttribute("article_next",article_next);
        request.setAttribute("article_pre",article_pre);
        request.getRequestDispatcher("/page/article.jsp").forward(request,response);
    }

}
