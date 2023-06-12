<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>FileDownload</title>
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

<%

ArrayList<String> filenames = (ArrayList<String>) request.getAttribute("filenames");
String stringToRemove = "_uuid_name_"; // specify the string to remove from the filename
for (String filename : filenames) {
    String displayName = filename;
    int index = filename.indexOf(stringToRemove);
    if (index != -1) {
        displayName = filename.substring(index + stringToRemove.length());
    }
    String url = request.getContextPath() + "/DownloadServlet?path=" + filename;
    out.println("<a href='" + url + "'>" + displayName + "</a><br>");
}

%>
<!-- <a href=<c:url value='/DownloadServlet?path=filename.txt'/>>filename</a><br/> -->
</form>
<body>
</body>
</html>