page(title, body) ::= <<
<html>

<head>
       <meta charset="utf-8">
       <title>$title$</title>
       <meta name="viewport" content="width=device-width, initial-scale=1.0">
   	<!-- include the following Bootstrap files-->
       <link rel="stylesheet" type="text/css" href="/files/css/bootstrap.min.css">
       <link rel="stylesheet" type="text/css" href="/files/css/bootstrap-theme.min.css">
   	   <script src="http://code.jquery.com/jquery.min.js"></script>
   	   <script src="/files/libs/jquery/jquery-1.8.2.min.js"></script>
       <script src="/files/js/bootstrap.min.js"></script>
       <link href="/files/bootstrap-editable/css/bootstrap-editable.css" rel="stylesheet">
       <script src="/files/bootstrap-editable/js/bootstrap-editable.min.js"></script>

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
              });

              \$("#link-forward").click(function(){
              	\$("#panel-forward").toggle();
              });

              \$(function() {
                \$(document).on("click",".clickableRow",function() {
                   window.document.location = \$(this).attr("href");
                  });
              });
         });
       </script>


</head>

<body>
    $body$
</body>

</html>
>>
