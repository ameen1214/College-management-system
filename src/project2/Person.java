
package project2;


public abstract class Person {
    private String name;
    private String id;
    private int age;
    private double salary;
    private String category;
    private String department_id;
    
    //sets
    public void setName(String name){
        this.name=name;
    }
    public void setId(String id){
        this.id=id;
    }
    public void setDepartmentId(String department_id){
        this.department_id=department_id;
    }
    public void setAge(int age){
        this.age=age;
    }
    public void setSalary(double salary){
        this.salary=salary;
    }
    public void setCategory(String category){
        this.category=category;
    }
    
    //gets
    public String getName(){
        return name;
    }
    public int getAge(){
        return age;
    }
    public double getSalary(){
        return salary;
    }
    public String getId(){
        return id;
    }
    public String getDepartmentId(){
        return department_id;
    }
    public String getCategory(){
        return category;
    }
    // abstract method
    public abstract void Category();
}
