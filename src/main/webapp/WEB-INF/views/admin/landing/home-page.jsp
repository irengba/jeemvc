<%@include file="/WEB-INF/layout/include.jsp"%>


<div class="row justify-content-center">
	<div class="col-md-3">
		<h1>Home Page</h1>
		<div id="loginForm">
			<form action="${requestContext.contextPath}/admin/login" method="post">
				<div class="form-group">
					<input type="number" name="mobile" placeholder="Mobile Number" class="form-control" />
				</div>
				<div class="form-group">
					<input type="password" name="password" placeholder="Password" class="form-control" />
				</div>
				<div class="form-group">
					<input type="submit" name="submit" value="Submit" class="btn btn-primary" />
				</div>
			</form>
		</div>
	</div>
</div>