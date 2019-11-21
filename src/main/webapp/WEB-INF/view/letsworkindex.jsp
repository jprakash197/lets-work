<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
<head>
<style>
body {background-color: powderblue;}
h1   {color: blue;font: 300%;text-align:center}
h2   {color: blue;font: 180%;}
p    {color: red;font: 100%;}
</style>
<meta charset="ISO-8859-1">
<title>LET'S WORK</title>
</head>
<body>
    <h1>WELCOME TO LET'S WORK</h1><br><br>
	<h2>LET'S WORK PROPERTIES</h2><br>
	<p>TITLE &nbsp; : &nbsp; ${work.title}</p>
	<p>PROJECT &nbsp; : &nbsp; ${work.project}</p>
    <p>VERSION  &nbsp; : &nbsp; ${work.version}</p>
    <p>ENVIRONMENT &nbsp; : &nbsp; ${work.environment}</p>
</body>
</html>