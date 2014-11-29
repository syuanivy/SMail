tablerow(email) ::= <<
<tr  id="$email.id$" href="/display?id=$email.id$&label=$email.label$" msg_label="$email.label$">
  <td><input type="checkbox"></td>
  <td>$email.sender$</td>
  <td>$email.subject$</td>
  <td>$email.body$</td>
  <td>$email.time$</td>
</tr>
>>