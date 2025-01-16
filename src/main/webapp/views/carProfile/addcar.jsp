<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Car Profile</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Add Car Profile</h2>
        <form action="add" method="post">
            <div class="form-group">
                <label for="licensePlate">License Plate:</label>
                <input type="text" class="form-control" id="licensePlate" name="licensePlate" required>
            </div>
            <div class="form-group">
                <label for="brand">Brand:</label>
                <input type="text" class="form-control" id="brand" name="brand" required>
            </div>
            <div class="form-group">
                <label for="model">Model:</label>
                <input type="text" class="form-control" id="model" name="model" required>
            </div>
            <div class="form-group">
                <label for="year">Year:</label>
                <input type="number" class="form-control" id="year" name="year" required>
            </div>
            <div class="form-group">
                <label for="vin">VIN:</label>
                <input type="text" class="form-control" id="vin" name="vin" required>
            </div>
            <div class="form-group">
                <label for="userId">User ID:</label>
                <input type="number" class="form-control" id="userId" name="userId" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Car</button>
        </form>
    </div>
</body>
</html>