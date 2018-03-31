<%@include file="/WEB-INF/layout/include.jsp"%>


<div class="row justify-content-center">
	<div class="col-md-3">
		<h1>Register</h1>
		<form action="${requestContext.contextPath}/register" method="post">
			<div class="form-group">
				<input type="text" name="email" placeholder="Enter Email" class="form-control" />
			</div>
			<div class="form-group">
				<input type="password" name="password" placeholder="Enter Password" class="form-control" />
			</div>
			<div class="form-group">
				<input type="submit" name="submit" value="Submit" class="btn btn-primary" />
			</div>
		</form>
	</div>
</div>