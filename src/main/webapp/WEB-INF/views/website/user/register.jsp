<%@include file="/WEB-INF/layout/include.jsp"%>


<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<h1>Register</h1>
		<form action="${requestContext.contextPath}/register" method="post">
			<input type="text" name="email" placeholder="Enter Email" />
			<input type="password" name="password" placeholder="Enter Password" />
			<input type="submit" name="submit" value="Submit" />
		</form>
	</div>
</div>