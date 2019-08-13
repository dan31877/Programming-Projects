<div>
	<!-- Header -->
	<header class="header">
		<h1>DEAL FINDER</h1>
	</header>
	<!-- BOOTSTRAP NAVBAR  -->
	<nav class="navbar navbar-expand-sm navbar-dark bg-dark mb-3">
		<!--div class="container"-->

		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target=".dual-collapse2">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div
			class="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/index">Search</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/about">About</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/showAllProducts">Products</a>
				</li>
			</ul>
		</div>
		<div
			class="navbar-collapse collapse w-100 order-3 order-md-0 dual-collapse2">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link text-primary"
					href="${pageContext.request.contextPath}/userLogin">Login</a></li>
				<li class="nav-item"><a class="nav-link text-primary"
					href="${pageContext.request.contextPath}/registerUser">Register</a>
				</li>
			</ul>
		</div>

		<!--/div-->
	</nav>

</div>