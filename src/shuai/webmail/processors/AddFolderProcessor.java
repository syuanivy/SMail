package shuai.webmail.processors;

import shuai.webmail.entities.Account;
import shuai.webmail.managers.AccountManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/28/14.
 */
public class AddFolderProcessor extends PostProcessor {

    public AddFolderProcessor(HttpServletRequest request,
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
    public void processPost() throws SQLException, IOException{
        Account accountToShow = (Account) request.getSession().getAttribute("accountToShow");
        if(accountToShow == null){
            response.sendRedirect("/login");
            return;
        }

        String foldername = request.getParameter("foldername");
        accountToShow.folders.newFolder(foldername);
        AccountManager.updateFolders(accountToShow);
        response.sendRedirect("/home");
    }

}
