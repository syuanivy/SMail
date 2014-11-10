home_searchrow(user) ::= <<

<div class="row">
				<div class="col-xs-2">
				    <form method ="post" action ="/home/fetch">
					    <button type="submit" class="btn btn-primary" >
						    <span class="glyphicon glyphicon-refresh"></span> Refresh
					    </button>
					</form>
				</div>
				<div class="col-xs-8">
					<form class="form-inline">
						<div class="form-group">
							<label class="sr-only" for="searchEmail">Email</label>
							<input type="search" class="form-control" id="searchEmail" placeholder="Sender, Email, ...">
						</div>
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-search"></span> Search
						</button>
					</form>
				</div>
			</div>

>>