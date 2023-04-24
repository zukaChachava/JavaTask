<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello Zura" %></h1>
<br/>
<a href="post">See Posts</a>
<br/>
<form action="post" method="post">
  <input type="text" placeholder="Header" name="header"/>
  <input type="text" placeholder="Body" name="body"/>
  <input type="text" placeholder="Author" name="author"/>
  <button type="submit">Save</button>
</form>
</body>
</html>