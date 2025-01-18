<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
    body {
        background-color: #f8f9fa;
        padding-top: 20px;
    }
    .container {
        background-color: white;
        border-radius: 10px;
        padding: 20px;
        box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h2 {
        color: #007bff;
        margin-bottom: 20px;
    }
    .card {
        margin-bottom: 20px;
        border: none;
        box-shadow: 0 0 5px rgba(0,0,0,0.1);
    }
    .card-title {
        color: #28a745;
    }
    .card-text {
        font-size: 24px;
        font-weight: bold;
    }
</style>
</head>
<body>
    <div class="container">
        <h2>Dashboard</h2>
        <p>Xin chào, ${user.fullName}!</p>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Lịch hẹn mới</h5>
                        <p class="card-text">${newAppointments}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Doanh thu</h5>
                        <p class="card-text">${revenue}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Tổng số xe</h5>
                        <p class="card-text">${totalCars}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>