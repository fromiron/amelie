package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/MenuInsertSvl")
public class MenuInsertSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MenuInsertSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        System.out.println("svl");
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = "";

        String msg;

        HttpSession session = request.getSession(false);

        // session 처리 에러 getAttribute 사용!
        if (session.getAttribute("admName") == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        String typeID = request.getParameter("typeID");

        if (Integer.parseInt(typeID) == 100) {
            request.setAttribute("typeID", typeID);
            url = "/courseInsert.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        url = "/menuInsert.jsp";
        request.setAttribute("typeID", typeID);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
