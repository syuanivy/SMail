viewmsg() ::= <<
  <h1>View MSG</h1>
    <form method=get action=/logout>
    <input type=submit value=Logout>
    </form>

    <form method=get action=/inbox>
    Emailbody:<input type=text name=emailbody><br><br>
    <input type=submit value=Return to Inbox>
    <input type=submit value=Save>
    <input type=submit value=Cancel>
    </form>

    <form method=get action=/reply>
    <input type=submit value=Reply>
    </form>



>>