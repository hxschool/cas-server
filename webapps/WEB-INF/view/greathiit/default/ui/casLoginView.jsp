<jsp:directive.include file="includes/top.jsp" />
<%
	String action = request.getParameter("action");
	if (action != null) {
		if (action.equals("getlt")) {
			String callbackName = request.getParameter("callback");
			String jsonData = "{\"execution\":\"" + request.getAttribute("flowExecutionKey") + "\"}";
			String jsonp = callbackName + "(" + jsonData + ")";

			response.setContentType("application/javascript");
			response.getWriter().write(jsonp);
			response.getWriter().close();
		} else if (action.equals("auto")) {
%>


	<form id="credentials" method="POST"
		action="<%=request.getContextPath()%>/login?service=<%=request.getParameter("service")%>">

		<input type="hidden" name="lt" value="${loginTicket}" /> <input
			type="hidden" name="execution" value="${flowExecutionKey}" /> <input
			type="hidden" name="_eventId" value="submit" /> <input type="hidden"
			name="username" value="<%=request.getParameter("user")%>" /> <input
			type="hidden" name="password"
			value="<%=request.getParameter("pwd")%>" /> <input type="submit"
			style="visibility: hidden;" />
	</form>
	<script>
		function doAutoLogin() {
			document.forms[0].submit();
		}
		doAutoLogin();
	</script>
<%	} %>
</body>
</html>

<%
	} else {
%>

<!--
		<div id="cookiesDisabled" class="errors" style="display: none;">
			<h2>
				<spring:message code="screen.cookies.disabled.title" />
			</h2>
			<p>
				<spring:message code="screen.cookies.disabled.message" />
			</p>
		</div>


		<c:if test="${not empty registeredService}">
			<c:set var="registeredServiceLogo" value="images/webapp.png" />
			<c:set var="registeredServiceName" value="${registeredService.name}" />
			<c:set var="registeredServiceDescription"
				value="${registeredService.description}" />

			<c:choose>
				<c:when test="${not empty mduiContext}">
					<c:if test="${not empty mduiContext.logoUrl}">
						<c:set var="registeredServiceLogo" value="${mduiContext.logoUrl}" />
					</c:if>
					<c:set var="registeredServiceName"
						value="${mduiContext.displayName}" />
					<c:set var="registeredServiceDescription"
						value="${mduiContext.description}" />
				</c:when>
				<c:when test="${not empty registeredService.logo}">
					<c:set var="registeredServiceLogo"
						value="${registeredService.logo}" />
				</c:when>
			</c:choose>

			<div id="serviceui" class="serviceinfo">
				<table>
					<tr>
						<td><img src="${registeredServiceLogo}"></td>
						<td id="servicedesc">
							<h1>${fn:escapeXml(registeredServiceName)}</h1>
							<p>${fn:escapeXml(registeredServiceDescription)}</p>
						</td>
					</tr>
				</table>
			</div>
			<p />
		</c:if>


-->

		<div class="row">
			<div class="col-md-4">


				<!-- Start Sign In Form -->

				<form:form class="fh5co-form animate-box" method="post" id="fm1"
					commandName="${commandName}" htmlEscape="true">
					<form:errors path="*" id="msg" cssClass="errors" element="div"
						htmlEscape="false" />
					<h2>
						<spring:message code="screen.welcome.instructions" />
					</h2>
					<div class="form-group">
						<label for="username" class="sr-only">username</label>
						<c:choose>
							<c:when test="${not empty sessionScope.openIdLocalId}">
								<strong><c:out value="${sessionScope.openIdLocalId}" /></strong>
								<input type="hidden" id="username" name="username"
									value="<c:out value="${sessionScope.openIdLocalId}" />" />
							</c:when>
							<c:otherwise>
								<spring:message code="screen.welcome.label.netid.accesskey"
									var="userNameAccessKey" />
								<form:input cssClass="required form-control"
									placeholder="please input username" cssErrorClass="error"
									id="username" size="25" tabindex="1"
									accesskey="${userNameAccessKey}" path="username"
									autocomplete="off" htmlEscape="true" class="form-control" />


							</c:otherwise>
						</c:choose>
					</div>
					<div class="form-group">
						<label for="password" class="sr-only">pwd</label>
						<spring:message code="screen.welcome.label.password.accesskey"
							var="passwordAccessKey" />
						<form:password cssClass="required form-control"
							placeholder="please input password" cssErrorClass="error"
							id="password" size="25" tabindex="2" path="password"
							accesskey="${passwordAccessKey}" htmlEscape="true"
							autocomplete="off" />
						<span id="capslock-on" style="display: none;"><p>
								<img src="images/warning.png" valign="top">
								<spring:message code="screen.capslock.on" />
							</p></span>
					</div>

					<div class="form-group">

						<section class="row btn-row">

							<input type="hidden" name="execution" value="${flowExecutionKey}" />
							<input type="hidden" name="_eventId" value="submit" /> <input
								class="btn btn-primary" name="submit" accesskey="l"
								value="<spring:message code="screen.welcome.button.login" />"
								tabindex="6" type="submit" /> <input class="btn btn-reset"
								name="reset" accesskey="c"
								value="<spring:message code="screen.welcome.button.clear" />"
								tabindex="7" type="reset" />
						</section>
					</div>
				</form:form>
				<!-- END Sign In Form -->

			</div>
		</div>
		<%
			}
		%>
		<jsp:directive.include file="includes/bottom.jsp" />
