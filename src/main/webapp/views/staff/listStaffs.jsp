<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Staffs List</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<style>
body {
    background-color: #f8f9fa;
    font-family: Arial, sans-serif;
}

.container {
    max-width: 900px;
    margin-top: 50px;
    padding: 30px;
    background-color: #ffffff;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

h2 {
    text-align: center;
    margin-bottom: 30px;
    font-size: 24px;
}

table {
    margin-top: 20px;
}

.btn {
    margin-right: 10px;
}
</style>
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
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> staffList = (List<User>) request.getAttribute("staffList");
                    for (User staff : staffList) {
                %>
                    <tr>
                        <td><%= staff.getUserId() %></td>
                        <td><%= staff.getFullName() %></td>
                        <td><%= staff.getRole() %></td>
                        <td><%= staff.getPhoneNumber() %></td>
                        <td>
                            <a href="StaffServlet?action=edit&userId=<%= staff.getUserId() %>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="StaffServlet?action=delete&userId=<%= staff.getUserId() %>" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
</body>
</html>
