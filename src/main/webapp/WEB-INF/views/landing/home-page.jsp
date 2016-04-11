<%@include file="/WEB-INF/layout/template/default/include.jsp"%>


<h1>Login</h1>
=====${contextPath}===
<div id="loginForm">
	<form action="${requestContext.contextPath}/login.htm" method="post">
		<input type="text" name="email" placeholder="Enter Email" id="email" />
		<input type="password" name="password" placeholder="Enter Password" id="password" />
		<input type="submit" name="submit" value="Submit" />
	</form>
</div>


<script>
	
	/* $(function(){
		$('#loginSubmit').on('click', function(){
			var data = {
				email: $('#loginForm #email').val(),
				password: $('#loginForm #password').val()
			};
			$.post(contextPath+"/login.htm", data , function(data){
				console.log("Login >>>>> ", data);
		    });
		});
		
	}); */

</script>