<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Customer Details</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Customer Details</h2>
        <p><strong>ID:</strong> ${customer.customerId}</p>
        <p><strong>Name:</strong> ${customer.name}</p>
        <p><strong>Phone:</strong> ${customer.phone}</p>
        <a href="listCustomers" class="btn btn-primary">Back to List</a>
    </div>
</body>
</html>