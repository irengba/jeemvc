<%@include file="/WEB-INF/layout/include.jsp"%>
<%@page import="com.app.session.JSPIntializerWeb"%>
<%JSPIntializerWeb jspIntializer = new JSPIntializerWeb(request);%>


<header id="header">
	<h2><a href="<c:url value="/" />">Header</a></h2>	
	
	<%if(jspIntializer.isLoggedIn()){%>
			Hi, <%=jspIntializer.getSession().getUser().getEmail()%> | 
			<a href="<c:url value='/logout' /> ">Logout</a>
	<%}else{%>
			<div id="loginForm">
				<input type="text" name="email" placeholder="Enter Email" id="email" />
				<input type="password" name="password" placeholder="Enter Password" id="password" />
				<input type="submit" name="submit" value="Submit" id="loginSubmit" />
			</div>
	<%}%>
</header>


<script>
	
	$(function(){
		$('#loginSubmit').on('click', function(){
			var data = {
				email: $('#loginForm #email').val(),
				password: $('#loginForm #password').val()
			};
			$.post(contextPath+"/login", data , function(data){
				location.reload();
		    });
		});
		
	});

</script>