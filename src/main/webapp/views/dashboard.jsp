<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet" href="[https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css](https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css)">
</head>
<body>
    <div class="container">
        <h2>Dashboard</h2>
        <p>Xin chào, <span class="math-inline">\{user\.fullName\}\!</p\>
<div class\="row"\>
<div class\="col\-md\-4"\>
<div class\="card"\>
<div class\="card\-body"\>
<h5 class\="card\-title"\>Lịch hẹn mới</h5\>
<p class\="card\-text"\></span>{newAppointments}</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">Doanh thu</h5>
                        <p class="card-text"><span class="math-inline">\{revenue\}</p\>
</div\>
</div\>
</div\>
<div class\="col\-md\-4"\>
<div class\="card"\>
<div class\="card\-body"\>
<h5 class\="card\-title"\>Tổng số xe</h5\>
<p class\="card\-text"\></span>{totalCars}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>