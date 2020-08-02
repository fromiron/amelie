package amelie;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/UserLoginSvl")
public class UserLoginSvl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLoginSvl() {
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
        String usrId;
        String password;
        String url = "";
        String msg;

        System.out.println("UserLoginSvl");


        HttpSession session = request.getSession(false);

        usrId = request.getParameter("usrId");
        password = request.getParameter("password");

        System.out.println(usrId);
        System.out.println(password);

        User u = User.login(usrId, password);

        if (u == null) {
            try {
                throw new IdealException(
                        IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
            } catch (IdealException e) {
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_NOT_MEMBER_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/userLogin.jsp";
            }
        } else {
            session = request.getSession(true);
            session.setAttribute("usrIndex", u.getUsrIndex());
            session.setAttribute("usrId", u.getUsrId());
            session.setAttribute("usrName", u.getUsrId());
            session.setAttribute("password", u.getUsrId());
            session.setAttribute("address", u.getUsrId());
            session.setAttribute("phone", u.getUsrId());
            session.setAttribute("mail", u.getUsrId());

            url = "/userIndex.jsp";
        }

        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
