<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="jakarta.servlet.jsp.jstl.core.*" %>
<%@ page import="jakarta.servlet.jsp.el.ELException" %>
<% Boolean isAdmin = (Boolean) request.getAttribute("isAdmin"); %>
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
    line-height: 1.6;
}

header {
    background: linear-gradient(to right, #007bff, #0056b3);
    color: white;
    padding: 20px 40px;
    display: flex;
    justify-content: space-between;
    align-items: center;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    width: 100%;
    z-index: 1000;
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
    background-image: url('https://via.placeholder.com/1500x600?text=Chăm+S%C3%B3c+Xe+H%C6%A1i+Chuy%C3%AAn+Nghi%E1%BB%87p');
    background-size: cover;
    background-position: center;
    color: white;
    text-align: center;
    padding: 200px 20px 150px;
    margin-top: 80px;
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
    max-width: 800px;
    margin: 0 auto;
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
    margin-bottom: 20px;
}

.services {
    display: flex;
    justify-content: space-between;
    flex-wrap: wrap;
    margin-top: 50px;
}

.service-card {
    flex-basis: calc(33.333% - 20px);
    background: white;
    border-radius: 8px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    padding: 20px;
    margin-bottom: 30px;
    transition: transform 0.3s ease;
}

.service-card:hover {
    transform: translateY(-5px);
}

.service-card h3 {
    color: #007bff;
    margin-bottom: 15px;
}

.service-card p {
    font-size: 16px;
    color: #666;
}

.logout-form {
    position: absolute;
    top: 40px;
    right: 20px;  
}

.btn-danger {
    background-color: #dc3545;
    color: white;
    border: none;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.btn-danger:hover {
    background-color: #c82333;
}

footer {
    background-color: #333;
    color: white;
    text-align: center;
    padding: 15px 0;
    position: relative;
}

footer a {
    color: #ff5722;
    text-decoration: none;
    font-weight: bold;
}

footer a:hover {
    text-decoration: underline;
}

/* Responsive Design */
@media (max-width: 768px) {
    header {
        flex-direction: column;
        padding: 20px;
    }

    nav {
        margin-top: 20px;
    }

    nav a {
        margin: 5px;
    }

    .hero {
        padding: 150px 20px 100px;
    }

    .hero h1 {
        font-size: 36px;
    }

    .hero p {
        font-size: 18px;
    }

    .service-card {
        flex-basis: 100%;
    }
}

/* Style for the customer information form */
form {
    background-color: #ffffff;
    border-radius: 8px;
    padding: 30px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    max-width: 800px;
    margin: 50px auto;
}

form .form-group {
    margin-bottom: 20px;
}

form label {
    font-size: 16px;
    color: #333;
    font-weight: bold;
    margin-bottom: 5px;
    display: block;
}

form input[type="text"],
form input[type="number"],
form input[type="datetime-local"] {
    width: 100%;
    padding: 12px;
    font-size: 16px;
    border: 1px solid #ddd;
    border-radius: 5px;
    box-sizing: border-box;
    transition: border-color 0.3s ease;
}

form input[type="text"]:focus,
form input[type="number"]:focus,
form input[type="datetime-local"]:focus {
    border-color: #007bff;
    outline: none;
}

form button[type="submit"] {
    background-color: #f1061d;
    color: white;
    padding: 12px 20px;
    font-size: 18px;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
    width: 100%;
}

form button[type="submit"]:hover {
    background-color: #a52935;
}

form .form-group input {
    margin-bottom: 10px;
}

h3 {
    font-size: 24px;
    color: #007bff;
    margin-bottom: 20px;
    text-align: center;
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
        <a href="CustomerServlet?action=list">Quản lý khách hàng</a>
        <% if (isAdmin != null && isAdmin) { %>
            <a href="StaffServlet?action=list">Quản lý nhân viên</a>
        <% } %>
        <a href="SparePartServlet">Quản lý phụ tùng</a>
        <a href="DashboardServlet">Thống kê</a>
    </nav>
    </header>

    <!-- Hero Section -->
    <div class="hero">
        <h1>Chăm Sóc Xe Hơi Chuyên Nghiệp</h1>
        <p>Đưa xe của bạn đến Gara vì tết để tận hưởng dịch vụ bảo dưỡng
            và sửa chữa chất lượng cao nhất.</p>
    </div>
    
    <form action="logout" method="get" class="logout-form">
        <button type="submit" class="btn btn-danger">Đăng xuất</button>
    </form>

    <!-- Content Section -->
    <div class="content">
        <h2>Chào mừng đến với Gara Vì tết</h2>
        <p>Tại Gara Vì tết, chúng tôi cam kết mang đến cho khách hàng dịch vụ chăm sóc xe hơi tốt nhất. Với đội ngũ kỹ thuật viên giàu kinh nghiệm và trang thiết bị hiện đại, chúng tôi đảm bảo xe của bạn sẽ được chăm sóc một cách chuyên nghiệp và tỉ mỉ.</p>
        <p>Chúng tôi cung cấp đa dạng các dịch vụ từ bảo dưỡng định kỳ, sửa chữa lớn đến các dịch vụ chăm sóc xe chuyên sâu. Hãy khám phá các dịch vụ của chúng tôi dưới đây:</p>
        
        <div class="services">
            <div class="service-card">
                <h3>Bảo Dưỡng Định Kỳ</h3>
                <p>Dịch vụ bảo dưỡng định kỳ giúp duy trì hiệu suất tối ưu và kéo dài tuổi thọ cho xe của bạn.</p>
            </div>
            <div class="service-card">
                <h3>Sửa Chữa Chuyên Sâu</h3>
                <p>Đội ngũ kỹ thuật viên của chúng tôi có thể xử lý mọi vấn đề từ đơn giản đến phức tạp của xe hơi.</p>
            </div>
            <div class="service-card">
                <h3>Chăm Sóc Ngoại Thất</h3>
                <p>Dịch vụ rửa xe, đánh bóng và phủ ceramic giúp xe của bạn luôn trong tình trạng hoàn hảo.</p>
            </div>
        </div>
        
                <!-- Customer Information Form -->
        <h3>Điền thông tin liên hệ</h3>
        <form action="submitCustomerInfo" method="post">
            <div class="form-group">
                <label for="fullName">Full Name:</label>
                <input type="text" class="form-control" id="fullName" name="fullName" required>
            </div>
            <div class="form-group">
                <label for="phoneNumber">Phone Number:</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" required>
            </div>
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
                <label for="timestamp">Timestamp:</label>
                <input type="datetime-local" class="form-control" id="timestamp" name="timestamp" required>
            </div>
            <button type="submit" class="btn btn-success">Gửi</button>
        </form>
        
    </div>

    <!-- Footer Section -->
    <footer>
        <p>
            &copy; 2025 Gara Pro. All rights reserved. <a href="/contact">Liên hệ</a>
        </p>
    </footer>
</body>
</html>