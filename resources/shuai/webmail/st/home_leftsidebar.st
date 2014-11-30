home_leftsidebar(account, count0, count1,count2,count3, userdefinedlist) ::= <<

     <div class="col-xs-2">
            <a href="/home" class="thumbnail">
                <img src="/files/images/logo.jpg" alt="125x125">
            </a>

			<div class="list-group">
				<a href="#" class="list-group-item active" data-toggle="modal" data-target="#modalCompose">
					<span class="glyphicon glyphicon-pencil"></span> Compose
				</a>
				<a href="/home?folder=0" folder="0" class="list-group-item">
                   <span class="glyphicon glyphicon-envelope"></span> Inbox <span class="badge">$count0$</span>
                </a>
                <a href="/home?folder=1" folder="1" class="list-group-item">
                <span class="glyphicon glyphicon-share"></span> Sent <span class="badge">$count1$</span>
                </a>
                <a href="/home?folder=2" folder="2" class="list-group-item">
                <span class="glyphicon glyphicon-pencil"></span> Draft <span class="badge">$count2$</span>
                </a>
                <a href="/home?folder=4" folder="4" class="list-group-item">
                <span class="glyphicon glyphicon-trash"></span> Trash <span class="badge">$count3$</span>
                </a>

				$userdefinedlist:list_item()$

			</div>
	</div>

>>