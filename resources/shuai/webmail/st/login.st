login() ::= <<
  <h1>Welcome to Shuai's Webmail</h1>

      <form method=get action=/welcome>
      Username: <input type=text name=user><br>
      Password: <input type=text name=password><br><br>
      <input type=submit value=Login>
      </form>
      <p>Do not have an account?</p>
      <form method=get action=/register>

          <input type=submit value=Create>
        </form>
>>