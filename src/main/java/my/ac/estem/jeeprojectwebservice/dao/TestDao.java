package my.ac.estem.jeeprojectwebservice.dao;

import java.sql.Date;
import java.time.LocalDate;

public class TestDao {

    public static void main(String[] args) {
        DaoImp d= new DaoImp();
        //d.addStudent(new Student("ANWAR","ANWAR", Date.valueOf(LocalDate.of(1996, 03, 16))));
        //d.addStudent(new Student("Samah","Samha", Date.valueOf(LocalDate.of(2003, 03, 16))));
        //d.addStudent(new Student("cccc","AAA", Date.valueOf(LocalDate.of(2004, 03, 16))));
//        d.getAllStudent().forEach(et -> {
//        	System.out.println(et);
//        });
        //System.out.println(d.getStudentById(2));
        //d.deleteStudent(4);
        d.updateStudent(3, new Student("Asmae", "ELBAZE", Date.valueOf(LocalDate.of(2004, 03, 16))));
    }

}