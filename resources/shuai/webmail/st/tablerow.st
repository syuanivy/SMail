tablerow(email) ::= <<
<tr class="clickableRow" id="$email.id$" label="testlabel" href="/display?id=$email.id$&label=$email.label$" >
  <td>$email.sender$</td>
  <td>$email.subject$</td>
  <td>$email.body$</td>
  <td>$email.time$</td>
</tr>
>>