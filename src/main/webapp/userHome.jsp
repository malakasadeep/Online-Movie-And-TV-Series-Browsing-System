<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movie Browsing System</title>
<style>
body {
	background-color: #000;
	color: #fff;
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	min-height: 100vh;
}

.card-container {
	display: flex;
	justify-content: space-around;
	flex-wrap: wrap;
	max-width: 800px;
	margin-top: 200px;
	margin-left: 420px;
	margin-bottom: auto;
	/* Pushes the container to the top of the remaining space */
}

.card {
	width: 200px;
	height: 200px;
	background-color: #000;
	border: 2px solid #fff;
	border-radius: 10px;
	margin: 20px;
	padding: 20px;
	box-sizing: border-box;
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	text-align: center;
	position: relative;
	overflow: hidden;
	cursor: pointer;
	transition: all 0.3s ease;
}

.card:hover {
	transform: scale(1.05);
	box-shadow: 0 0 20px rgba(255, 255, 255, 0.5);
}

.card::before {
	content: '';
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(255, 255, 255, 0.1);
	transition: all 0.3s ease;
	z-index: -1;
}

.card:hover::before {
	transform: scale(2);
}

.card h2 {
	margin: 0;
	font-size: 1.2em;
}

.card p {
	margin: 10px 0 0;
	font-size: 0.9em;
}

.add-movies, .view-movies, .add-packages, .view-packages, .add-users,
	.view-users {
	background-color: #000;
	color: #fff;
}

.footer {

	margin-top: 20px;
	text-align: center;
	bottom: 0;
	width: 100%;
}
</style>
</head>
<body>
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="card-container">

		<a href="<%=request.getContextPath()%>/movie_view">
			<div class="card add-movies">
				<h2>Brows Movies</h2>
				<p>Brows movies to the system</p>
			</div>
		</a> <a href="<%=request.getContextPath()%>/pkg_view">
			<div class="card view-movies">
				<h2>Brows Packages</h2>
				<p>View Available Packages in the system</p>
			</div>
		</a><a href="<%=request.getContextPath()%>/review_new">
			<div class="card add-packages">
				<h2>Add Ratings</h2>
				<p>Add new Review for your favorite movie</p>
			</div>
		</a><a href="<%=request.getContextPath()%>/review_list">
			<div class="card view-packages">
				<h2>View Ratings</h2>
				<p>View your ratings for movie </p>
			</div>
		</a><a href="<%=request.getContextPath()%>/book_new">
		<div class="card add-users">
			<h2>Book Packages</h2>
			<p>Add new users to the system</p>
		</div></a><a href="<%=request.getContextPath()%>/book_list">
		<div class="card view-users">
			<h2>View Bookings</h2>
			<p>View existing users in the system</p>
		</div></a>
	</div>
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
