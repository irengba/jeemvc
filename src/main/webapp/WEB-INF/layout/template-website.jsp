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

	<tiles:insertAttribute name="pageHeader" />
	<div class="<c:choose><c:when test="${gridType eq 'fluid'}">container-fluid</c:when><c:otherwise>container</c:otherwise></c:choose>">
		<tiles:insertAttribute name="pageBody" />
	</div>
	<tiles:insertAttribute name="pageFooter" />
	
	<input type="hidden" name="contextPath" value="${requestContext.contextPath}" id="contextPath" />
	<c:set var="contextPath" value="${requestContext.contextPath}" scope="request" />
	
	<script>
			var contextPath = $("#contextPath").val();
	</script>
	
	<tiles:insertAttribute name="footerJS" />
	<tiles:insertAttribute name="pageSpecificFooter" />

</body>
</html>
