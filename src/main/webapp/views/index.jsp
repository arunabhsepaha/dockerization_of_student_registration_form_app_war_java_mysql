<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>     

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form:form action="save" modelAttribute="student" method="POST">

	<h3>Student Registration Form</h3>
	
	<font color='green'>${msg}</font>
		<table>
			<tr>
				<td>Name:</td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td><form:input path="email" /></td>
			</tr>
				<tr>
				<td>Gender:</td>
				<td><form:radiobutton path="gender" value="male"/> Male 
				<form:radiobutton path="gender" value="female"/> Fe-Male</td>
			</tr>

			<tr>
				<td>Course:</td>
				<td><form:select path="course">
						<form:option value="">-Select-</form:option>
						<form:options items="${courses}" />
					</form:select></td>
			</tr>

			<tr>
				<td>Timings:</td>
				<td><form:checkboxes items="${timings}" path="timings" /></td>
			</tr>

			<tr>
				<td></td>
				<td><input type="submit" value="Save" /></td>
			</tr>

		</table>
</form:form>
<a href="viewStudents">View Students</a>
</body>
</html>