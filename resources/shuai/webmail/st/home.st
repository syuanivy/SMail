home(user) ::= <<

<nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
  $user:home_navbar()$
</nav>

<br>

<div class="container">
	<div class="page-header">
	</div>
</div>


<div class="container">
	<div class="row">

		$user:home_leftsidebar()$

		<br>

		$user:home_center()$
	</div>
</div>


<div class="container">
	<div class="row">
		<div class="col-sm-12">
			<footer>
				<p>&copy; Copyright 2014 Shuai Yuan</p>
			</footer>
		</div>
	</div>
</div>



>>