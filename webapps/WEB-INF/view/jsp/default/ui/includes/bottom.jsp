<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="Java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
</div> <!-- END #content -->

<footer>
  <div id="copyright" class="container">
    <p>Copyright @ 1998-2017 Harbin Institute Of Information Technology All Rights Reservd 黑ICP备05007064号</p>
        <p>学院地址：哈尔滨市宾西经济技术开发区大学城9 号　　邮编：150025 　 电话：13284997565
            <%=org.jasig.cas.CasVersion.getDateTime()%></p>
  </div>
</footer>

</div> <!-- END #container -->

<script src="https://cdnjs.cloudflare.com/ajax/libs/headjs/1.0.3/head.min.js"></script>
<spring:theme code="cas.javascript.file" var="casJavascriptFile" text="" />
<script type="text/javascript" src="<c:url value="${casJavascriptFile}" />"></script>



       <div class="close"><a href="javascript:close()">关闭</a></div>
    </div>
</body>
</html>

