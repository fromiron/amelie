package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = "/AdminLoginSvl")
public class AdminLoginSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AdminLoginSvl() {
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
        String admName;
        String password;
        String url = "";
        String msg;

        System.out.println("AdminSvl");


        HttpSession session = request.getSession(false);

        admName = request.getParameter("admName");
        password = request.getParameter("password");

        Admin a = Admin.login(admName, password);

        if (a == null) {
            try {
                throw new IdealException(
                        IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
            } catch (IdealException e) {
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/adminLogin.jsp";
            }
        } else {
            session = request.getSession(true);
            session.setAttribute("admName", a.getAdmName());
            session.setAttribute("password", a.getPassword());
            System.out.println(a.getAdmName());
            System.out.println(a.getPassword());
            System.out.println("login ok");
            url = "/adminIndex.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
