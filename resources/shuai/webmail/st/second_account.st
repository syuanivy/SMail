second_account(user) ::= <<

        <div id = "panel-accnt-1" class="panel panel-info" style="display:none">
          <div class="panel-heading">
            <b>Additional Account</b>
            <label >
              &nbsp;&nbsp;<a href="#">check</a>
            </label>
          </div>

          <div class="panel-body">
                <form method=post action="/addaccount">
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
                            <input type="text" name="smtpPort" class="form-control" id="inputSMTPport" placeholder="25" required>
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
                        <input type="text" name="popPort" class="form-control" id="inputPOPport" placeholder="995" required>
                      </div>
                    </div>
              </div>


              <div class="row">
                <div class="col-xs-12 text-center">
                  <button type="submit class="btn btn-primary btn-lg">Add a Second Account</button>
                </div>
              </div>
          </form>

          </div> <!-- panel body-->
        </div> <!-- panel-->
>>