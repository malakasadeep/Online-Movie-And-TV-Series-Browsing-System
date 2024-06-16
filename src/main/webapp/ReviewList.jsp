<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Review List - Order Confirmation System</title>
<style>
/* Page styles */
/* Page styles */
body {
	background-color: #f4f4f4;
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
}

/* Container styles */
.ttcontainer {
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	padding: 20px;
	width: 90%;
	margin: 150px auto;
}

/* Table styles */
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	padding: 12px 15px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #e9e9e9;
}

/* Button container styles */
.button-container {
	text-align: center;
}

/* Button styles */
.ttbtn {
	padding: 10px 20px;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s ease;
	font-weight: bold;
	text-transform: uppercase;
	margin: 5px;
}

.btn-update {
	background-color: #4CAF50;
	color: white;
}

.btn-update:hover {
	background-color: #45a049;
}

.btn-delete {
	background-color: #f44336;
	color: white;
}

.btn-delete:hover {
	background-color: #d32f2f;
}

/* Footer styles */



.footer {
	text-align: center;
	padding: 10px 0;
	width: 100%;
}
</style>
</head>
<body>
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="ttcontainer">
		<h2>Movie Review List</h2>
		<table>
			<thead>
				<tr>
					<th>Movie Title</th>
					<th>Rating</th>
					<th>Review</th>
					<th>Reviewer Name</th>
					<th>Reviewer Email</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listReviews}" var="review">
					<tr class="fadeIn">
						<td>${review.movie}</td>
						<td>${review.rating}/5</td>
						<td>${review.review}</td>
						<td>${review.name}</td>
						<td>${review.email}</td>
						<td class="button-container">
							<a href="review_edit?id=${review.id}">
								<button class="ttbtn btn-update" type="submit">Update</button>
							</a>
							<a href="review_delete?id=${review.id}">
								<button class="ttbtn btn-delete" type="submit">Delete</button>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
