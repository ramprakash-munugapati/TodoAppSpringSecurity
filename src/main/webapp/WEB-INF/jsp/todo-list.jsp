<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container" style="margin-top: 30px">
	<table class="table table-striped">
		<caption>Your todos are</caption>
		<thead>
			<tr>
				<th>Description</th>
				<th>Target Date</th>
				<th>Is it Done?</th>
				<th>Action</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${todos}" var="todo">
				<tr>
					<td>${todo.desc}</td>
					<td><fmt:formatDate value="${todo.targetDate}"
							pattern="dd/MM/yyyy" /></td>
					<td>${todo.done}</td>
					<td><a class="btn btn-success"
						href="/todo-update?id=${todo.id}">Update</a></td>
					<td><a class="btn btn-warning"
						href="/todo-delete?id=${todo.id}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div>
		<a class="button" href="/todo-add">Add a Todo</a>
	</div>
</div>
<%@ include file="common/footer.jspf" %>

