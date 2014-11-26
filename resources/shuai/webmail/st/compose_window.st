compose_window(sender) ::= <<
  <div class="modal-dialog">
	<div class="modal-content">
	  <div class="modal-header">
		<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
		<h4 class="modal-title">Compose Message</h4>
	  </div>
	<form role="form" class="form-horizontal" method="post" action="/composeprocessor">
	  <div class="modal-body">
			<div class="form-group">
			  <label class="col-sm-2" for="inputFrom">From</label>
			  <div class="col-sm-10"> <p class="form-control-static">$sender$</p> </div>
			</div>
			<div class="form-group">
			  <label class="col-sm-2" for="inputTo">To</label>
			  <div class="col-sm-10"><input type="email" name="recipient" class="form-control" id="inputTo" placeholder="comma separated list of recipients"></div>
			</div>
			<div class="form-group">
			  <label class="col-sm-2" for="inputSubject">Subject</label>
			  <div class="col-sm-10"><input type="text" name="subject" class="form-control" id="inputSubject" placeholder="subject"></div>
			</div>
			<div class="form-group">
			  <label class="col-sm-12" for="inputBody">Message</label>
			  <div class="col-sm-12"><textarea spellcheck="true" name="body" class="form-control" id="inputBody" rows="10"></textarea></div>
			</div>
	  </div>

	  <div class="modal-footer">
		<button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
		<button type="button" class="btn btn-warning pull-left">Save Draft</button>
		<button type="submit" class="btn btn-primary ">Send <i class="fa fa-arrow-circle-right fa-lg"></i></button>
	  </div>
    </form>
	</div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
  >>