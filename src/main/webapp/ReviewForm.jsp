<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Movie Review</title>
<style>
body {
    background-color: #f4f4f4; /* Set page background to light gray */
    font-family: Arial, sans-serif; /* Set font */
    display: flex;
    flex-direction: column; /* Arrange content in a column */
    align-items: center; /* Center content horizontally */
    justify-content: center; /* Center content vertically */
    min-height: 100vh; /* Minimum height to cover the viewport */
    margin: 0;
}

.review-container {
    width: 80%;
    max-width: 600px; /* Limit container width */
    background-color: #fff; /* Set background color to white */
    border-radius: 10px; /* Add border radius */
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Add box shadow */
    padding: 30px;
    text-align: center;
    margin-top:150px;
    animation: fadeIn 0.5s ease; /* Add fadeIn animation */
}

@keyframes fadeIn {
    from {
        opacity: 0;
        transform: translateY(-20px);
    }
    to {
        opacity: 1;
        transform: translateY(0);
    }
}

h1 {
    font-size: 28px;
    margin-bottom: 20px;
    color: #333; /* Set heading color */
}

form {
    width: 100%;
}

.input-group {
    margin-bottom: 20px;
    text-align: left; /* Align text to the left */
}

label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
    color: #555; /* Set label color */
}

.review-container input[type="text"],
.review-container input[type="number"],
.review-container input[type="email"],
.review-container textarea,
.review-container select {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 16px;
    color: #333; /* Set text color */
}

.review-container button {
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin-top: 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
    font-size: 16px;
    transition: background-color 0.3s ease;
}

.review-container button:hover {
    background-color: #45a049;
}
input,
textarea,
select,
button {
    opacity: 0; /* Initially hide form elements */
    animation: slideIn 0.5s ease forwards; /* Add slideIn animation */
}

@keyframes slideIn {
    from {
        opacity: 0;
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
<jsp:include page="header2.jsp"></jsp:include>
    <div class="review-container">
        <h1>
            <c:choose>
                <c:when test="${empty review}">
                    Add Movie Review
                </c:when>
                <c:otherwise>
                    Update Movie Review
                </c:otherwise>
            </c:choose>
        </h1>
        <form action="<c:choose><c:when test="${empty review}">review_insert</c:when><c:otherwise>review_update</c:otherwise></c:choose>" method="post">
            <c:if test="${not empty review}">
                <input type="hidden" name="id" value="${review.id}" />
            </c:if>
            <div class="input-group">
                <label for="movie">Movie Title:</label>
                <input type="text" id="movie" name="movie" value="${empty review ? '' : review.movie}" required>
            </div>
            <div class="input-group">
                <label for="rating">Rating:</label>
                <select id="rating" name="rating" required>
                    <option value="1" <c:if test="${!empty review and review.rating eq '1'}">selected</c:if>>1</option>
                    <option value="2" <c:if test="${!empty review and review.rating eq '2'}">selected</c:if>>2</option>
                    <option value="3" <c:if test="${!empty review and review.rating eq '3'}">selected</c:if>>3</option>
                    <option value="4" <c:if test="${!empty review and review.rating eq '4'}">selected</c:if>>4</option>
                    <option value="5" <c:if test="${!empty review and review.rating eq '5'}">selected</c:if>>5</option>
                </select>
            </div>
            <div class="input-group">
                <label for="review">Your Review:</label>
                <textarea id="review" name="review" rows="4" required>${empty review ? '' : review.review}</textarea>
            </div>
            <div class="input-group">
                <label for="name">Your Name:</label>
                <input type="text" id="name" name="name" value="${empty review ? '' : review.name}" required>
            </div>
            <div class="input-group">
                <label for="email">Your Email:</label>
                <input type="email" id="email" name="email" value="${empty review ? '' : review.email}" required>
            </div>
            <button type="submit">
                <c:choose>
                    <c:when test="${empty review}">Add Review</c:when>
                    <c:otherwise>Update Review</c:otherwise>
                </c:choose>
            </button>
        </form>
    </div>
    <div class="footer">
		<jsp:include page="footer.jsp"></jsp:include>
	</div>
</body>
</html>
