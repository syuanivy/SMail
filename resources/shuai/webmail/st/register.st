register() ::= <<
<br>

<div class="container">
	<div class="row">
		<div class="col-xs-4">
		</div>
		<div class="col-xs-4">
			<a href="#" class="thumbnail">
			<img src="/files/images/logo.jpg" alt="125x125">
			</a>
			<h3>Register a new user</h3>
		</div>
		<div class="col-xs-4">
		</div>
	</div>
</div>

<div class="container">
  <div class="row">
	<div class="col-xs-4">
	</div>

	<div class="col-xs-4">
		<form method=post action=/registerprocessor>
			<span class="help-block">
				The items with * are required.
			</span>
			<div class="form-group">
				<label for="username">Username*</label>
				<input type="username" name="username" class="form-control" id="username" placeholder="Username" required autofocus>
			</div>
			<div class="form-group">
				<label for="inputEmail">Email*</label>
				<input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" required>
			</div>
			<div class="form-group">
				<label for="inputPassword">Password*</label>
				<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" required>
			</div>
			<div class="form-group">
				<label for="retypePassword">Retype Password*</label>
				<input type="password" name="retype" class="form-control" id="retypePassword" placeholder="Retype Password" required>
			</div>

			<button type="submit" class="btn btn-primary">Register</button>
		</form>
	</div>

    <div class="col-xs-4">
	</div>
  </div>
</div> <!-- /container -->
>>