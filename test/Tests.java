

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import project2.DepartmentNotFound;
import project2.MemberAlreadyExist;
import project2.Project2;
import project2.Staff;
import project2.Teacher;
import project2.TeacherNotInDepartment;


public class Tests {
    project2.Project2 p=new Project2();
    public Tests() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void MainTest() {
        assertEquals(true, p.setDep());  
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test(expected = Exception.class)
     public void loadTeacherFromFileTest() throws FileNotFoundException {
         assertEquals(true, p.loadTeacherFromFile());   
     }
     @Test
     public void loadStaffFromFileTest() throws FileNotFoundException {
         assertEquals(true, p.loadStaffFromFile());   
     }
     //
     @Test 
     public void addTeacherTest() throws FileNotFoundException, IOException, DepartmentNotFound, MemberAlreadyExist {
         Teacher teacher=new Teacher();
         teacher.setName("samia");
         teacher.setDegree("Master");
         teacher.setHours(0);
         teacher.setSpecialty("Maths");
         teacher.setWorkType("full-time");
         teacher.setId("3400");
         teacher.setDepartmentId("10");
         assertEquals(true, p.addTeacher(teacher));   
     }
     //
     @Test
     public void addStaffTest() throws FileNotFoundException, IOException, DepartmentNotFound, MemberAlreadyExist {
         Staff staff=new Staff();
         staff.setName("lara");
         staff.setId("3800");
         staff.setWorkLoud(25);
         staff.setDepartmentId("10");
         assertEquals(true, p.addStaff( staff));   
     }
     //
     @Test(expected =TeacherNotInDepartment.class )
     public void findTest() throws FileNotFoundException, IOException, TeacherNotInDepartment {
         assertEquals(true, p.find("1005","20"));   
     }
}
