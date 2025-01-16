<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Car Profiles</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Car Profiles</h2>
        <a href="add" class="btn btn-primary mb-3">Add Car</a>
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
                <c:forEach items="${carProfiles}" var="car">
                    <tr>
                        <td>${car.licensePlate}</td>
                        <td>${car.brand}</td>
                        <td>${car.model}</td>
                        <td>${car.year}</td>
                        <td>${car.vin}</td>
                        <td>${car.user.userId}</td>
                        <td>
                            <a href="edit?carId=${car.carId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="delete?carId=${car.carId}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>