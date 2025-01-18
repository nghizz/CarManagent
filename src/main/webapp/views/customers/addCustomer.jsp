<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Customer</title>
    <style>
        /* General Styling */
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1 {
            color: #333;
        }

        /* Form Styling */
        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: 20px 0;
        }

        label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        input[type="text"], input[type="number"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        /* Button Styling */
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        button:hover {
            background-color: #0056b3;
        }
    </style>
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
    <a href="/CustomerServlet?action=list">Back to List</a>
</body>
</html>
