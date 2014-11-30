home_navbar(user,account,second_account) ::= <<

	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbarCollapse">
				<span class="sr-only">Mail Navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">$user.name$</a>
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
                           <li><a href="/home?accountNum=1" accountNum="1" >$account.emailAddress$</a></li>
                           <li><a href="/home?accountNum=2" accountNum="2">$second_account.emailAddress$</a></li>
                      </ul>
                </li>
                <li role="presentation" class="dropdown">
                      <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-expanded="false">
                            <span class="glyphicon glyphicon-cog"></span> <span class="caret"></span>
                      </a>
                      <ul class="dropdown-menu" role="menu">
                            <li><a href="/userinfo">Settings</a></li>
                            <li><a href="/userinfo">Change Password</a></li>
                            <li><a href="/logout">Log out</a></li>
                      </ul>
                </li>
			</ul>
		</div>
	</div>


>>