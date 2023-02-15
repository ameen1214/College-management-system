
package project2;


public class Teacher extends Person implements PayRoll{
    private String specialty;
    private String degree;
    private String work_type;
    private int degreeRate;
    private int hoursWorked;
    
    //sets
    public void setSpecialty(String specialty){
        this.specialty=specialty;
    }
    public void setDegree(String degree){
        this.degree=degree;
    }
    public void setWorkType(String work_type){
        this.work_type=work_type;
    }
    public void setHours(int hour){
        this.hoursWorked=hour;
    }
    public void setDegreeRate(){
        switch(degree){
            case "PhD": degreeRate=112;break;
            case "Master": degreeRate=82;break;
            case "Bachelor": degreeRate=42;break;
            default: degreeRate=0;break;
        }
    }
    
    //gets
    public String getSpecialty(){
        return specialty;
    }
    public String getDegree(){
        return degree;
    }
    public String getWorkType(){
        return work_type;
    }
    public int getDegreeRate(){
        return degreeRate;
    }
    public int getHours(){
        return hoursWorked;
    }
    @Override
    public boolean equals(Object ob){
        if(this==ob)
            return true;
        else
            return false;
    }
    @Override
    public String toString(){
        String str="name: "+getName()+"\n"+"id: "+getId()+"\n"+"specialty: "+getSpecialty()+"\n"+"degree: "+getDegree()+"\n"+"work type: "+getWorkType()+"\n"
                +"hours Worked: "+getHours()+"\n"+"department id: "+getDepartmentId()+"\n"+"salary: "+ComputePayRoll()+"\n";
        return str;
    }

    @Override
    public double ComputePayRoll() {
        if(work_type.equals("full-time")){
            return (32*degreeRate*2)*0.85;
        }
        else{
            return (hoursWorked*degreeRate*2)*0.76;
        }
    }

    @Override
    public void Category() {
        setCategory("teacher");
    }
    
}
