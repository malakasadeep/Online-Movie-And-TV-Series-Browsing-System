<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Movie Packages</title>
<link rel="stylesheet" type="text/css" href="styles.css">
<style type="text/css">
body {
	background-color: #f0f0f0;
	font-family: Arial, sans-serif;
}

.wwcontainer {
	width: 80%;
	margin: 150px auto;
	background-color: #fff;
	border-radius: 10px;
	box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.1);
	padding: 30px;
	display: flex;
}

.wwcontainer .form-group {
	margin-bottom: 20px;
	width: 150%;
	margin-left: 150px;
}

.wwcontainer .form-group label {
	display: block;
	font-weight: bold;
}

.wwcontainer .form-group input[type="text"], .wwcontainer .form-group input[type="email"],
	.wwcontainer .form-group select, .wwcontainer .form-group input[type="date"],
	.wwcontainer .form-group input[type="number"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
	font-size: 16px;
	margin-top: 5px;
}

.wwcontainer .form-group select {
	width: 100%;
}

.wwcontainer .form-group button {
	background-color: #4CAF50;
	color: white;
	padding: 14px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	width: 100%;
	font-size: 16px;
	transition: background-color 0.3s ease;
}

.wwcontainer .form-group button:hover {
	background-color: #45a049;
}

@media ( min-width : 768px) {
	.container {
		width: 80%;
		display: grid;
		grid-template-columns: 1fr 1fr;
		gap: 20px;
	}
	.form-group {
		margin-bottom: 0;
	}
	.form-group button {
		width: auto;
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
	<jsp:include page="header2.jsp"></jsp:include>
	<div class="wwcontainer">
		<h2>
			<c:choose>
				<c:when test="${empty bookpkg}">
                    Book Movie Package
                </c:when>
				<c:otherwise>
                    Update Booking Details
                </c:otherwise>
			</c:choose>
			<img alt="" src="images/logo123.png" width="450px" height="auto" style="margin-top: 150px;"/>
		</h2>
		<form
			action="<c:choose><c:when test="${empty bookpkg}">book_insert</c:when><c:otherwise>book_update</c:otherwise></c:choose>"
			method="post">
			<c:if test="${not empty bookpkg}">
				<input type="hidden" name="id" value="${bookpkg.id}" />
			</c:if>
			<div class="form-group">
				<label for="userName">User Name:</label> <input type="text"
					id="userName" name="userName"
					value="${empty bookpkg ? '' : bookpkg.userName}" required>
			</div>
			<div class="form-group">
				<label for="userEmail">User Email:</label> <input type="email"
					id="userEmail" name="userEmail"
					value="${empty bookpkg ? '' : bookpkg.userEmail}" required>
			</div>
			<div class="form-group">
				<label for="bookpkgName">Package Name:</label> <input type="text"
					id="bookpkgName" name="bookpkgName"
					value="${empty bookpkg ? '' : bookpkg.bookpkgName}" required>
			</div>
			<div class="form-group">
				<label for="bookpkgType">Package Type:</label> <select
					id="bookpkgType" name="bookpkgType" required>
					<option value="Standard"
						<c:if test="${!empty bookpkg and bookpkg.bookpkgType eq 'Standard'}">selected</c:if>>Standard</option>
					<option value="Premium"
						<c:if test="${!empty bookpkg and bookpkg.bookpkgType eq 'Premium'}">selected</c:if>>Premium</option>
				</select>
			</div>
			<div class="form-group">
				<label for="startDate">Start Date:</label> <input type="date"
					id="startDate" name="startDate"
					min="<%= (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()) %>"
					value="${empty bookpkg ? '' : bookpkg.startDate}" required>
			</div>
			<div class="form-group">
				<label for="duration">Duration (in days):</label> <input
					type="number" id="duration" name="duration"
					value="${empty bookpkg ? '' : bookpkg.duration}" required>
			</div>
			<div class="form-group">
				<label for="paymentMethod">Payment Method:</label> <select
					id="paymentMethod" name="paymentMethod" required>
					<option value="Credit Card"
						<c:if test="${!empty bookpkg and bookpkg.paymentMethod eq 'Credit Card'}">selected</c:if>>Credit
						Card</option>
					<option value="Debit Card"
						<c:if test="${!empty bookpkg and bookpkg.paymentMethod eq 'Debit Card'}">selected</c:if>>Debit
						Card</option>
					<option value="PayPal"
						<c:if test="${!empty bookpkg and bookpkg.paymentMethod eq 'PayPal'}">selected</c:if>>PayPal</option>
				</select>
			</div>
			<div class="form-group">
				<button type="submit">
					<c:choose>
						<c:when test="${empty bookpkg}">Book Package</c:when>
						<c:otherwise>Update Booking Details</c:otherwise>
					</c:choose>
				</button>
			</div>
		</form>
	</div>

	<div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
