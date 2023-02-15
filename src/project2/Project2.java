
package project2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Project2 {
    static Department dep1=new Department();
    static Department dep2=new Department();
    static Department dep3=new Department();
    static List<Department> departments=new ArrayList();
    
    public static boolean loadTeacherFromFile()throws FileNotFoundException{   
        File file=new File("teachers.txt");
        Scanner input=new Scanner(file);
        String line="";
        while(input.hasNextLine()){
            line=input.next();
            String [] teacher_info=line.split("/");
            String name=teacher_info[0];
            String id=teacher_info[1];
            String sp=teacher_info[2];
            String degree=teacher_info[3];
            String work_type=teacher_info[4];
            String hours=teacher_info[5];
            String dep_id=teacher_info[6];
            //new teacher
            Teacher teacher=new Teacher();
            teacher.setName(name);
            teacher.setId(id);
            teacher.setSpecialty(sp);
            teacher.setWorkType(work_type);
            teacher.setDepartmentId(dep_id);
            teacher.setDegree(degree);
            teacher.setHours(Integer.parseInt(hours));
            teacher.setDegreeRate();
            if(dep_id.equals(dep1.getId())){
                dep1.teachers.add(teacher);
            }
            else if(dep_id.equals(dep2.getId())){
                dep2.teachers.add(teacher);
            }
            else if(dep_id.equals(dep3.getId())){
                dep3.teachers.add(teacher);
            }
    }
        return true;  
}
    public static boolean loadStaffFromFile()throws FileNotFoundException{  
        File file=new File("staff.txt");
        Scanner input=new Scanner(file);
        String line="";
        while(input.hasNextLine()){
            line=input.next();
            String [] staff_info=line.split("/");
            String name=staff_info[0];
            String id=staff_info[1];
            String workLoad=staff_info[2];
            String dep_id=staff_info[3];
            //new staff
            Staff staff=new Staff();
            staff.setName(name);
            staff.setId(id);
            staff.setWorkLoud(Integer.parseInt(workLoad));
            staff.setDepartmentId(dep_id);
            if(dep_id.equals(dep1.getId())){
                dep1.staff.add(staff);
            }
            else if(dep_id.equals(dep2.getId())){
                dep2.staff.add(staff);
            }
            else if(dep_id.equals(dep3.getId())){
                dep3.staff.add(staff);
            }
    }
        return true;
    
}
    public static boolean addTeacher(Teacher teacher) throws IOException, DepartmentNotFound, MemberAlreadyExist{
        boolean exist=false;
        String dep_id=teacher.getDepartmentId();
        for(Department d:departments){
            for(Teacher teacher1:d.teachers){
                if(teacher.getId().equals(teacher1.getId())){
                    exist=true;
                    break;
                }
            }
        }
        if(exist){
            throw new MemberAlreadyExist("teacher already exist");
        }
        else{
            if(dep_id.equals(dep1.getId())){
                dep1.teachers.add(teacher);
            }
            else if(dep_id.equals(dep2.getId())){
                dep2.teachers.add(teacher);
            }
            else if(dep_id.equals(dep3.getId())){
                dep3.teachers.add(teacher);
            }
            else{
                throw new DepartmentNotFound("Department Not Found");
            }
       } 
        return true;
    }
    public static boolean addStaff(Staff staff) throws IOException, DepartmentNotFound, MemberAlreadyExist{
         boolean exist=false;
       String dep_id=staff.getDepartmentId();
       for(Department d:departments){
            for(Staff staff1:d.staff){
                if(staff.getId().equals(staff1.getId())){
                    exist=true;
                    break;
                }
            }
        }
        if(exist){
            throw new MemberAlreadyExist("staff already exist");
        }
        else{
            if(dep_id.equals(dep1.getId())){
                dep1.staff.add(staff);
            }
            else if(dep_id.equals(dep2.getId())){
                dep2.staff.add(staff);
            }
            else if(dep_id.equals(dep3.getId())){
                dep3.staff.add(staff);
            }
            else{
                throw new DepartmentNotFound("Department Not Found");
            }
        }
       return true;
    }
    public static void printTeacher(JTextArea text){
        text.setText("");
        for(Department d:departments){
            for(Teacher teacher:d.teachers){
                text.setText(text.getText()+teacher.toString()+"\n");
            }
        }
    }
    public static void printStaff(JTextArea text){
        text.setText("");
        for(Department d:departments){
            for(Staff staff:d.staff){
                text.setText(text.getText()+staff.toString()+"\n");
            }
        }
    }
    
    public static void setDean(){
        JFrame frame3=new JFrame();
        frame3.setSize(300, 400);
        JPanel panel3=new JPanel();
        panel3.setLayout(null);
        JLabel ll1=new JLabel("teacher id");
        ll1.setBounds(10, 10, 75, 50);
        JTextField ttf1=new JTextField();
        ttf1.setBounds(100, 10, 100, 30);
        //
        JLabel ll2=new JLabel("department id");
        ll2.setBounds(10, 70, 75, 50);
        JTextField ttf2=new JTextField();
        ttf2.setBounds(100, 70, 100, 30);
        //   
        JButton b4=new JButton("dean");
        b4.setBounds(100, 200, 100, 40);
        panel3.add(b4);
        //
        panel3.add(ll1);
        panel3.add(ttf1);
        panel3.add(ll2);
        panel3.add(ttf2);
        panel3.add(b4);
        frame3.add(panel3);
        frame3.setLocationRelativeTo(null);
        frame3.setVisible(true);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String teacher_id=ttf1.getText();  
                String dep_id=ttf2.getText();  
                try {
                    find(teacher_id,dep_id);
                } catch (TeacherNotInDepartment ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public static boolean find(String id,String dep_id) throws TeacherNotInDepartment{
        for(Department d:departments){
            if(d.getId().equals(dep_id)){
                for(Teacher t:d.teachers){
                    if(t.getId().equals(id)){
                        d.setDean(t);
                        return true;
                    }
                }
            }        
        }
        throw new TeacherNotInDepartment("Teacher Not In Department");
    }
    // set departments informations
    public static boolean setDep(){
        dep1.setId("10");
        dep2.setId("20");
        dep3.setId("30");
        departments.add(dep1);
        departments.add(dep2);
        departments.add(dep3);
        return true;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {     
        setDep();
       
        try{
            loadTeacherFromFile();
        }catch(java.util.NoSuchElementException e){}
        try{
            loadStaffFromFile();
        }catch(java.util.NoSuchElementException e){}
        JFrame frame=new JFrame("tiny college");
        frame.setSize(600, 600);
        JPanel panel1=new JPanel();
        panel1.setLayout(null);
        JButton b1=new JButton("add teacher");
        JButton b2=new JButton("add staff");
        JButton print1=new JButton("teachers");
        JButton print2=new JButton("staff");
        JButton set_dean=new JButton("set dean");
        b1.setSize(100, 75);
        b2.setSize(100, 75);
        b1.setLocation(450, 20);
        b2.setLocation(450, 100);
        print1.setBounds(450, 180, 100, 75);
        print2.setBounds(450, 260, 100, 75);
        set_dean.setBounds(450, 340, 100, 75);
        JTextArea text=new JTextArea();
        text.setBounds(100, 100, 200, 300);
        Font font=new Font("time roman",0, 20);
        text.setFont(font);
        text.setEditable(false);
        JScrollPane sc=new JScrollPane(text);
        sc.setBounds(30, 30, 400, 400);
        panel1.add(sc);
        panel1.add(b1);
        panel1.add(b2);
        panel1.add(print1);
        panel1.add(print2);
        panel1.add(set_dean);
        frame.add(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //
        JFrame frame2=new JFrame();
        frame2.setSize(300, 550);
        JPanel panel2=new JPanel();
        panel2.setLayout(null);
        JLabel l1=new JLabel("name");
        l1.setBounds(10, 10, 50, 50);
        JTextField tf1=new JTextField();
        tf1.setBounds(100, 10, 100, 30);
        //
        JLabel l2=new JLabel("ID");
        l2.setBounds(10, 70, 50, 50);
        JTextField tf2=new JTextField();
        tf2.setBounds(100, 70, 100, 30);
        //
        JLabel l3=new JLabel("Specialty");
        l3.setBounds(10, 130, 75, 50);
        JTextField tf3=new JTextField();
        tf3.setBounds(100, 130, 100, 30);
        //
        JLabel l4=new JLabel("degree");
        l4.setBounds(10, 190, 75, 50);
        JTextField tf4=new JTextField();
        tf4.setBounds(100, 190, 100, 30);
        //
        JLabel l5=new JLabel("work type");
        l5.setBounds(10, 250, 75, 50);
        JTextField tf5=new JTextField();
        tf5.setBounds(100, 250, 100, 30);
        //
        JLabel l6=new JLabel("worked hours");
        l6.setBounds(10, 310, 100, 50);
        JTextField tf6=new JTextField();
        tf6.setBounds(100, 310, 100, 30);
        //
        JLabel l7=new JLabel("department");
        l7.setBounds(10, 370, 75, 50);
        JTextField tf7=new JTextField();
        tf7.setBounds(100, 370, 100, 30);
        //
        JButton b3=new JButton("add");
        b3.setBounds(100, 450, 100, 40);
        panel2.add(b3);
        //
        panel2.add(l1);
        panel2.add(tf1);
        panel2.add(l2);
        panel2.add(tf2);
        panel2.add(l3);
        panel2.add(tf3);
        panel2.add(l4);
        panel2.add(tf4);
        panel2.add(l5);
        panel2.add(tf5);
        panel2.add(l6);
        panel2.add(tf6);
        panel2.add(l7);
        panel2.add(tf7);
        frame2.add(panel2);
        frame2.setLocationRelativeTo(null);
        //
        JFrame frame3=new JFrame();
        frame3.setSize(300, 550);
        JPanel panel3=new JPanel();
        panel3.setLayout(null);
        JLabel ll1=new JLabel("name");
        ll1.setBounds(10, 10, 50, 50);
        JTextField ttf1=new JTextField();
        ttf1.setBounds(100, 10, 100, 30);
        //
        JLabel ll2=new JLabel("ID");
        ll2.setBounds(10, 70, 50, 50);
        JTextField ttf2=new JTextField();
        ttf2.setBounds(100, 70, 100, 30);
        //
        JLabel ll3=new JLabel("work load");
        ll3.setBounds(10, 130, 75, 50);
        JTextField ttf3=new JTextField();
        ttf3.setBounds(100, 130, 100, 30);
        //
        JLabel ll4=new JLabel("department");
        ll4.setBounds(10, 190, 75, 50);
        JTextField ttf4=new JTextField();
        ttf4.setBounds(100, 190, 100, 30);
        //     
        JButton b4=new JButton("add");
        b4.setBounds(100, 300, 100, 40);
        panel3.add(b4);
        //
        panel3.add(ll1);
        panel3.add(ttf1);
        panel3.add(ll2);
        panel3.add(ttf2);
        panel3.add(ll3);
        panel3.add(ttf3);
        panel3.add(ll4);
        panel3.add(ttf4);
        frame3.add(panel3);
        frame3.setLocationRelativeTo(null);
        //
        //
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                frame2.setVisible(true);              
            }
        });
        
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame3.setVisible(true);      
            }
        });
        
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=tf1.getText();
                String id=tf2.getText();
                String sp=tf3.getText();
                String degree=tf4.getText();
                String workType=tf5.getText();
                String hours=tf6.getText();
                String dep_id=tf7.getText();
                Teacher teacher=new Teacher();
                teacher.setName(name);
                teacher.setId(id);
                teacher.setSpecialty(sp);
                teacher.setWorkType(workType);
                teacher.setDepartmentId(dep_id);
                teacher.setDegree(degree);
                teacher.setHours(Integer.parseInt(hours));
                teacher.setDegreeRate();
                try {
                    addTeacher(teacher);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DepartmentNotFound ex) {
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MemberAlreadyExist ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
        });
        
         b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name=ttf1.getText();
                String id=ttf2.getText();
                int workLoad=Integer.parseInt(ttf3.getText());
                String dep_id=ttf4.getText();
                Staff staff=new Staff();
                staff.setName(name);
                staff.setId(id);
                staff.setWorkLoud(workLoad);
                staff.setDepartmentId(dep_id);
                try {
                    if(workLoad<=40)
                        addStaff(staff);
                    else
                        JOptionPane.showMessageDialog(null,"workload should be less than 40");
                } catch (IOException ex) {
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DepartmentNotFound ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MemberAlreadyExist ex) {
                    JOptionPane.showMessageDialog(null,ex);
                    Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
                }
        
            }
        });
         
         print1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printTeacher(text);   
            }
        });
         print2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                printStaff(text);   
            }
        });
         set_dean.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDean(); 
            }
        });
             
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windoEvent){
                File file1=new File("teachers.txt");
                File file2=new File("staff.txt");
       
                try {
                    BufferedWriter bw1 = new BufferedWriter(new FileWriter(file1));
                    BufferedWriter bw2=new BufferedWriter(new FileWriter(file2));
                    for(Department d:departments){
                        for(Teacher teacher:d.teachers){
                            String teacherInfo=teacher.getName()+"/"+teacher.getId()+"/"+teacher.getSpecialty()+"/"+teacher.getDegree()+"/"+teacher.getWorkType()+"/"+teacher.getHours()+"/"+teacher.getDepartmentId();                          
                            bw1.write(teacherInfo+"\n");
                        }
                        for(Staff staff:d.staff){
                            String staffInfo=staff.getName()+"/"+staff.getId()+"/"+staff.getWorkload()+"/"+staff.getDepartmentId();
                            bw2.write(staffInfo);
                            bw2.newLine();
                        }
                    }
                bw1.close();
                bw2.close();
                }catch (IOException ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
               
      System.exit(0);
            }
        });
    }
        
   }
    

