<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entity.SpareParts" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Spare Parts List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h2>Spare Parts List</h2>
        <a href="addSparePart" class="btn btn-primary mb-3">Add Spare Part</a>
        <a href="home" class="btn btn-secondary ml-2">Back to Home</a>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Get the list of spare parts from the request object
                    List<SpareParts> spareParts = (List<SpareParts>) request.getAttribute("spareParts");
                    for (SpareParts sparePart : spareParts) {
                %>
                    <tr>
                        <td><%= sparePart.getPartId() %></td>
                        <td><%= sparePart.getPartName() %></td>
                        <td><%= sparePart.getDescription() %></td>
                        <td><%= sparePart.getPrice() %></td>
                        <td>
                            <a href="editSparePart?sparePartId=<%= sparePart.getPartId() %>" class="btn btn-warning btn-sm">Edit</a>
                            <a href="deleteSparePart?sparePartId=<%= sparePart.getPartId() %>" class="btn btn-danger btn-sm">Delete</a>
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
