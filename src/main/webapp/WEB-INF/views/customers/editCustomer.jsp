<!DOCTYPE html>
<html>
<head>
    <title>Edit Customer</title>
</head>
<body>
    <h1>Edit Customer</h1>
    <form action="/customers" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="userId" value="${customer.userId}">
        <label for="fullName">Full Name:</label>
        <input type="text" name="fullName" value="${customer.fullName}" required><br>
        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="${customer.phone}" required><br>
        <button type="submit">Update</button>
    </form>
    <a href="/customers">Back to List</a>
</body>
</html>
