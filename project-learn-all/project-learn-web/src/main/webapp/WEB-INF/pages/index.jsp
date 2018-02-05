<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/res/jquery.min.js"></script>
</head>
<body>
welcome to project-learn-web!!!
${jsonVal }
${user }
<script type="text/javascript">
	$(function(){
		$.get("${pageContext.request.contextPath }/index/getData",function(data){
			console.log(data);
		});
	})
</script>
</body>
</html>