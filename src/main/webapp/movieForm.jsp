<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Movie or TV Series - Movie & TV Series Management</title>
<link rel="stylesheet" href="css/styles.css">
<!-- You can link your custom CSS here -->
<!-- Include SweetAlert library from CDN -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">

<style>
/* Form container */
.fcontainer {
	width: 50%;
	margin-left: 400px;
	margin-top: 150px;
	padding: 30px;
	background-color: #000;
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(255, 255, 0, 0.5);
	animation: fadeIn 0.5s ease;
	padding: 30px;
}

/* Form title */
h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #ff0;
	text-transform: uppercase;
	letter-spacing: 2px;
}

/* Form labels */
label {
	display: block;
	margin-bottom: 10px;
	font-weight: bold;
	color: #ff0;
}

/* Form input fields */
input[type="text"], input[type="file"], input[type="date"],
	/* Added file input type */ input[type="number"], select, textarea {
	width: 100%;
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ff0;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 16px;
	background-color: #000;
	color: #ff0;
}

/* Form button */
.fcontainer button {
	width: 100%;
	padding: 15px;
	background-color: green;
	border: 1px solid #ff0;
	color: #fff;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
	transition: background-color 0.3s ease;
}

.fcontainer button:hover {
	background-color: #f80;
}

/* SweetAlert error message style */
.swal2-icon {
	font-size: 2em;
	margin-top: 10px;
	color: #f44336;
}

/* Animations */
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
	<div class="hesder">
		<jsp:include page="header.jsp"></jsp:include>
	</div>


	<div class="fcontainer">

		<h1>
			<c:if test="${movieOrSeries != null}">
				<span>Edit</span> Movie or TV Series
        </c:if>
			<c:if test="${movieOrSeries == null}">
				<span>Add</span> Movie or TV Series
        </c:if>
		</h1>

		<form id="movieOrSeriesForm"
			action="<c:if test='${movieOrSeries != null}'>movie_update</c:if><c:if test='${movieOrSeries == null}'>movie_insert</c:if>"
			method="post" enctype="multipart/form-data">
			<!-- Added enctype="multipart/form-data" for file upload -->

			<c:if test="${movieOrSeries != null}">
				<input type="hidden" name="id"
					value="<c:out value='${movieOrSeries.id}' />" />
			</c:if>

			<div class="input-group">
				<label for="title">Title:</label> <input type="text" id="title"
					name="title" value="<c:out value='${movieOrSeries.title}' />"
					required>
			</div>

			<div class="input-group">
				<label for="genre">Genre:</label> <select id="genre" name="genre"
					required>
					<option value="Action"
						<c:if test="${movieOrSeries.genre eq 'Action'}">selected</c:if>>Action</option>
					<option value="Comedy"
						<c:if test="${movieOrSeries.genre eq 'Comedy'}">selected</c:if>>Comedy</option>
					<option value="Drama"
						<c:if test="${movieOrSeries.genre eq 'Drama'}">selected</c:if>>Drama</option>
					<option value="Sci-Fi"
						<c:if test="${movieOrSeries.genre eq 'Sci-Fi'}">selected</c:if>>Sci-Fi</option>
					<!-- Add more genres here -->
				</select>
			</div>

			<div class="input-group">
				<label for="releaseDate">Release Date:</label> <input type="date"
					id="releaseDate" name="releaseDate"
					max="<%= (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()) %>"
					value="<c:out value='${movieOrSeries.releaseDate}' />" required>
			</div>

			<div class="input-group">
				<label for="director">Director:</label> <input type="text"
					id="director" name="director"
					value="<c:out value='${movieOrSeries.director}' />" required>
			</div>

			<div class="input-group">
				<label for="description">Description:</label>
				<textarea id="description" name="description" rows="4" required><c:out
						value='${movieOrSeries.description}' /></textarea>
			</div>

			<div class="input-group">
				<label for="image">Upload Image:</label> <input type="file"
					id="image" name="image" accept="image/*" required>
			</div>

			<button type="submit">
				<c:if test="${movieOrSeries != null}">Update</c:if>
				<c:if test="${movieOrSeries == null}">Add</c:if>
			</button>
		</form>

	</div>
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
