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
				<label for="inputPassword">Password*</label>
				<input type="password" name="password" class="form-control" id="inputPassword" placeholder="Password" required>
			</div>
			<div class="form-group">
				<label for="retypePassword">Retype Password*</label>
				<input type="password" name="retype" class="form-control" id="retypePassword" placeholder="Retype Password" required>
			</div>
			<div class="form-group">
				<label for="inputEmail">Email*</label>
				<input type="email" name="email" class="form-control" id="email" placeholder="Email" required>
			</div>

			<div class="form-group">
                <label for="smtp">SMTP Server*</label>
                <input type="text" name="smtp" class="form-control" id="smtp" placeholder="smtp.xxx.com" required>
            </div>
            <div class="form-group">
                <label for="smtpPort">SMTP Port*</label>
                <input type="text" name="smtpPort" class="form-control" id="smtpPort" placeholder="25 or 465" required>
            </div>
			<div class="form-group">
                <label for="popServer">POP Server*</label>
                <input type="text" name="pop" class="form-control" id="pop" placeholder="pop.xxx.com" required>
            </div>
			<div class="form-group">
            	<label for="popPort">POP PORT*</label>
            	<input type="text" name="popPort" class="form-control" id="popPort" placeholder="110 or 995" required>
            </div>

			<div class="form-group">
            	<label for="ssl">SSL*</label>
            	<input type="text" name="ssl" class="form-control" id="ssl" placeholder="0 or 1" required>
            </div>
			<div class="form-group">
				<label for="emailUsername">Username for the Servers*</label>
				<input type="username" name="emailUsername" class="form-control" id="emailUsername" placeholder="Username" required autofocus>
			</div>
			<div class="form-group">
				<label for="emailPassword">Password for the Servers*</label>
				<input type="password" name="emailPassword" class="form-control" id="emailPassword" placeholder="Password" required>
			</div>


			<button type="submit" class="btn btn-primary">Register</button>
		</form>
	</div>

    <div class="col-xs-4">
	</div>
  </div>
</div> <!-- /container -->
>>