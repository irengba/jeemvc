<%@include file="/WEB-INF/layout/template/default/include.jsp"%>
<%@page import="com.web.app.util.JSPIntializer"%>
<%JSPIntializer jspIntializer = new JSPIntializer(request);%>


<header id="header">
	<h2><a href="<c:url value="/" />">Header</a></h2>

	<%if(jspIntializer.isLoggedIn()){%>
			Hi, <%=jspIntializer.getSession().getUser().getEmail()%> |
			<a href="<c:url value='/logout' /> ">Logout</a>
			<h2>Start Payment</h2>
			<form action="<c:url value='/start-payment' />" method="POST">
			    <input type="submit" value="Pay" />
			</form>
	<%}else{%>
			<div id="loginForm">
				<form action="${requestContext.contextPath}/login" method="post">
					<input type="text" name="email" placeholder="Enter Email" id="email" />
					<input type="password" name="password" placeholder="Enter Password" id="password" />
					<input type="submit" name="submit" value="Submit" />
				</form>
			</div>
	<%}%>
	<br/>===${message}
</header>
