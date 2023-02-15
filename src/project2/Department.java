
package project2;

import java.util.ArrayList;
import java.util.List;

public class Department {
    public List <Teacher>teachers=new ArrayList();
    public List <Staff>staff=new ArrayList();
    private Teacher dean;
    private String id;
    //sets
    public void setDean(Teacher dean){
        this.dean=dean;
    }
    public void setId(String id){
        this.id=id;
    }
    //gets
    public Teacher getDean(){
        return dean;
    }
    public String getId(){
        return id;
    }
}
