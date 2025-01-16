<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customer List</h1>
<form action="CustomerServlet" method="get">
    <input type="text" name="keyword" placeholder="Search by name, phone, or username">
    <button type="submit" name="action" value="search">Search</button>
</form>
<a href="add-customer.jsp">Add New Customer</a>
<table border="1">
    <tr>
        <th>Full Name</th>
        <th>Phone Number</th>
        <th>Identity Card</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="customer" items="${customers}">
        <tr>
            <td>${customer.fullName}</td>
            <td>${customer.phoneNumber}</td>
            <td>${customer.identityCardNumber}</td>
            <td>
                <a href="edit-customer.jsp?userId=${customer.userId}">Edit</a>
                <a href="CustomerServlet?action=delete&userId=${customer.userId}">Delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
