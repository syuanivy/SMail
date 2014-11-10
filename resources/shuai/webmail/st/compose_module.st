compose_module(sender) ::= <<

<div class="col-xs-10">
			$sender:home_searchrow()$

			<br>
			<div class="panel panel-primary">
            				<!--
            				<div class="panel-heading">
            					Panel heading without title
            				</div>
            				-->

            				<div class="panel-body">

			                <form method=post action=/composeprocessor>
                            From: $sender$<br><br>
                            Or: <input type=text name=sender><br><br>
                            To:<input type=text name=recipient><br><br>
                            Subject:<input type=text name=subject><br><br>
                            Text:<input type=text name=body><br><br>

                            <input type=submit value=Send>
                            <input type=submit value=Save>
                            <input type=submit value=Cancel>
                            </form>

            				</div>
            				<!--
            				<div class="panel-footer">
            					Panel footer
            				</div>
            				-->
            </div>


</div>

>>