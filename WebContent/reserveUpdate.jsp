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
 <link rel="stylesheet" href="public/css/reserveCourse.css" />
<script src="./public/js/nav.js" defer="defer"></script>
<script src="./public/js/bg.js" defer="defer"></script>



<script type="text/javascript" defer="defer">

function fillWhite(){
    const otherTarget =  document.querySelectorAll(".reserveCourse");

    for(i=0; i<otherTarget.length; i++){
        otherTarget[i].classList.remove("selected");
    }

}

	window.onload = function() {
		const hour = document.querySelector("#hour");
		const minute = document.querySelector("#minute");
		const person = document.querySelector("#person");
		const course = document.querySelector("#reserveCourse__wrapper");
		const courseIdChk = <%= request.getAttribute("courseId") %>;

		const radio = document.querySelectorAll("#radio");

		radio[courseIdChk-1].checked=true;

		for (var i = 0; i < hour.options.length; i++) {
			if (hour.options[i].value == <%=request.getAttribute("hour")%>
	) { 	hour.options[i].selected = true;
				break;
			}
		}


		if (<%=request.getAttribute("minute")%>==0){
            minute.options[1].selected = true;
            }else{

		for (var i = 0; i < minute.options.length; i++) {

            if (minute.options[i].value == <%=request.getAttribute("minute")%>) {
            	minute.options[i].selected = true;
                break;
            }
        }
     }
		  for (var i = 0; i < person.options.length; i++) {
	            if (person.options[i].value == <%=request.getAttribute("person")%>
	    ) {     person.options[i].selected = true;
	                break;
	            }
	        }


		    const reserveCourse =  document.querySelectorAll(".menu_list__menu_title");

		    const courseId =  document.getElementById("courseId");
		    const courseName =  document.getElementById("courseName");
		    const msgBox = document.querySelector("#reserve__msg");

		    courseId.value = courseIdChk;


		for(i =0; i<reserveCourse.length;i++){
		    reserveCourse[i].addEventListener('click', e => {
		        parent = e.currentTarget;
		        fillWhite();
		        parent.classList.add("selected");

		        console.log("course "+parent.firstElementChild.nextElementSibling.textContent);
		        console.log("courseId is "+parent.firstElementChild.innerText);

		        courseName.value=parent.firstElementChild.nextElementSibling.textContent;
		        courseId.value=parent.firstElementChild.innerText;

		    });

		    }


	}
</script>


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

     <input type="hidden" name="mode" value="12" /> <input type="date"
      name="date" id="dateForm"
      value="<%=request.getAttribute("date")%>" required />

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
    <input type="hidden" name="rsvId" value="<%=request.getAttribute("rsvId")%>"/> <input
     id="courseId" type="hidden" name="courseId" />
       <input class="reserve__submit" type="submit" value="変更"/>


   </form>

  </div>
 </div>







 <!-- TODO 全ページにコピペする  -->
 <div class="home__bg_cover"></div>
 <div id="home__bg"></div>
 <!-- TODO 全ページにコピペする  -->

</body>

</html>