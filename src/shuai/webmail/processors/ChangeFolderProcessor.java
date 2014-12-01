package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.entities.Outgoing;
import shuai.webmail.managers.EmailManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ivy on 11/27/14.
 */
public class ChangeFolderProcessor extends PostProcessor {
    public ChangeFolderProcessor(HttpServletRequest request,
                              HttpServletResponse response){
        super(request, response);
    }
    @Override
    public void verify() throws IOException {
        if(request.getSession().getAttribute("user")==null || request.getSession().getAttribute("accountToShow")==null){
            try{
                response.sendRedirect("/");
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @Override
    public void processPost() throws SQLException, IOException {

        String ids = request.getParameter("selected_mails");
        String[] id_array = ids.split(",");
        if(id_array[0].equals("")) {
            response.sendRedirect("/home");
            return;
        }
        int labelAfter = Integer.parseInt(request.getParameter("labelAfter"));
        if(labelAfter==0) labelAfter=3;
        for (String id: id_array) {
            EmailManager.changeFolder(id, labelAfter);
        }
        response.sendRedirect("/home");
    }


}
