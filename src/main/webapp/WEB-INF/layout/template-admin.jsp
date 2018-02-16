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
	<tiles:insertAttribute name="pageSpecific" />
</head>
<body class="${pageType}">

	<tiles:insertAttribute name="pageHeader" />
	<tiles:insertAttribute name="pageBody" />
	<tiles:insertAttribute name="pageFooter" />
	<tiles:insertAttribute name="footerJS" />
	
	<input type="hidden" name="contextPath" value="${requestContext.contextPath}" id="contextPath" />
	<c:set var="contextPath" value="${requestContext.contextPath}" scope="request" />
	
	<script>
			var contextPath = $("#contextPath").val();
	</script>

</body>
</html>
