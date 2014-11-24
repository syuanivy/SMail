home_tabledisplay(inbox) ::= <<

<div class="panel panel-primary">
				<!--
				<div class="panel-heading">
					Panel heading without title
				</div>
				-->

				<div class="panel-body">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Sender</th>
								<th>Subject</th>
								<th>Message</th>
								<th>Datetime</th>
							</tr>
						</thead>

						<tbody>
							$inbox:tablerow()$
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