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
<!-- TODO 全ページにコピペする  -->

</head>

<body>

<!-- 全ページにコピペする  -->
 <!-- session ゲット -->

 <%
     request.setCharacterEncoding("UTF-8");
     String sessionId = null;
     String sessionName = null;
     String loginFailed = null;
     String sessionAdmName = null;
     try {
     sessionAdmName = (String) session.getAttribute("admName");
         sessionId = (String) session.getAttribute("usrId");
         sessionName = (String) session.getAttribute("usrName");
         loginFailed = (String) request.getAttribute("msg");

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
      if (sessionId == null && sessionAdmName==null) {
  %>

  <div class="nav_list_btn">
   <a href="./userLogin.jsp">login</a>
  </div>
  <div class="nav_list_btn">
   <a href="userInsert.jsp">join</a>
  </div>

  <%
      } else if(sessionId != null) {
  %>
  <div class="nav_list_btn">
   <a href="userIndex.jsp">my page</a>
  </div>
  <%
      }else if(sessionAdmName !=null) {
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
      if (sessionId == null && sessionAdmName ==null) {
  %>
  <a href="adminLogin.jsp"><i class="fas fa-user-cog icon_hover"></i></a>
  <% }else{ %>
  <a href="./UserLogoffSvl"><span class="logout">logout</span></a><% } %>
 </div>


 </nav>
 <div class="menuDropBtn">
  <i class="fas fa-chevron-down"></i>
 </div>
 <msg class="user_msg" display="hidden"></msg>
 <!-- TODO 全ページにコピペする  -->




<div class="login_contents">


        <div class="login_form">
            <form method="post" action="UserLoginSvl">
                <div class="login__logo_wrapper">
                    <i class="fab fa-accusoft"></i>
                    <div>Amelie</div>
                </div>
                <div class="login_form__innerWrapper">
                    <div class="login_input_wrapper">
                        <div class="login_sub_title">
                            <div class="login_sub_title__title">USER ID</div>
                            <div class="login_input_text_wrapper"><input class="login__text" type="text" name="usrId" placeholder="IDを入力してください。" required /></div>
                        </div>
                        <div class="login_sub_title">
                            <div class="login_sub_title__title">USER Pass</div>
                            <div class="login_input_text_wrapper"><input class="login__text" type="password" name="password"  placeholder="パスワードを入力してください。" required /></div>
                        </div>
                    </div>
                    <div class="login_submit_wrapper">
                        <input class="login__submit" type="submit" value="LOGIN" />
                        <a href="userInsert.jsp" class="login__submit">JOIN</a>
                    </div>
                    <% if(loginFailed !=null){ %>
                        <div class="login__failed"><%=loginFailed %></div>
                        <%} %></div>
            </form>
        </div>
    </div>











 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>