page(title, body) ::= <<
<html>

<head>
       <meta charset="utf-8">
       <title>$title$</title>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	<!-- include the following Bootstrap files-->
       <link rel="stylesheet" type="text/css" href="/files/css/bootstrap.min.css">
       <link rel="stylesheet" type="text/css" href="/files/css/bootstrap-theme.min.css">
   	   <script src="//code.jquery.com/jquery.min.js"></script>
       <script src="/files/js/bootstrap.min.js"></script>


       <script>
         \$(document).ready(function(){
             \$("#link-cc").click(function(){
              	\$("#formgroup-cc").toggle();
              });

              \$("#link-bcc").click(function(){
              	\$("#formgroup-bcc").toggle();
              });

             \$("#link-reply").click(function(){
              	\$("#panel-reply").toggle();
              	\$("#panel-forward").hide();
              });

              \$("#link-forward").click(function(){
              	\$("#panel-forward").toggle();
              	\$("#panel-reply").hide();
              });

              \$("#cancel-reply").click(function(){
              	\$("#panel-reply").hide();
              });

              \$("#cancel-forward").click(function(){
                \$("#panel-forward").hide();
              });

            \$("#table_msg tbody td:not(:first-child)").click(function() {
                  window.document.location = \$(this).parent().attr("href");
             });
            \$("#table_msg tbody tr[msg_label='0']").css({"font-weight":"bold"});

              \$("#add-account").click(function(){
                \$("#panel-accnt-1").show();
                \$("#add-account").hide();
                \$("#remove-account").show();
              });

              \$("#remove-account").click(function(){
                \$("#panel-accnt-1").hide();
                \$("#add-account").show();
                \$("#remove-account").hide();
              });

              if (\$("#sslCheckbox").attr("ssl") == 1){
                \$("#sslCheckbox").prop('checked', true);}
              else{
                \$("#sslCheckbox").prop('checked', false);}



              \$("#select_all").click(function() {
                    \$('#table_msg :checkbox').prop('checked', true);
                })

              \$("#select_none").click(function() {
                    \$('#table_msg :checkbox').prop('checked', false);
                })

              \$(".form_move").submit( function(e) {
                    var ids = "";  // hidden value to submit
                    \$('#table_msg input:checked').each(function() {
                        ids += \$(this).parents("tr").attr("id") + ",";
                    });
                    var input = \$(this).find("input[name=selected_mails]");
                    input.val( ids );   // set hidden value
                    return true;        //still POST; if return false, skip POST.
               })
               \$("#form_search").submit( function(e) {
                    var url = "/home?" + \$(this).serialize();
                    alert(url)
                    window.document.location = url;
                    return false; //override
                });
         });
       </script>


</head>

<body>
    $body$
</body>

</html>
>>
