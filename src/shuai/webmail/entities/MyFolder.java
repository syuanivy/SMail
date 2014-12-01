package shuai.webmail.entities;

/**
 * Created by ivy on 11/28/14.
 */
public class MyFolder {
    public int label;
    public String foldername;
    public int count=0;
    public MyFolder(int label, String foldername){
        this.label = label;
        this.foldername = foldername;
    }

    public void setCount(int count){this.count = count;}
}
