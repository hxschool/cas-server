<jsp:directive.include file="includes/top.jsp" />
<div class="row">
	<div class="col-md-4 fh5co-form animate-box" style="height: 400px">
		<div id="msg" class="success">
			<h2>
				<spring:message code="screen.success.header" />
			</h2>
			<p>
				<spring:message code="screen.success.success"
					arguments="${principal.id}" />
			</p>
			<p>
				<spring:message code="screen.success.security" />
			</p>
		</div>
	</div>
</div>
<jsp:directive.include file="includes/bottom.jsp" />

