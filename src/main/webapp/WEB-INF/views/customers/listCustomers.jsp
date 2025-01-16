<!DOCTYPE html>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
    <h1>Customer List</h1>
    <form action="/customers" method="get">
        <input type="text" name="name" placeholder="Search by name">
        <button type="submit" name="action" value="search">Search</button>
    </form>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Full Name</th>
            <th>Phone</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${customers}">
            <tr>
                <td>${customer.userId}</td>
                <td>${customer.username}</td>
                <td>${customer.fullName}</td>
                <td>${customer.phone}</td>
                <td>
                    <a href="/customers?action=edit&userId=${customer.userId}">Edit</a>
                    <a href="/customers?action=delete&userId=${customer.userId}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/addCustomer.jsp">Add New Customer</a>
</body>
</html>
