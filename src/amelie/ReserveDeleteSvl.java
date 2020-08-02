package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/ReserveDeleteSvl")
public class ReserveDeleteSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ReserveDeleteSvl() {
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

        int rsvId = 0;
        int person = 0;
        int courseId = 0;
        int tableId = 0;
        String date = "";
        String courseName = null;
        String talbeName = null;

        String msg;
        HttpSession session = request.getSession(false);

        System.out.println("MenuDeleteSvl");

        if (session == null) {
            System.out.println("session null");

            url = "/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        try {
            System.out.println("getparmeter start");

            date = request.getParameter("date");
            courseName = request.getParameter("courseName");
            talbeName = request.getParameter("talbeName");

            System.out.println(courseName);
            System.out.println(talbeName);
            System.out.println(date);

            String rsvIdStr = "";
            String personStr = "";
            String courseIdStr = "";
            String tableIdStr = "";


            try {
                rsvIdStr = (String) request.getParameter("rsvId");
                personStr = (String) request.getParameter("person");
                courseIdStr = (String) request.getParameter("courseId");
                tableIdStr = (String) request.getParameter("tableId");

                rsvId = Integer.parseInt(rsvIdStr);
                person = Integer.parseInt(personStr);
                courseId = Integer.parseInt(courseIdStr);
                tableId = Integer.parseInt(tableIdStr);
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println(rsvId);
            System.out.println(person);
            System.out.println(courseId);
            System.out.println(tableId);

            System.out.println("rsvId : " + rsvId);
        } catch (Exception e) {
            System.out.println("getparameter error");
        }

        try {
            System.out.println("reserveDelete forward");
            url = "/reserveDelete.jsp";

            request.setAttribute("mType", MenuType.getAllType());
            request.setAttribute("rsvId", rsvId);
            request.setAttribute("person", person);
            request.setAttribute("courseId", courseId);
            request.setAttribute("tableId", tableId);
            request.setAttribute("date", date);
            request.setAttribute("courseName", courseName);
            request.setAttribute("talbeName", talbeName);

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);

        } catch (IdealException e) {
            e.printStackTrace();
            IdealException ie = new IdealException(
                    IdealException.ERR_NO_DB_EXCEPTION);
            msg = ie.getMsg();
            System.out.println(msg);
            request.setAttribute("msg", msg);
            url = "/reserveDelete.jsp";

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

    }
}
