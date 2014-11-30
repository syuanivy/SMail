dropdownlist(folder) ::= <<
    <li>
        <form class="form_move" method="post" action="/home/changefolder">
            <input type="hidden" name="selected_mails" value="0">
            <input type="hidden" name="labelAfter" value="$folder.label$">
            <button id="submit_move" type="submit" class="btn btn-link" data-toggle="tooltip" data-placement="top" >
                $folder.foldername$
            </button>
        </form>
    </li>


>>