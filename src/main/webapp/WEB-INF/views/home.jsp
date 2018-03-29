<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page session="false" %>

<h1><s:message code="spitter.welcome" /></h1>
<a href="<c:url value="/spittles" />"><s:message code="spitter.spittles"/></a> |
<a href="<c:url value="/spitter/register" />"><s:message code="spitter.register"/></a>
