register() ::= <<
<br>

<div class="container">
  <div class="row">
      <div class="col-xs-2 col-xs-offset-5">
          <a href="#" class="thumbnail">
             <img src="/files/images/logo.jpg" alt="125x125">
          </a>
      </div>
      <div class="col-xs-12 text-center">
          <h3>Register a New User</h3>
          <span class="help-block">The items with * are required.</span>
      </div>
  </div>
</div>


<div class="container">
 <div class="row">

  <div class="col-xs-8 col-xs-offset-2">
        <form method="post" action="/registerprocessor">

            <div class="row">
                  <div class="col-xs-6">
                    <div class="form-group">
                      <label for="username">Username*&nbsp;&nbsp;<a href="#" class="text-right">check</a></label>
                      <input type="username" name="username" class="form-control active" id="username" placeholder="Username" required autofocus>
                    </div>
                  </div>

            </div>

            <div class="row">
                  <div class="col-xs-6">
                        <div class="form-group">
                          <label for="userPassword">Password*</label>
                          <input type="password" name="password" class="form-control" id="userPassword" placeholder="Password" required>
                        </div>
                  </div>

                  <div class="col-xs-6">
                        <div class="form-group">
                          <label for="userRetypePassword">Retype Password*</label>
                          <input type="password" name="retype" class="form-control" id="userRetypePassword" placeholder="Retype Password" required>
                        </div>
                  </div>
            </div>


            <div class="row" >
                <div class="col-xs-11 col-xs-offset-1 text-left">
                   <h3>Default Account &nbsp;<label><a href="#" class="text-right"><small>check</small></a></label> </h3>
                </div>
            </div>

            <div class="row">
                  <div class="col-xs-6">
                    <div class="form-group">
                      <label for="inputEmail">Email*</label>
                      <input type="email" name="email" class="form-control" id="inputEmail" placeholder="Email" required>
                    </div>
                  </div>

                    <div class="col-xs-2">
                      <div class="form-group">
                        <label for="SMTPssl">SSL</label>
                        <input type="checkbox" name="ssl" id="ssl">
                      </div>
                    </div>
            </div>

            <div class="row">
                  <div class="col-xs-6">
                    <div class="form-group">
                      <label for="inputSMTP">Username*</label>
                      <input type="text" name="emailUser" class="form-control" id="inputSMTP" placeholder="Username" required>
                    </div>
                  </div>

                  <div class="col-xs-6">
                        <div class="form-group">
                          <label for="inputPassword">Password*</label>
                          <input type="password" name="emailPassword" class="form-control" id="inputPassword" placeholder="Password" required>
                        </div>
                  </div>
            </div>


            <div class="row">
                      <div class="col-xs-6">
                            <div class="form-group">
                              <label for="inputSMTP">SMTP*</label>
                              <input type="text" name="smtp" class="form-control" id="inputSMTP" placeholder="smtp.xxx.com" required>
                            </div>
                      </div>

                      <div class="col-xs-6">
                        <div class="form-group">
                          <label for="inputSMTPport">SMTP Port*</label>
                          <input type="text" name="smtpPort" class="form-control" id="inputSMTPport" placeholder="25 or 465" required>
                        </div>
                      </div>

            </div>

            <div class="row">
                  <div class="col-xs-6">
                    <div class="form-group">
                      <label for="inputPOP">POP*</label>
                      <input type="text" name="pop" class="form-control" id="inputPOP" placeholder="pop.xxx.com" required>
                    </div>
                  </div>

                  <div class="col-xs-6">
                    <div class="form-group">
                      <label for="inputPOPport">POP Port* </label>
                      <input type="text" name="popPort" class="form-control" id="inputPOPport" placeholder="110 or 995" required>
                    </div>
                  </div>

            </div>

            <div class="row">
              <div class="col-xs-12 text-center">
                <button type="submit" class="btn btn-primary btn-lg">Register</button>
              </div>
            </div>


        </form>

  </div> <!-- col-->

 </div> <!-- row -->
</div> <!-- /container -->


>>