package my.ac.estem.jeeprojectwebservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoImp implements IDao{

    private final String s1="com.mysql.cj.jdbc.Driver";
    private final String s2="jdbc:mysql://localhost:3306/studentdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String s3="root";
    private final String s4="";



    @Override
    public void addStudent(Student s) {
        try {
            Class.forName(s1);
            try (Connection connection = DriverManager.getConnection(s2, s3, s4);
                 PreparedStatement ps = connection.prepareStatement("insert into STUDENTS(firstname,lastname, birthdate) values(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, s.getFirstNameStudent());
                ps.setString(2, s.getLastNameStudent());
                ps.setDate(3, s.getDateBirthStudent());
                ps.executeUpdate();
                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (keys.next()) {
                        s.setIdStudent(keys.getInt(1));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Student getStudentById(int id) {
        Student st = null;
        try {
            Class.forName(s1);
            try (Connection connection = DriverManager.getConnection(s2, s3, s4);
                 PreparedStatement ps = connection.prepareStatement("select * from STUDENTS where id = ?")) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        st = new Student();
                        st.setIdStudent(rs.getInt("id"));
                        st.setFirstNameStudent(rs.getString("firstname"));
                        st.setLastNameStudent(rs.getString("lastname"));
                        st.setDateBirthStudent(rs.getDate("birthdate"));
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return st;
    }

    @Override
    public List<Student> getAllStudent() {
        List<Student> stds= new ArrayList<Student>();

        try {
            Class.forName(s1);
            try (Connection con = DriverManager.getConnection(s2, s3, s4);
                 PreparedStatement ps = con.prepareStatement("select * from STUDENTS");
                 ResultSet rs = ps.executeQuery()) {

                while(rs.next()){
                    Student etd=new Student();
                    etd.setIdStudent(rs.getInt("id"));
                    etd.setFirstNameStudent(rs.getString("firstname"));
                    etd.setLastNameStudent(rs.getString("lastname"));
                    etd.setDateBirthStudent(rs.getDate("birthdate"));
                    stds.add(etd);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return stds;

    }

    @Override
    public void deleteStudent(int id) {
        try {
            Class.forName(s1);
            try (Connection connection = DriverManager.getConnection(s2, s3, s4);
                 PreparedStatement ps = connection.prepareStatement("delete from STUDENTS where id = ?")) {
                ps.setInt(1, id);
                ps.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void updateStudent(int id, Student s) {
        try {
            Class.forName(s1);
            try (Connection con = DriverManager.getConnection(s2, s3, s4);
                 PreparedStatement ps = con.prepareStatement("UPDATE STUDENTS SET firstname = ?, lastname = ?, birthdate = ? WHERE id = ? ")) {
                ps.setString(1, s.getFirstNameStudent());
                ps.setString(2, s.getLastNameStudent());
                ps.setDate(3, s.getDateBirthStudent());
                ps.setInt(4, id);
                ps.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
