<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.CarProfile" %><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Profiles</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container">
		<h2>Car Profiles</h2>
		<div>
		<a href="add" class="btn btn-primary mb-3">Add Car</a>
		<a href="home" class="btn btn-secondary ml-2">Back to Home</a>
		</div>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>License Plate</th>
					<th>Brand</th>
					<th>Model</th>
					<th>Year</th>
					<th>VIN</th>
					<th>User ID</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<CarProfile> carProfiles = (List<CarProfile>) request.getAttribute("carProfiles");
				for (CarProfile car : carProfiles) {
				%>
				<tr>
					<td><%=car.getLicensePlate()%></td>
					<td><%=car.getBrand()%></td>
					<td><%=car.getModel()%></td>
					<td><%=car.getYear()%></td>
					<td><%=car.getVin()%></td>
					<td><%=car.getUser().getUserId()%></td>
					<td><a href="edit?carId=<%=car.getCarId()%>"
						class="btn btn-warning btn-sm">Edit</a> <a
						href="delete?carId=<%=car.getCarId()%>"
						class="btn btn-danger btn-sm">Delete</a></td>
				</tr>
				<%
				}
				%>

			</tbody>
		</table>
	</div>
</body>
</html>