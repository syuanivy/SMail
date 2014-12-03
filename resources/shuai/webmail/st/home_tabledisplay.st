home_tabledisplay(maillist) ::= <<

<div class="panel panel-primary">
				<!--
				<div class="panel-heading">
					Panel heading without title
				</div>
				-->

				<div class="panel-body">
					<table id="table_msg" class="table table-hover" style="cursor: pointer;word-break: break-all;">
						<thead>
							<tr>
							    <th width=4%></th>
								<th width=23%>Sender</th>
								<th width=20%>Subject</th>
								<th width=33>Message</th>
								<th width=20%>Datetime</th>
							</tr>
						</thead>

						<tbody>
							$maillist:tablerow()$
						</tbody>
					</table>
				</div>
				<!--
				<div class="panel-footer">
					Panel footer
				</div>
				-->
</div>
>>