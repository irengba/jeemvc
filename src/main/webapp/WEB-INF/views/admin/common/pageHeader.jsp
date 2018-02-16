<%@include file="/WEB-INF/layout/include.jsp"%>
<%@page import="com.app.session.JSPIntializerAdmin"%>
<%JSPIntializerAdmin jspIntializerAdmin = new JSPIntializerAdmin(request);%>

<div class="row">
	<div class="col-md-12">
	
		<header id="header">
			<h2 class="fl"><a href="<c:url value="/admin" />">Admin</a></h2>	
			
			<div class="fr profileSettings">
			<%if(jspIntializerAdmin.isLoggedIn()){%>
					Hi, <%=jspIntializerAdmin.getSession().getAdmin().getEmail()%> | 
					<a href="<c:url value='/admin/logout' /> ">Logout</a>
			<%}%>
			</div>
		</header>
		
	</div>
</div>

