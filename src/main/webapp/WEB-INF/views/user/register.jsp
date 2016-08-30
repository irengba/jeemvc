<%@include file="/WEB-INF/layout/template/default/include.jsp"%>


<h1>Register</h1>

<div id="signupForm">
	<form action="${requestContext.contextPath}/register" method="post">
		<input type="text" name="email" placeholder="Enter Email" id="email" />
		<input type="password" name="password" placeholder="Enter Password" id="password" />
		<input type="submit" name="submit" value="Submit" />
	</form>
</div>
