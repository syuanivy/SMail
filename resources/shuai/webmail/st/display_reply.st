display_reply(email, account) ::= <<

        <div class="col-xs-1">
			<img src="/files/images/snail.png" alt="125x125", style="width: 100%; max-height: 100%">
		</div>

		<div class="col-xs-8">
		    <h4>$email.subject$</h4>
			<h4><small> From: Shuai Yuan </small><small> &lt;$email.sender$&gt;</small></h4>
			<h4> <small> To: Snail User </small> <small> &lt;$email.recipient$&gt;</small></h4>
			<h4><small>Date: $email.time$ </small></h4>

	        <div class="form-group">
	             <div class="col-xs-6">&#160;&#160;&#160;&#160;</div>
                 <label class="col-xs-1"> <a id="link-reply">Reply</a> </label>
                 <label class="col-xs-1"> <a id="link-forward">Forward</a> </label>
            </div>


		    <br>

			$email.body$

		    <hr>

			<div class="panel panel-default" id="panel-reply" style="display:none">
				<div class="panel-body">
					<form role="form" class="form-horizontal" method="post" action="/composeprocessor">
						<div class="form-group">
                        	<label class="col-xs-2" for="inputTo">Subject</label>
                        		<div class ="col-xs-8">
                        		<input type="text" value ="Re:$email.subject$" name="subject" class="form-control" >
                        		</div>
                        </div>
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
							<input type="email" value ="$email.sender$" name="recipient" class="form-control" id="reply_to" >
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
						  <div class="col-xs-12"><textarea spellcheck="true" class="form-control" name="body" id="inputBody" rows="6">&#10;&#10;&#10;--On $email.time$, $email.sender$ wrote: &#13;&#10;$email.body$ </textarea></div>
						</div>

						<div class="col-xs-2">
		                    <button type="button" id="cancel-reply" class="btn btn-default pull-left" >Cancel</button>
		                </div>

						<div class="col-xs-2">
							<button type="submit" formaction="/save" class="btn btn-default">Save</button>
						</div>
						<div class="col-xs-2">
							<button type="submit" class="btn btn-primary ">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
						</div>

					</form>
				</div><!-- panel-body-->
			</div>

			<div class="panel panel-default" id="panel-forward" style="display:none">
            				<div class="panel-body">
            					<form role="form" class="form-horizontal" method="post" action="/composeprocessor">
            						<div class="form-group">
                                    	<label class="col-xs-2" for="inputTo">Subject</label>
                                    		<div class ="col-xs-8">
                                    		<input type="text" value ="Fwd:$email.subject$" name="subject" class="form-control" >
                                    		</div>
                                    </div>
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
            							<label class="col-xs-2" for="inputTo">To</label>
            							<div class ="col-xs-8">
            							<input type="email" name="recipient" class="form-control" id="reply_to" >
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
            						     <div class="col-xs-12"><textarea spellcheck="true" class="form-control" name="body" id="inputBody" rows="6">&#10;&#10;---------- Forwarded message ----------&#13;&#10;From&#58; $email.sender$&#13;&#10;Date&#58; $email.time$&#13;&#10;Subject&#58; $email.subject$&#13;&#10;To&#58; $account$&#13;&#10;&#10;$email.body$ </textarea></div>
            						</div>

       						        <div class="col-xs-2">
		                                 <button type="button" id="cancel-forward" class="btn btn-default pull-left" >Cancel</button>
		                            </div>

            						<div class="col-xs-2">
            							<button type="submit" formaction="/save" class="btn btn-default">Save</button>
            						</div>
            						<div class="col-xs-2">
            							<button type="submit" class="btn btn-primary ">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
            						</div>

            					</form>
            				</div><!-- panel-body-->
            </div>
>>