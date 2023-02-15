
package project2;

public class Staff extends Person implements PayRoll{
    private double duty;
    private int workload;
    //sets
    public void setDuty(double duty){
        this.duty=duty;
    }
    public void setWorkLoud(int workload){
        this.workload=workload;
    }
    //gets
    public double getDuty(){
        return this.duty;
    }
    public int getWorkload(){
        return this.workload;
    }
    @Override
    public boolean equals(Object ob){
        if(this==ob)
            return true;
        else
            return false;
    }
    public String toString(){
        String str="name: "+getName()+"\n"+"id: "+getId()+"\n"+"workload: "+getWorkload()+"\n"+"department id: "+getDepartmentId()+"\n"+"salary: "+ComputePayRoll()+"\n";
        return str;
    }

    @Override
    public double ComputePayRoll() {
        return (workload * 32 * 2) * 0.75;
    }

    @Override
    public void Category() {
        setCategory("staff");
    }
}
