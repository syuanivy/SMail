home_searchrow(account,folders) ::= <<

<div class="row">
    <div class="col-xs-2">
       <div class="btn-group">
          <!-- drowdown button -->
          <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <span class="glyphicon glyphicon-list"></span> <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                <li><a id="select_all" href="#">All</a></li>
                <li><a id="select_none" href="#">None</a></li>
            </ul>
          </div>

          <!-- drowdown button -->
          <div class="btn-group">
            <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                <span class="glyphicon glyphicon-folder-close"></span> <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" role="menu">
                $folders:dropdownlist()$
            </ul>
          </div>
       </div>
    </div>

    <div class="col-xs-2">

              <div class="btn-group" role="group" aria-label="...">
                <form class="form_move" method="post" action="/home/changefolder">
                    <input type="hidden" name="selected_mails" value="0">
                    <input type="hidden" name="labelAfter" value="4">

                    <button type="submit" class="btn btn-default" data-toggle="tooltip" data-placement="top" title="Trash">
                          <span class="glyphicon glyphicon-trash"></span>
                    </button>


                    <button type="button" class="btn btn-default"  data-placement="top" title="Create folder"  data-toggle="modal" data-target="#modalCreateFolder">
                          <span class="glyphicon glyphicon-plus"></span>
                    </button>

                    <button type="submit" formaction="/emptytrash" class="btn btn-default" data-toggle="tooltip" data-placement="top" title="Empty Trash">
                          <span class="glyphicon glyphicon-fire"></span>
                    </button>
                </form>

              </div>

    </div>

    <div class="col-xs-1">
       <form method ="post" action ="/home/fetch">
          <button type="submit" class="btn btn-primary" data-toggle="tooltip" data-placement="top" title="Refresh">
                <span class="glyphicon glyphicon-refresh"></span>
          </button>
       </form>
    </div>

    <div class="col-xs-5">
          <form class="form-inline" id="form_search">
                <div class="form-group">
                      <label class="sr-only" for="search_input">Search Label</label>
                      <input type="search" name="keyword" class="form-control" id="search_input" placeholder="Sender, Subject, ...">
                </div>
                <button type="submit" class="btn btn-primary" >
                      <span class="glyphicon glyphicon-search"></span>
                </button>
                <div class="form-group">
                      <select class="form-control" name="by" >
                            <option>From</option>
                            <option>To</option>
                            <option>Subject</option>
                            <option>Message</option>
                      </select>
                </div>
          </form>
    </div>

    <div class="col-xs-1 text-right" >
        <!-- drowdown button -->
        <div class="btn-group">
          <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            <span class="glyphicon glyphicon-sort"></span> <span class="caret"></span>
          </button>
          <ul class="dropdown-menu" role="menu">
            <li><a href="/home?sortby=time" sortby="time">Time </a></li>
            <li><a href="/home?sortby=sender" sorby="sender">Sender </a></li>
            <li><a href="/home?sortby=subject" sortby="subject">Subject </a></li>
          </ul>
        </div>
    </div>

</div> <!-- div row-->

>>