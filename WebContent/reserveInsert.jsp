<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="amelie.*"%>
<!DOCTYPE html
    PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Ristorante Amelie</title>

<!-- TODO 全ページにコピペする  -->
<meta name="viewport" content="width=device-width, user-scalable=1" />
<link rel="stylesheet" href="public/css/reset.css" />
<link rel="stylesheet" href="public/css/stylesheet.css" />
<link
 href="https://fonts.googleapis.com/css2?family=Noto+Serif+JP:wght@300;400;500;600;700&display=swap"
 rel="stylesheet" />
<link rel="stylesheet"
 href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
 integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
 crossorigin="anonymous" />
<script src="./public/js/nav.js" defer="defer"></script>
<script src="./public/js/bg.js" defer="defer"></script>
<script src="./public/js/date.js" defer="defer"></script>
<script src="./public/js/reserve.js" defer="defer"></script>

<!-- TODO 全ページにコピペする  -->

</head>

<body>

 <!-- 全ページにコピペする  -->
 <!-- session ゲット -->

 <%
     request.setCharacterEncoding("UTF-8");
      String sessionId = null;
      String sessionName = null;
      String sessionAdmName = null;
      try {
          sessionAdmName = (String) session.getAttribute("admName");
          sessionId = (String) session.getAttribute("usrId");
          sessionName = (String) session.getAttribute("usrName");
      } catch (Exception e) {
      }
 %>
 <!-- session ゲット -->


 <nav class="nav"> <a class="nav__logo" href="home.jsp">
  <div class="nav__logo_wrapper">
   <i class="fab fa-accusoft"></i>
   <div>Amelie</div>
  </div>
 </a>
 <div class="nav__list">
  <div class="nav_list_btn">
   <a href="./ShowMenuSvl">menu</a>
  </div>

  <%
      if (sessionId == null && sessionAdmName == null) {
  %>

  <div class="nav_list_btn">
   <a href="userLogin.jsp">login</a>
  </div>
  <div class="nav_list_btn">
   <a href="userInsert.jsp">join</a>
  </div>

  <%
      } else if (sessionId != null) {
  %>
  <div class="nav_list_btn">
   <a href="userIndex.jsp">my page</a>
  </div>
  <%
      } else if (sessionAdmName != null) {
  %>
  <div class="nav_list_btn">
   <a href="adminIndex.jsp">admin page</a>
  </div>

  <%
      }
  %>


  <div class="nav_list_btn">
 <a href="contact.jsp">contact</a>  </div>
 </div>

 <div class="nav__admin">

  <%
      if (sessionId == null && sessionAdmName == null) {
  %>
  <a href="adminLogin.jsp"><i class="fas fa-user-cog icon_hover"></i></a>
  <%
      } else {
  %>
  <a href="./UserLogoffSvl"><span class="logout">logout</span></a>
  <%
      }
  %>
 </div>


 </nav>
 <div class="menuDropBtn">
  <i class="fas fa-chevron-down"></i>
 </div>
 <msg class="user_msg" display="hidden"></msg>
 <!-- TODO 全ページにコピペする  -->



 <div class="join_contents reserve">
  <div class="join_form reserve">


   <form id="reserveForm" method="post" action="ReserveTableSvl">

    <div class="reserveDate">

     <input type="hidden" name="mode" value="11" /> <input type="date"
      name="date" id="dateForm" required />

    </div>

    <div class="reserve__selectWrapper">
     <select name="hour" id="hour" required>
      <option value="">時間</option>
      <option value="17">17</option>
      <option value="18">18</option>
      <option value="19">19</option>
      <option value="20">20</option>
      <option value="21">21</option>
     </select> <select name="minute" id="minute" required>
      <option value="">分</option>
      <option value="00">00</option>
      <option value="15">15</option>
      <option value="30">30</option>
      <option value="45">45</option>
     </select>

 <select name="person" id="person" required>
     <option value="">人数</option>
     <option value="1">1名様</option>
     <option value="2">2名様</option>
     <option value="3">3名様</option>
     <option value="4">4名様</option>
     <option value="5">5名様</option>
     <option value="6">6名様</option>
    </select>


    </div>






    <div id="reserveCourse__wrapper">
     <%
         ArrayList<Course> course = Course.getOneCourseList();

               for (int i = 0; i < course.size(); i++) {
           Course c = course.get(i);
           if (c.getPriority() == 2) {
     %>

     <div class="menu_list__menu_title reserveCourse recommend">
    <div id="getCourseId" style="display: none"><%=c.getCourseId()%></div>
      <label class="reserve__course">
      <input id="radio" name="radio" type="radio"/>
      <span><%=c.getCourseName()%> <i class="fas fa-award"></i> おすすめ</span><br/>
      <span class="menu_list__menu_detail"><%=c.getDetail()%></span><br />￥<%=c.getPrice()%>
      </label></div>

     <%
         } else {
     %>

     <div class="menu_list__menu_title reserveCourse">
      <div id="getCourseId" style="display: none"><%=c.getCourseId()%></div>
      <label class="reserve__course">
      <input id="radio" name="radio" type="radio"/>
      <span><%=c.getCourseName()%></span><br/>
      <span class="menu_list__menu_detail"><%=c.getDetail()%></span><br />￥<%=c.getPrice()%>
      </label></div>
     <%
         }
               }
     %>
     <div id="reserve__msg"></div>
    </div>

    <input id="courseName" type="hidden" name="courseName" />
    <input id="courseId" type="hidden" name="courseId" />
    <input class="reserve__submit" type="submit" value="すすむ"/>





   </form>



  </div>
 </div>


 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->



</body>

</html>