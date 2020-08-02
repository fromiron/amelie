package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/MenuUpdateSvl")
public class MenuUpdateSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MenuUpdateSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        System.out.println("update svl");

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String url = "";
        String msg;
        System.out.println("めあ");
        String menuID = request.getParameter("menuID");
        String typeID = request.getParameter("typeID");
        //String menuID ="1";
        //String typeID ="100";
        System.out.println("menuID :" + menuID);
        System.out.println("typeID :" + typeID);
        System.out.println("めあ2");

        HttpSession session = request.getSession(false);

        if (session.getAttribute("admName") == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        System.out.println("めあ3");

        if (Integer.parseInt(typeID) == 100) {
            System.out.println("めあ４");
            request.setAttribute("menuID", menuID);
            request.setAttribute("typeID", typeID);
            url = "/courseUpdate.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        System.out.println("めあ5");
        url = "/menuUpdate.jsp";
        request.setAttribute("menuID", menuID);
        request.setAttribute("typeID", typeID);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
