<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<h2>Login Here</h2>
	<p></p>
	<strong> ${errorMessage}</strong>
	<form method="post">
		<div class="form-group">
			<label for="email">Name:</label> <input type="text"
				class="form-control" id="name" placeholder="Enter Name" name="name">
		</div>
		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password"
				class="form-control" id="pwd" placeholder="Enter Password"
				name="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input
				class="form-check-input" type="checkbox" name="remember">
				Remember me
			</label>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
	</form>
</div>
<%@ include file="common/footer.jspf" %>
