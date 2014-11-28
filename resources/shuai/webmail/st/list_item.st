list_item(folder) ::= <<
    <a href="/home?folder=$folder.label$" folder="$folder.label$" class="list-group-item">
        <span class="glyphicon glyphicon-user"></span> $folder.foldername$ <span class="badge">$folder.count$</span>
    </a>
>>