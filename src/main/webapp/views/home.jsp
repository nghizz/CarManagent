<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="vi">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Gara - Chăm Sóc Xe Hơi Chuyên Nghiệp</title>
<style>
/* Resetting some default styles */
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	font-family: 'Arial', sans-serif;
	background-color: #f9fafb;
	color: #333;
}

header {
	background: linear-gradient(to right, #007bff, #0056b3);
	color: white;
	padding: 20px 40px;
	display: flex;
	justify-content: space-between;
	align-items: center;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
}

header .logo {
	font-size: 32px;
	font-weight: bold;
	text-transform: uppercase;
	letter-spacing: 1.5px;
}

header .logo span {
	color: #ffd700;
}

nav {
	display: flex;
	align-items: center;
}

nav a {
	text-decoration: none;
	color: white;
	margin: 0 15px;
	font-size: 18px;
	padding: 10px 15px;
	border-radius: 5px;
	transition: all 0.3s;
}

nav a:hover {
	background: white;
	color: #ff5722;
}

.hero {
	background-image:
		url('https://via.placeholder.com/1500x600?text=Chăm+S%C3%B3c+Xe+H%C6%A1i+Chuy%C3%AAn+Nghi%E1%BB%87p');
	/* Placeholder image */
	background-size: cover;
	background-position: center;
	color: white;
	text-align: center;
	padding: 150px 20px;
}

.hero h1 {
	font-size: 48px;
	font-weight: bold;
	margin-bottom: 20px;
	text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.5);
}

.hero p {
	font-size: 20px;
	line-height: 1.6;
	text-shadow: 2px 2px 8px rgba(0, 0, 0, 0.5);
}

.content {
	max-width: 1200px;
	margin: 50px auto;
	padding: 20px;
}

.content h2 {
	font-size: 32px;
	color: #333;
	text-align: center;
	margin-bottom: 30px;
}

.content p {
	font-size: 18px;
	color: #555;
	line-height: 1.8;
	text-align: justify;
}

footer {
	background-color: #333;
	color: white;
	text-align: center;
	padding: 15px 0;
	position: relative;
	margin-top: 50px;
}

footer a {
	color: #ff5722;
	text-decoration: none;
	font-weight: bold;
}

footer a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<!-- Header Section -->
	<header>
		<div class="logo">
			Gara <span>Vì tết</span>
		</div>
		<nav>
			<a href="carprofile">Quản lý xe</a>
			<a href="CustomerServlet">Quản lý khách hàng</a>
			<c:if test="${sessionScope.userLogin.role == 'ADMIN'}">
			<a href="StaffServlet">Quản lý nhân viên</a>
			</c:if>
			<a href="SparePartServlet">Quản lý phụ tùng</a>
			<a href="DashboardServlet">Thống kế</a>
		</nav>
	</header>

	<!-- Hero Section -->
	<div class="hero">
		<h1>Chăm Sóc Xe Hơi Chuyên Nghiệp</h1>
		<p>Đưa xe của bạn đến Gara vì tết để tận hưởng dịch vụ bảo dưỡng
			và sửa chữa chất lượng cao nhất.</p>
	</div>
	
	<form action="logout" method="get" style="display: inline;">
            <button type="submit" class="btn btn-danger">Đăng xuất</button>
        </form>

	<!-- Content Section -->
	<div class="content"></div>

	<!-- Footer Section -->
	<footer>
		<p>
			&copy; 2025 Gara Pro. All rights reserved. <a href="/contact">Liên
				hệ</a>
		</p>
	</footer>
</body>
</html>
