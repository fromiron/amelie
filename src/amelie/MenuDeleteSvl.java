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
 * Servlet implementation class MenuDeleteSvl
 */
@WebServlet (urlPatterns = "/MenuDeleteSvl")
public class MenuDeleteSvl extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuDeleteSvl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String url = "";
        String msg;
        System.out.println("めで");
        String menuID = request.getParameter("menuID");
        String typeID = request.getParameter("typeID");
        //String menuID ="1";
        //String typeID ="100";
        System.out.println("menuID :" + menuID);
        System.out.println("typeID :" + typeID);
        System.out.println("めで2");

        HttpSession session = request.getSession(false);

        // session 처리 에러 getAttribute 사용!
        if (session.getAttribute("admName") == null) {
            url = "/amelie/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        if (Integer.parseInt(typeID) == 100) {
            System.out.println("めで3");
            request.setAttribute("menuID", menuID);
            request.setAttribute("typeID", typeID);
            url = "/courseDelete.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        try {
            url = "/menuDelete.jsp";
			request.setAttribute("mType", MenuType.getAllType());
			request.setAttribute("menuID", menuID);
			request.setAttribute("typeID", typeID);
			RequestDispatcher rd=request.getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (IdealException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			IdealException ie=new IdealException(IdealException.ERR_NO_DB_EXCEPTION);
			msg=ie.getMsg();
			System.out.println(msg);
			request.setAttribute("msg", msg);
			url="/menuDelete.jsp";
		}

	}
}
