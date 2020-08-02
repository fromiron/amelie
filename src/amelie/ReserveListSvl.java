package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveListSvl")
public class ReserveListSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReserveListSvl() {
        super();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String url = "";
        String msg;
        int usrIndex = 0;
        String usrId = "";
        HttpSession session = request.getSession(false);
        try {
            usrId = (String) session.getAttribute("usrId");
            User u = User.getUser(usrId);
            usrIndex = u.getUsrIndex();
            request.setAttribute("usrIndex", usrIndex);
            request.setAttribute("usrId", usrId);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        try {
            url = "/reserveList.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_DB_EXCEPTION);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/userIndex.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }

}
