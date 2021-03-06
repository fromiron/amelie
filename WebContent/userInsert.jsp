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
<script src="./public/js/join.js" defer="defer"></script>

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
      if (sessionId == null && sessionAdmName==null) {
  %>

  <div class="nav_list_btn">
   <a href="userLogin.jsp">login</a>
  </div>
  <div class="nav_list_btn">
   <a href="./userInsert.jsp">join</a>
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

 <!-- TODO 全ページにコピペする  -->


<div class="join_contents">
        <div class="join_form">
            <form class="join_form__form" method="post" action="UserOperationSvl" >
                <div class="join__logo_wrapper">
                    <i class="fab fa-accusoft"></i>
                    <div>Amelie</div>
                </div>
                <div class="join_form__innerWrapper">
                    <div class="join_input_wrapper">
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">USER ID</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="userId" type="text" name="usrId" placeholder="使用するIDを入力してください。" required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">USER Pass</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="password" type="password" name="password" placeholder="パスワードを入力してください。" required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">USER Pass - Repeat</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="password2" type="password" name="password2" placeholder="パスワードを再度確認してください。"required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">Name</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="userName" type="text" name="usrName" placeholder="お名前を入力してください。" required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">Address</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="address" type="text" name="address"  placeholder="住所を入力してください。" required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">e-Mail</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="mail" type="email" name="mail"  placeholder="メールを入力してください。" required />
                            </div>
                        </div>
                        <div class="join_sub_title">
                            <div class="join_sub_title__title">TEL</div>
                            <div class="join_input_text_wrapper"><input class="join__text" id="tel" type="text" name="phone" placeholder="ご連絡先を入力してください。" required />
                            </div>
                        </div>
                    </div>
                    <div class="join_submit_wrapper">
                        <input type="hidden" name="mode" value="11"/>
                        <input class="join__submit" type="button" value="JOIN" />
                    </div>
                     </div>
            </form>
            <% if(request.getAttribute("msg")!=null){ %>
            <div style="margin-top:10px;"><%=request.getAttribute("msg")%></div>
            <%} %>

        </div>
    </div>






 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>