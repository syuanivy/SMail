display_reply(email, account) ::= <<
        <div class="col-xs-1">
			<img src="/files/images/snail.png" alt="125x125", style="width: 100%; max-height: 100%">
		</div>

		<div class="col-xs-8">
			<h4> Su Xue <small> &lt;$email.sender$&gt;</small></h4>

			<h4><small> $email.time$ </small></h4>

			<br>

			$email.body$

			<hr>

			<div class="panel panel-default">
				<div class="panel-body">
					<form role="form" class="form-horizontal">
						<div class="form-group">
							<label class="col-xs-2" for="inputFrom">From</label>
							<div class="col-xs-8">
								<select class="form-control">
									<option>$account$</option>
									<option>syuan@nyu.edu</option>
									<option>ivyandscorpio@hotmail.com</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-xs-2" for="inputTo">Reply To</label>
							<div class ="col-xs-8">
							<input type="email" value ="$email.sender$" name="reply_to" class="form-control" id="reply_to" >
						</div>
							<label class="col-xs-1"> <a id="link-cc">cc</a> </label>
							<label class="col-xs-1"> <a id="link-bcc">bcc</a> </label>
						</div>

						<div class="form-group" id="formgroup-cc" style="display:none">
							<label class="col-sm-1" for="inputCC"> cc </label>
							<div class="col-sm-10"><input type="email" class="form-control" id="inputCC" placeholder="comma separated list of recipients"></div>
						</div>

						<div class="form-group" id="formgroup-bcc" style="display:none">
							<label class="col-sm-1" for="inputBCC"> bcc </label>
							<div class="col-sm-10"><input type="email" class="form-control" id="inputBCC" placeholder="comma separated list of recipients"></div>
						</div>

						<div class="form-group">
						  <div class="col-xs-12"><textarea class="form-control" id="inputBody" rows="6"></textarea></div>
						</div>

						<div class="col-xs-2">
							<button type="button" class="btn btn-default">Save</button>
						</div>
						<div class="col-xs-2">
							<button type="button" class="btn btn-primary ">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
						</div>

					</form>
				<div><!-- panel-body-->
			</div>

		</div>
>>