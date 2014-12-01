userinfo(user, defaultAccount, secondAccount) ::= <<
<br>

<div class="container">
  <div class="row">
      <div class="col-xs-2 col-xs-offset-5">
          <a href="/home" class="thumbnail">
             <img src="/files/images/logo.jpg" alt="125x125">
          </a>
      </div>
      <div class="col-xs-12 text-center">
          <h3>$user.name$'s Account Information</h3>
          <span class="help-block">The items with * are required.</span>
      </div>
  </div>
</div>


<div class="container">
 <div class="row">

  <div class="col-xs-8 col-xs-offset-2">
        <form method=post action="/changePW">
          <div class="row">
                <div class="col-xs-6">
                  <div class="form-group">
                    <label for="username">Username*&nbsp;&nbsp;</label>
                    <input type="username" class="form-control" value="$user.name$">
                  </div>
                </div>

          </div>

          <div class="row">
                <div class="col-xs-6">
                      <div class="form-group">
                        <label for="userPassword">Password*</label>
                        <input type="password" class="form-control" value="$user.password$" name="password">
                      </div>
                </div>

                <div class="col-xs-6">
                      <div class="form-group">
                        <label for="userRetypePassword">Retype Password*</label>
                        <input type="password" class="form-control" value="$user.password$" name="retype">
                      </div>
                </div>
          </div>

        <div class="row">
          <div class="col-xs-12 text-center">
            <button type="submit class="btn btn-primary btn-lg">Update(Change Password)</button>
          </div>
        </div>
       </form>



        <div id = "panel-default-accnt" class="panel panel-info" >
            <div class="panel-heading">
              <b>Default Account</b>
              <label >
                &nbsp;&nbsp;
              </label>
            </div>

            <div class="panel-body">


                    <div class="row">
                          <div class="col-xs-6">
                            <div class="form-group">
                              <label for="inputEmail">Email*</label>
                              <input type="email" class="form-control" value="$defaultAccount.emailAddress$">
                            </div>
                          </div>

                            <div class="col-xs-2">
                              <div class="form-group">
                                <label for="SMTPssl">SSL</label>
                                <input type="checkbox" class="form-control" id="sslCheckbox" ssl="$defaultAccount.SSL$">
                              </div>
                            </div>
                    </div>

                    <div class="row">
                          <div class="col-xs-6">
                            <div class="form-group">
                              <label for="inputSMTP">Username*</label>
                              <input type="text" class="form-control" value="$defaultAccount.userName$">
                            </div>
                          </div>

                          <div class="col-xs-6">
                                <div class="form-group">
                                  <label for="inputPassword">Password*</label>
                                  <input type="password" class="form-control" value="$defaultAccount.password$">
                                </div>
                          </div>
                    </div>


                <div class="row">
                          <div class="col-xs-6">
                                <div class="form-group">
                                  <label for="inputSMTP">SMTP*</label>
                                  <input type="text" class="form-control" value="$defaultAccount.smtpServer$">
                                </div>
                          </div>

                          <div class="col-xs-6">
                            <div class="form-group">
                              <label for="inputSMTPport">SMTP Port*</label>
                              <input type="text" class="form-control" value="$defaultAccount.smtpPort$">
                            </div>
                          </div>

                </div>

                <div class="row">
                      <div class="col-xs-6">
                        <div class="form-group">
                          <label for="inputPOP">POP*</label>
                          <input type="text" class="form-control" value="$defaultAccount.popServer$">
                        </div>
                      </div>

                      <div class="col-xs-6">
                        <div class="form-group">
                          <label for="inputPOPport">POP Port* </label>
                          <input type="text" class="form-control" value="$defaultAccount.popPort$">
                        </div>
                      </div>
                </div>

        </div>
      </div>



        $secondAccount:second_account()$


        <div class="row">
          <div class="col-xs-12 text-right" id="add-account">
            <a id="link-add-account" style="cursor: pointer;"  data-toggle="tooltip" data-placement="bottom" title="Add another account">
              <span class="glyphicon glyphicon-plus"></span> <span class="glyphicon glyphicon-user"></span>
            </a>
          </div>
        </div> <!-- row -->

        <div class="row">
          <div class="col-xs-12 text-right" id="remove-account" style="display:none">
            <a id="link-add-account" style="cursor: pointer;"  data-toggle="tooltip" data-placement="bottom" title="Remove the additional account">
              <span class="glyphicon glyphicon-minus"></span> <span class="glyphicon glyphicon-user"></span>
            </a>
          </div>
        </div> <!-- row -->




      </div> <!-- col-->

</div> <!-- row -->
</div> <!-- /container -->






>>
