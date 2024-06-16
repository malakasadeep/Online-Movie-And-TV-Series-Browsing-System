<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie and TV Series List</title>
<link rel="stylesheet" href="css/tablestyle.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.js"></script>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

h1 {
	text-align: center;
	color: #333;
	margin-bottom: 30px;
	text-transform: uppercase;
	letter-spacing: 2px;
}

.container {
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	gap: 20px;
	padding: 20px;
}

.card {
	width: 300px;
	height: 400px; /* Adjust the height as needed */
	background-color: #fff;
	border-radius: 8px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	padding: 20px;
	text-align: center;
	position: relative;
	overflow: hidden;
	transition: all 0.3s ease;
}

.card:hover {
	transform: scale(1.05);
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.3);
}

.card img {
	height: 50%;
	width: auto;
	border-radius: 8px;
	object-fit: cover;
	object-position: center;
	transition: transform 0.3s ease;
	display: block; /* Center the image */
	margin: 0 auto; /* Center the image */
}

.card:hover img {
	transform: scale(1.1);
}

.card h3 {
	color: #333;
	margin-bottom: 10px;
}

.card p {
	margin: 5px 0;
	overflow: hidden; /* Truncate text */
	text-overflow: ellipsis; /* Truncate text */
	white-space: nowrap; /* Truncate text */
}

.actions {
	display: flex;
	justify-content: space-between;
	margin-top: auto;
}

.actions a i {
	margin: 0 5px;
	cursor: pointer;
}

.fa-edit {
	color: #113f8d;
}

.fa-trash {
	color: #5c0a0a;
}

.ccc {
	margin-top: 150px;
}

.ccc h1 {
	color: white;
}

.footer {
	background-color: #000;
	color: #fff;
	margin-top: 200px;
	text-align: center;
	bottom: 0;
	width: 100%;
}
</style>
</head>

<body>
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="ccc">
		<h1>Movie and TV Series List</h1>
		<div class="container">
			<c:forEach items="${listMovies}" var="movie">
				<div class="card">
					<img src="${pageContext.request.contextPath}/${movie.imageUrl}"
						alt="${movie.title}">
					<h3>ID: ${movie.id}</h3>
					<p>Title: ${movie.title}</p>
					<p>Genre: ${movie.genre}</p>
					<p>Release Date: ${movie.releaseDate}</p>
					<p>Description: ${movie.description}</p>
				</div>
			</c:forEach>
		</div>
	</div>
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
