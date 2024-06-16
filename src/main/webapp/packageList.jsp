<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Package List - Order Confirmation System</title>
<style>
/* Page styles */
body {
	background-color: black; /* Set page background to black */
	display: flex;
	flex-direction: column; /* Arrange content in a column */
	min-height: 100vh; /* Minimum height to cover the viewport */
	margin: 0; /* Remove default margin */
}

/* Container styles */
.ttcontainer {
	background-color: rgba(255, 255, 255, 0.6);
	/* Transparent white background */
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
	padding: 20px;
	width: 75%;
	margin: 200px auto; /* Center container horizontally */
	flex: 1; /* Expand container to fill remaining space */
}

/* Table styles */
table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 30px;
}

th, td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f5f5f5;
}

tr:hover {
	background-color: #e0e0e0;
}

/* Button container styles */
.button-container {
	text-align: center;
	
	justify-content: center;
}

/* Button styles */
.ttbtn {
	padding: 10px;
	border: none;
	cursor: pointer;
	border-radius: 5px;
	transition: background-color 0.3s ease;
	font-weight: bold;
	text-transform: uppercase;
	margin: 10px; /* Add margin between buttons */
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

/* Animation */
@
keyframes fadeIn {from { opacity:0;
	transform: translateY(-20px);
}

to {
	opacity: 1;
	transform: translateY(0);
}

}
.footer {
	text-align: center;
	padding: 10px 0;
	width: 100%;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="ttcontainer">
		<h2>Package List</h2>
		<table>
			<thead>
				<tr>
					<th>Package Name</th>
					<th>Description</th>
					<th>Price</th>
					<th>Duration (Days)</th>
					<th>Video Quality</th>
					<th>Package Type</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listPackages}" var="item">
					<tr class="fadeIn">
						<td>${item.pkgName}</td>
						<td>${item.description}</td>
						<td>${item.price}</td>
						<td>${item.duration}</td>
						<td>${item.videoQuality}</td>
						<td>${item.pkgType}</td>
						<td class="button-container"><a
							href="pkg_edit?id=${item.id}">
								<button class="ttbtn btn-update" type="submit">Update</button>
						</a> <a href="pkg_delete?id=${item.id}">
								<button class="ttbtn btn-delete" type="submit">Delete</button>
						</a></td>
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
