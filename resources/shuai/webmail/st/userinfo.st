userinfo() ::= <<
  <h1>Userinfo</h1>

  <form method=get action=/>
  <input type=submit value=Logout>
  </form>

  <form method=get action=/inbox>
   User: <input type=text name=user><br>
   Password: <input type=text name=password><br><br>
   <input type=submit value=Update>
   <input type=submit value=Cancel>
   </form>

>>