login() ::= <<
  <h1>Welcome to Shuai's Webmail</h1>

  <form method=post action=/loginprocessor>
    Username: <input type=text name=username><br>
    Password: <input type=password name=password><br><br>
    <input type=submit value=Login>
  </form>

  <p>Do not have an account?</p>

   <form method=get action=/register>
     <input type=submit value=Create>
   </form>
>>