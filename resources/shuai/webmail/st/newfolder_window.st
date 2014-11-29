newfolder_window(account) ::= <<


<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h4 class="modal-title">Create New Folder</h4>
    </div>

    <form role="form" class="form-horizontal" method="post" action="/home/addfolder">
        <div class="modal-body">

                  <div class="form-group">
                    <label class="col-sm-1" for="inputName">Folder Name</label>
                    <div class="col-sm-11"><input type="text" name="foldername" class="form-control" id="inputName" placeholder="Family, Work, Ads..."></div>
                  </div>

        </div>

        <div class="modal-footer">
            <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
            <button type="submit" class="btn btn-primary ">Create<i class="fa fa-arrow-circle-right fa-lg"></i></button>
        </div>
    </form>

  </div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->

>>