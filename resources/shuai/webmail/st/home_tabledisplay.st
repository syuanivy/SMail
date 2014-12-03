home_tabledisplay(maillist) ::= <<

<div class="panel panel-primary">
				<!--
				<div class="panel-heading">
					Panel heading without title
				</div>
				-->

				<div class="panel-body">
					<table id="table_msg" class="table table-hover" style="cursor: pointer;">
						<thead>
							<tr>
							    <th></th>
								<th>Sender</th>
								<th>Subject</th>
								<th>Message</th>
								<th>Datetime</th>
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