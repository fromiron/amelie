package amelie;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet (urlPatterns = "/CourseOperationSvl")
public class CourseOperationSvl extends HttpServlet {
    private static final long  serialVersionUID   = 1L;

    public static final int    INSERT             = 21;
    public static final int    UPDATE             = 22;
    public static final int    DELETE             = 23;
    public static final String COURSE_MENU_TYPE[] = { "appetizerID", "soupID",
            "pastaID", "meatID", "fishID", "dessertID" };

    public CourseOperationSvl() {
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
        int mode = 0;
        String courseName = "";
        String detail = "";
        int orderFlg = 0;
        int price = 0;
        int priority = 0;
        int typeID = 0;
        int courseId = 0;
        int menuId = 0;
        String msg;
        Course c = null;
        HttpSession session = request.getSession(false);

        if (session == null) {
            url = "/amelie/home.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }

        courseName = request.getParameter("courseName");
        detail = request.getParameter("detail");
        System.out.println(courseName);
        System.out.println(detail);
        try {
            mode = Integer.parseInt(request.getParameter("mode"));
        } catch (Exception e) {
            e.printStackTrace();
            mode = 100;
        }
        try {
            orderFlg = Integer.parseInt(request.getParameter("orderFlg"));
            typeID = 100;
            price = Integer.parseInt(request.getParameter("price"));
            priority = Integer.parseInt(request.getParameter("priority"));
            System.out.println(orderFlg);

            System.out.println(price);
            System.out.println(priority);
        } catch (Exception e) {
            e.printStackTrace();

        }

        if (mode == INSERT) {
            System.out.println("course insert");
            c = new Course();
            c.setCourseName(courseName);
            c.setDetail(detail);
            c.setOrderFlg(orderFlg);
            c.setTypeId(typeID);
            c.setCourseId(courseId);
            c.setPrice(price);
            c.setPriority(priority);
            ArrayList<CourseCtl> al = new ArrayList<CourseCtl>();
            for (int i = 0; i < COURSE_MENU_TYPE.length; i++) {
                menuId = Integer.parseInt(request
                        .getParameter(COURSE_MENU_TYPE[i]));
                if (menuId != 0) {
                    CourseCtl cc = new CourseCtl();
                    cc.setM_Id(menuId);
                    System.out.println(i + "CourseCtl menu id :" + menuId);
                    al.add(cc);
                }
            }

            try {
                Course.updateCourse(c, mode, al);
            } catch (IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/menuInsert.jsp";
            }

        } else if (mode == UPDATE) {
            courseId = Integer.parseInt(request.getParameter("courseId"));
            System.out.println("あお" + courseId);
            c = new Course();
            c.setCourseName(courseName);
            c.setDetail(detail);
            c.setOrderFlg(orderFlg);
            c.setCourseId(courseId);
            c.setTypeId(typeID);
            c.setPrice(price);
            c.setPriority(priority);
            ArrayList<CourseCtl> al = new ArrayList<CourseCtl>();
            for (int i = 0; i < COURSE_MENU_TYPE.length; i++) {
                menuId = Integer.parseInt(request
                        .getParameter(COURSE_MENU_TYPE[i]));
                if (menuId != 0) {
                    CourseCtl cc = new CourseCtl();
                    cc.setM_Id(menuId);
                    System.out.println(i + "あお2" + menuId);
                    al.add(cc);
                }
            }
            for (CourseCtl cl : al) {
                System.out.println("あお3" + cl.getM_Id());
            }

            try {
                Course.updateCourse(c, mode, al);
            } catch (IdealException e) {
                e.printStackTrace();
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_DB_EXCEPTION);
                msg = ie.getMsg();
                System.out.println(msg);
                request.setAttribute("msg", msg);
                url = "/menuUpdate.jsp";
            }

        } else if (mode == DELETE) {
            courseId = Integer.parseInt(request.getParameter("courseId"));
            c = new Course();
            c.setCourseId(courseId);
            @SuppressWarnings("unused")
            ArrayList<CourseCtl> al = new ArrayList<CourseCtl>();
            try {
                System.out.println("reserveCourseChk booean chk");
                System.out.println(Reserve.reserveCourseChk(courseId));
            } catch (IdealException e1) {
                e1.printStackTrace();
            }

            boolean reserveCnt = false;
            try{
            reserveCnt = Reserve.reserveCourseChk(courseId);}catch(Exception e){}
            System.out.println("reserveCnt is "+reserveCnt);
            if (reserveCnt) {
                IdealException ie = new IdealException(
                        IdealException.ERR_NO_NOT_RESERV_DELETE);
                msg = ie.getMsg();
                System.out.println(msg);
                try {
                    request.setAttribute("courseID", courseId);
                    request.setAttribute("typeID", "100");
                    request.setAttribute("msg", msg);

                } catch (Exception e) {
                }

                url = "/courseDelete.jsp";
                RequestDispatcher rd = request.getRequestDispatcher(url);
                rd.forward(request, response);
            } else {

                try {
                    Course.updateCourse(c, mode, al);
                } catch (IdealException e) {
                    e.printStackTrace();
                    IdealException ie = new IdealException(
                            IdealException.ERR_NO_DB_EXCEPTION);
                    msg = ie.getMsg();
                    System.out.println(msg);
                    request.setAttribute("msg", msg);
                    url = "/courseDelete.jsp";
                    RequestDispatcher rd = request
                            .getRequestDispatcher(url);
                    rd.forward(request, response);

                }

            }

        }

        request.setAttribute("typeID", "100");
        url = "/MenuMaintenanceSvl";
        System.out.println("こ4" + typeID);
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);

    }

}
