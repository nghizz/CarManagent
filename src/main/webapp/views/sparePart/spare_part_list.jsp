<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                <c:forEach items="${spareParts}" var="sparePart">
                    <tr>
                        <td>${sparePart.sparePartId}</td>
                        <td>${sparePart.name}</td>
                        <td>${sparePart.description}</td>
                        <td>${sparePart.price}</td>
                        <td>
                            <a href="editSparePart?sparePartId=${sparePart.sparePartId}" class="btn btn-warning btn-sm">Edit</a>
                            <a href="deleteSparePart?sparePartId=${sparePart.sparePartId}" class="btn btn-danger btn-sm">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>