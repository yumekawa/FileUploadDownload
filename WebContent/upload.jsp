<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FileUpload</title>
</head>
<script>
function doSubmit(servlet_name){
	frm.action=servlet_name;
	frm.method="post";
	frm.submit();
}
</script>
<body>
<form name=frm enctype="multipart/form-data" >
<%--上传者--%>
<input type="text" name="name">
<%--上传文件--%>
<input type="file" name="myfile"> <br>
<input type=button value="上传" onclick="doSubmit('UploadServlet');"><br>
<input type=button value="下载页面" onclick="doSubmit('ReadFolder');"><br>
</form>
<body>
</body>
</html>