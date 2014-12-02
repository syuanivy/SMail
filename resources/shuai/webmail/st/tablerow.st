tablerow(email) ::= <<
<tr  id="$email.id$" href="/display?id=$email.id$&label=$email.label$" msg_label="$email.label$">
  <td ><input type="checkbox"></td>
  <td class="td_sender">$email.sender$</td>
  <td class="td_subject">$email.subject$</td>
  <td class="td_message">$email.body$</td>
  <td class="td_time">$email.time$</td>
</tr>
>>