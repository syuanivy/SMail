home_navbar(user) ::= <<

	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse">
				<span class="sr-only">Mail Navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">$user$</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="navbarCollapse">
			<ul class="nav navbar-nav">
                <li >
                      <a href="/home">
                      <span class="glyphicon glyphicon-home"></span>
                      </a>
                </li>
                <li role="presentation" class="dropdown">
                      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                         <span class="glyphicon glyphicon-user"></span> <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" role="menu">
                           <li><a href="#">ivyandscorpio@gmail.com</a></li>
                           <li><a href="#">syuan@nyu.edu</a></li>
                      </ul>
                </li>
                <li role="presentation" class="dropdown">
                      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                            <span class="glyphicon glyphicon-cog"></span> <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" role="menu">
                            <li><a href="/userinfo">Settings</a></li>
                            <li><a href="/logout">Log out</a></li>
                      </ul>
                </li>
			</ul>
		</div>
	</div>


>>