home_display_reply(user, account, navbar, leftbar, display_reply) ::= <<

<nav id="myNavbar" class="navbar navbar-default navbar-inverse navbar-fixed-top" role="navigation">
  $navbar$
</nav>

<br>

<div class="container">
	<div class="page-header">
	</div>
</div>


<div class="container">
	<div class="row">

		$leftbar$

		<br>

		$display_reply$
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

<div class="modal fade" id="modalCreateFolder">
    $account:newfolder_window()$
</div><!-- /.modal Create Folder -->

>>