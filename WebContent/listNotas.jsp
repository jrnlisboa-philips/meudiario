<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<jsp:include page="includes/header.jsp"></jsp:include>

<main>
	
	<div class="jumbotron row">
		<h1>Meu Diário</h1>
		<br>
		<h3>Listagem de Notas </h3>
	</div>
	
	<div class="row mb-3">
		<a href="notas?action=cad" class="btn btn-success"> <i class="fas fa-plus"></i> Adicionar</a>
	</div>
	
	<div class="row">
		<c:if test="${listSize > 0 }">
			<ul class="list-group list-align-center col">
				<c:forEach var="nota" items="${listNotas }"  >
					<li class="list-group-items"> 
						<span class="badge badge-dark">
							<c:out value="${nota.id }" />
						</span>
						<span class="text-light bg-info">
							<c:out value="${nota.assunto }" />
						</span> 
						<span class="badge badge-info">
							<c:out value="${nota.dtNota }" />
						</span>
						<div class="float-right">
							<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#editNotaModal">
							  <i class="fas fa-edit"></i>
							</button>
						</div>
					</li>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${listSize == 0 }">
			<div class="alert alert-danger col">
				<h2>Não há itens cadastrados ainda!</h2>
			</div>
		</c:if>
	</div>
		
		
</main>

<jsp:include page="editNotas.jsp"></jsp:include>
<jsp:include page="includes/footer.jsp"></jsp:include>
