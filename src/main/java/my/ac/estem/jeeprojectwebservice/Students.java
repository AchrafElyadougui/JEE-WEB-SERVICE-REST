package my.ac.estem.jeeprojectwebservice;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import my.ac.estem.jeeprojectwebservice.dao.DaoImp;
import my.ac.estem.jeeprojectwebservice.dao.IDao;
import my.ac.estem.jeeprojectwebservice.dao.Student;

@Path("/studentListe")
public class Students {

    private final IDao dao = new DaoImp();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Student> getAllStudents() {
        return dao.getAllStudent();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudentById(@PathParam("id") int id) {
        Student student = dao.getStudentById(id);
        if (student == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(student).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(Student student) {
        dao.addStudent(student);
        return Response.status(Response.Status.CREATED).entity(student).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Student student) {
        Student existing = dao.getStudentById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.updateStudent(id, student);
        student.setIdStudent(id);
        return Response.ok(student).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") int id) {
        Student existing = dao.getStudentById(id);
        if (existing == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        dao.deleteStudent(id);
        return Response.noContent().build();
    }
}
