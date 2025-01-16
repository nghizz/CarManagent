<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Staff</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Edit Staff</h2>
       <form action="StaffServlet?action=update" method="post">
            <input type="hidden" name="staffId" value="${staff.staffId}">
            
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" class="form-control" id="name" name="name" value="${staff.name}" required>
            </div>

            <div class="form-group">
                <label for="role">Role:</label>
                <input type="text" class="form-control" id="role" name="role" value="${staff.role}" required>
            </div>

            <div class="form-group">
                <label for="phone">Phone:</label>
                <input type="text" class="form-control" id="phone" name="phone" value="${staff.phone}" required>
            </div>

            <div class="form-group">
                <label for="address">Address:</label>
                <input type="text" class="form-control" id="address" name="address" value="${staff.address}" required>
            </div>

            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save Changes</button>
            </div>
        </form>
    </div>
</body>
</html>
