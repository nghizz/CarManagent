<!DOCTYPE html>
<html>
<head>
    <title>Add Customer</title>
</head>
<body>
    <h1>Add Customer</h1>
    <form action="CustomerServlet?action=add" method="post">
        <input type="hidden" name="action" value="add">
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>
        <label for="fullName">Full Name:</label>
        <input type="text" name="fullName" required><br>
        <label for="phone">Phone:</label>
        <input type="text" name="phone" required><br>
        <button type="submit">Add</button>
    </form>
    <a href="/customers">Back to List</a>
</body>
</html>
