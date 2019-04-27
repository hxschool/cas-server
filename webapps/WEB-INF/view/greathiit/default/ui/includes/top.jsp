<!DOCTYPE html>

<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html lang="en">
<head>
  <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>用户登录系统</title>

    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

	<link rel="stylesheet" href="themes/greathiit/css/bootstrap.min.css">
	<link rel="stylesheet" href="themes/greathiit/css/animate.css">
	<link rel="stylesheet" href="themes/greathiit/css/style.css">
  	
    <link rel="icon" href="<c:url value="/favicon.ico" />" type="image/x-icon" />

</head>
	<body class="style-2" style="background-image: url(themes/greathiit/images/bg_2.jpg);">

<div class="container">
			<div class="row">
				<div class="col-md-12">
					<h1 class="logo"><img src="themes/greathiit/images/logo1.png" alt="">
					<span style="display:none;">哈尔滨信息工程学院-毕业论文管理系统</span></h1>
				</div>
			</div>