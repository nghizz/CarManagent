<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staffs List</title>
<link rel="stylesheet"
	href="[https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css](https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css)">
</head>
<body>
	<div class="container">
		<h2>Staffs List</h2>
		<a href="StaffServlet?action=add" class="btn btn-primary mb-3">Add Staff</a>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>ID</th>
					<th>Name</th>
					<th>Role</th>
					<th>Phone</th>
					<th>Address</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="staff" items="${staffList}">
					<tr>
						<td>${staff.userId}</td>
						<td>${staff.fullName}</td>
						<td>${staff.role}</td>
						<td>${staff.phoneNumber}</td>
						<td><a href="StaffServlet?action=edit&userId=${staff.userId}"
							class="btn btn-warning btn-sm">Edit</a> <a
							href="StaffServlet?action=delete&userId=${staff.userId}"
							class="btn btn-danger btn-sm">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>