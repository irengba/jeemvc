<%@include file="/WEB-INF/layout/include.jsp"%>


<div class="row">
	<div class="col-sm-4 col-sm-offset-4">
		<h1>Home Page</h1>
		<div id="loginForm">
			<form action="${requestContext.contextPath}/admin/login" method="post">
				<input type="number" name="mobile" placeholder="Mobile Number" />
				<input type="password" name="password" placeholder="Password" />
				<input type="submit" name="submit" value="Submit" />
			</form>
		</div>
	</div>
</div>