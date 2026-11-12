package my.ac.estem.jeeprojectwebservice.dao;

import java.util.List;

public interface IDao {

    public void addStudent(Student s);
    public Student getStudentById(int id);
    public List<Student> getAllStudent();
    public void deleteStudent(int id);
    public void updateStudent(int id, Student s);


}