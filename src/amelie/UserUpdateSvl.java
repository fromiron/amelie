package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class UserUpdateSvl
 */
@WebServlet (urlPatterns = "/UserUpdateSvl")
public class UserUpdateSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    public UserUpdateSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = "";
        HttpSession session = request.getSession(false);

        System.out.println("UserUpdateSvl");


        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        System.out.println("update svl");
        url = "/userUpdate.jsp";

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
