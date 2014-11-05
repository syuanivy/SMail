package shuai.webmail.processors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by ivy on 11/1/14.
 */
public abstract class PostProcessor {
    HttpServletRequest request;
    HttpServletResponse response;

    public PostProcessor(HttpServletRequest request, HttpServletResponse response){
        this.request = request;
        this.response = response;

    }
    /**
     * redirect uri
     */

    public abstract void processPost() throws SQLException, IOException;

}

