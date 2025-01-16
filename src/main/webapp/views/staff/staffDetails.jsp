<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staff Details</title>
<link rel="stylesheet" href="[https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css](https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css)">
</head>
<body>
    <div class="container">
        <h2>Staff Details</h2>
        <p><strong>ID:</strong> ${staff.staffId}</p>
        <p><strong>Name:</strong> ${staff.name}</p>
        <p><strong>Role:</strong> ${staff.role}</p>
        <p><strong>Phone:</strong> ${staff.phone}</p>
        <a href="listStaffs" class="btn btn-primary">Back to List</a>
    </div>
</body>
</html>