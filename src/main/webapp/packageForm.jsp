<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Package</title>
<style>
body {
	 background-color: black; /* Set page background to black */
    display: flex;
    flex-direction: column; /* Arrange content in a column */
    min-height: 100vh; /* Minimum height to cover the viewport */
    margin: 0; 
}

.f1container {
	width: 50%;
	margin: 150px auto; text-align : center;
	background-color: rgba(255, 255, 255, 0.6);
	/* Transparent white background */
	border-radius: 10px;
	overflow: hidden;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.8);
	padding: 20px;
	text-align: center;
	flex-grow: 1;
}

h1 {
	font-size: 28px;
	margin-bottom: 20px;
}

form {
	width: 100%;
}

.input-group {
	margin-bottom: 20px;
}

label {
	display: block;
	font-weight: bold;
}

.f1container input[type="text"], .f1container input[type="number"],
	.f1container  textarea, .f1container  select {
	width: 100%; /* Adjust width for padding and border */ padding : 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 16px;
	color: black; /* Change text color for better visibility */
	background-color: white;
	padding: 10px; /* Set background color for input fields */
}

.f1container button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	margin-top: 10px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
	font-size: 16px;
	transition: background-color 0.3s ease; /* Add transition effect */
}

.f1container button:hover {
	background-color: #45a049;
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
form.fadeIn {
	animation: fadeIn 0.5s ease; /* Apply animation to form */
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
	<div class="f1container">
		<h1>
			<c:if test="${pkg != null}">
				<span>Edit</span> Package
        </c:if>
			<c:if test="${pkg == null}">
				<span>Add</span> Package
        </c:if>
		</h1>
		<form id="pkgForm"
			action="<c:if test='${pkg != null}'>pkg_update</c:if><c:if test='${pkg == null}'>pkg_insert</c:if>"
			method="post" class="fadeIn">
			<c:if test="${pkg != null}">
				<input type="hidden" name="id" value="<c:out value='${pkg.id}' />" />
			</c:if>
			<div class="input-group">
				<label for="pkgName">Package Name:</label> <input type="text"
					id="pkgName" name="pkgName"
					value="<c:out value='${pkg != null ? pkg.pkgName : ""}' />"
					required>
			</div>
			<div class="input-group">
				<label for="description">Description:</label>
				<textarea id="description" name="description" rows="4" required><c:out
						value='${pkg != null ? pkg.description : ""}' /></textarea>
			</div>
			<div class="input-group">
				<label for="price">Price:</label> <input type="number" id="price"
					name="price"
					value="<c:out value='${pkg != null ? pkg.price : ""}' />" required>
			</div>
			<div class="input-group">
				<label for="duration">Duration (Days):</label> <input type="number"
					id="duration" name="duration"
					value="<c:out value='${pkg != null ? pkg.duration : ""}' />"
					required>
			</div>
			<div class="input-group">
				<label for="videoQuality">Video Quality:</label> <select
					id="videoQuality" name="videoQuality" required>
					<option value="HD"
						<c:if test="${pkg != null && pkg.videoQuality eq 'HD'}">selected</c:if>>HD</option>
					<option value="Full HD"
						<c:if test="${pkg != null && pkg.videoQuality eq 'Full HD'}">selected</c:if>>Full
						HD</option>
					<option value="4K"
						<c:if test="${pkg != null && pkg.videoQuality eq '4K'}">selected</c:if>>4K</option>
				</select>
			</div>
			<div class="input-group">
				<label for="pkgType">Package Type:</label> <select id="pkgType"
					name="pkgType" required>
					<option value="1 Person"
						<c:if test="${pkg != null && pkg.pkgType eq '1 Person'}">selected</c:if>>1
						Person</option>
					<option value="2 Person"
						<c:if test="${pkg != null && pkg.pkgType eq '2 Person'}">selected</c:if>>2
						Person</option>
					<option value="4 Person"
						<c:if test="${pkg != null && pkg.pkgType eq '4 Person'}">selected</c:if>>4
						Person</option>
				</select>
			</div>
			<button type="submit">
				<c:if test="${pkg != null}">Update Package</c:if>
				<c:if test="${pkg == null}">Add Package</c:if>
			</button>
		</form>
	</div>
	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
