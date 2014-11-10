home(user, account) ::= <<

<nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
  $account:home_navbar()$
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

<div class="modal fade" id="modalCompose">
    $account:compose_window()$
</div><!-- /.modal compose message -->


>>