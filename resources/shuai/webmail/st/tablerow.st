tablerow(email) ::= <<
<tr class="clickableRow" id="$email.id$" href="/display?id=$email.id$">
  <td>$email.sender$</td>
  <td>$email.subject$</td>
  <td>$email.body$</td>
  <td>$email.time$</td>
</tr>
>>