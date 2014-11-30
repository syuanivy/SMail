dropdownlist(folder) ::= <<
    <li>
        <form method=post action="/home/changefolder">
            <button type="submit" class="btn btn-link" labelAfter="$folder.label$" >$folder.foldername$</button>
        </form>
    </li>
>>