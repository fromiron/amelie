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



 <%
     String modeStr = "";
         String mode =null;
           Object reserveFlg =null;
            Object dateSQL = null;
            Object dateLimitSQL =null;
            Object reservePerson = null;
            Object reserveCourseId =null;
            Object reserveCourseName = null;
          try{
       mode = (String)request.getParameter("mode");
        reserveFlg = request.getAttribute("reserveFlg");
        dateSQL = request.getAttribute("dateSQL");
        dateLimitSQL = request.getAttribute("dateLimitSQL");
        reservePerson = request.getAttribute("reservePerson");
        reserveCourseId = request.getAttribute("reserveCourseId");
        reserveCourseName = request.getAttribute("reserveCourseName");
          }

          catch(Exception e){}


          if(Integer.parseInt(mode)==11){
      modeStr = "新規予約";
          }else if(Integer.parseInt(mode)==12){
      modeStr = "予約変更";
          }else{
      modeStr = "予約取消";
          }


          	 ArrayList<TableLoc> allTable = (ArrayList<TableLoc>) request.getAttribute("allTable");
          	 ArrayList<TableLoc> reservedTable = (ArrayList<TableLoc>) request.getAttribute("reservedTable");
 %>



 <div class="join_contents reserve">
  <div class="join_form reserve">


   <div class="reserve__title"><%=modeStr%></div>
   <div class="reserve__title"><%=dateSQL%></div>
   <div class="reserve__title"><%=reservePerson%>名様</div>
   <div class="reserve__title"><%=reserveCourseName%></div>

    <div>テーブル選択</div>

 <%
     for(int i=0; i < allTable.size(); i++){
 %>
 <form method="post" action="ReserveOperationSvl">
  <div id="table__list">
   <input type="hidden" name="mode" value="<%=mode%>" /> <input
    type="hidden" name="tableId"
    value="<%=allTable.get(i).getTableId()%>" /> <label><input
    type="submit" name="tableName"
    class="capacity<%=allTable.get(i).getMaxCapacity()%>"
    value="<%=allTable.get(i).getTableName()%>" /> <%=allTable.get(i).getMaxCapacity()%>人席</label>



  </div>

    <input type="hidden" name="rsvId" value="<%= request.getAttribute("rsvId") %>"/>
    <input type="hidden" name="dateSQL" value="<%=dateSQL %>"/>
    <input type="hidden" name="dateLimitSQL" value="<%=dateLimitSQL %>"/>
    <input type="hidden" name="reservePerson" value="<%=reservePerson %>"/>
    <input type="hidden" name="reserveCourseId" value="<%=reserveCourseId %>"/>
    <input type="hidden" name="reserveCourseName" value="<%=reserveCourseName %>"/>
 </form>

 <%
     }
 %>



<a class="reserve__submit" href="javascript:history.back()">戻る</a>







  </div>
 </div>





 </div>



 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>
<script defer="defer">
	const listAll = document.querySelectorAll("#table__list");
    const capFour =  document.querySelectorAll(".capacity4");
	let person = <%=reservePerson %>;

	let num;
<%for(int i=0; i<reservedTable.size(); i++){%>
	num =<%=reservedTable.get(i).getTableId()%>;
	num -= 1;

	listAll[num].style.display = "none";
<%}%>
if(person>4){
	for(let j=0; j<capFour.length;j++){
		listAll[j].style.display = "none";
	}
}



</script>


</html>