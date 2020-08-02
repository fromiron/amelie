package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveInsertSvl")
public class ReserveInsertSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final int   INSERT           = 11;
    public static final int   UPDATE           = 12;
    public static final int   DELETE           = 13;

    public ReserveInsertSvl() {
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
        int rsvYy = 0;
        int rsvMm = 0;
        int rsvDd = 0;
        int rsvHh = 0;
        int rsvMi = 0;
        int usrId = 0;
        int person = 0;
        int courseId = 0;

        HttpSession session = request.getSession(false);

        if (session == null) {
            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
        try {
            courseId = Integer.parseInt(request.getParameter("courseId"));
        } catch (Exception e) {

        }
        url = "/reserveInsert.jsp";

        try {
            throw new IdealException(IdealException.ERR_NO_NOT_VACANCY);
        } catch (Exception e) {
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_NOT_VACANCY);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/ReserveListSvl.java";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

}
