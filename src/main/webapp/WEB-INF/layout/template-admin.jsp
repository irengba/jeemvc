<%@page pageEncoding="UTF-8"%>
<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/WEB-INF/layout/include.jsp"%>
<%@page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
	<title><tiles:insertAttribute name="title" /></title>
	<tiles:insertAttribute name="headMeta" />
	<tiles:insertAttribute name="headCSS" />
	<tiles:insertAttribute name="headJS" />
	<tiles:insertAttribute name="pageSpecificHead" />
</head>
<body class="${pageType}">

	<div class="container-fluid">
		<tiles:insertAttribute name="pageHeader" />
		<tiles:insertAttribute name="pageBody" />
		<tiles:insertAttribute name="pageFooter" />
	</div>
	
	<input type="hidden" name="contextPath" value="${requestContext.contextPath}" id="contextPath" />
	<c:set var="contextPath" value="${requestContext.contextPath}" scope="request" />
	
	<script>
			var contextPath = $("#contextPath").val();
	</script>
	
	<tiles:insertAttribute name="footerJS" />
	<tiles:insertAttribute name="pageSpecificFooter" />

</body>
</html>
