    <%@ page contentType="text/html; charset=UTF-8" language="java" %>
      <%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer List</title>
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

        /* Table Styling */
        table {
            width: 100%;
            margin-top: 20px;
            border-collapse: collapse;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* Link Styling */
        a {
            color: #007bff;
            text-decoration: none;
        }

        a:hover {
            text-decoration: underline;
        }

        /* Form Styling */
        form {
            margin-bottom: 20px;
        }
    </style>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <h1>Customer List</h1>
    <form action="CustomerServlet" method="get">
        <input type="text" name="keyword" placeholder="Search by name, phone, or username">
        <button type="submit" name="action" value="search">Search</button>
    </form>
    <button class="btn btn-primary" data-toggle="modal" data-target="#addCustomerModal">Add Customer</button>
    <a href="home" class="btn btn-secondary ml-2">Back to Home</a>

    <table>
        <tr>
            <th>Full Name</th>
            <th>Phone Number</th>
            <th>Identity Card</th>
            <th>Actions</th>
        </tr>
 
<%
    List<User> customers = (List<User>) request.getAttribute("customers");
    for (User customer : customers) {
%>
        <tr>
            <td><%= customer.getFullName() %></td>
            <td><%= customer.getPhoneNumber() %></td>
            <td><%= customer.getIdentityCardNumber() %></td>
            <td>
                            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editCustomerModal"
                                    data-id="${customer.userId}" data-name="${customer.fullName}" data-phone="${customer.phoneNumber}">
                                Edit
                            </button>
                <a href="CustomerServlet?action=delete&userId=<%= customer.getUserId() %>">Delete</a>
            </td>
        </tr>
<%
    }
%>

    </table>

    <!-- Modal for Add Customer -->
    <div class="modal fade" id="addCustomerModal" tabindex="-1" role="dialog" aria-labelledby="addCustomerModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="addCustomerModalLabel">Add Customer</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="CustomerServlet?action=add" method="post">
                        <input type="hidden" name="action" value="add">
                        <div class="form-group">
                            <label for="username">Username:</label>
                            <input type="text" class="form-control" name="username" required><br>
                        </div>
                        <div class="form-group">
                            <label for="fullName">Full Name:</label>
                            <input type="text" class="form-control" name="fullName" required><br>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" required><br>
                        </div>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Modal for editing customer -->
    <div class="modal fade" id="editCustomerModal" tabindex="-1" role="dialog" aria-labelledby="editCustomerModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="editCustomerModalLabel">Edit Customer</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="CustomerServlet?action=update" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="userId" id="userId">
                        <div class="form-group">
                            <label for="fullName">Full Name:</label>
                            <input type="text" class="form-control" name="fullName" id="fullName" required>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" id="phone" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Update</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script>
        // JavaScript to fill the modal with customer data when clicking "Edit"
        $('#editCustomerModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var userId = button.data('id'); // Extract customer id from data-* attributes
            var fullName = button.data('name');
            var phoneNumber = button.data('phone');

            var modal = $(this);
            modal.find('#userId').val(userId);
            modal.find('#fullName').val(fullName);
            modal.find('#phone').val(phoneNumber);
        });
    </script>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
