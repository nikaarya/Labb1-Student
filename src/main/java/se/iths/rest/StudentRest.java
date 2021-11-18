package se.iths.rest;


import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("students")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentRest {

    private static final String exceptionStarter = "Student with ID: ";

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        try {
            studentService.createNewStudent(student);
            return Response.ok(student).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_ACCEPTABLE)
                    .entity("Error when creating new student " + e).type(MediaType.APPLICATION_JSON).build());
        }
    }

    @Path("updateemail/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        try {
            Student updatedStudentEmail = studentService.updateStudentEmail(id, email);
            return Response.ok(updatedStudentEmail).build();
        } catch (Exception e) {

            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(exceptionStarter + id + " was not found").type(MediaType.TEXT_PLAIN).build());
        }
    }

    @Path("")
    @GET
    public Response getAllStudents() {
        List<Student> listOfStudents = studentService.getAllStudents();
        if (listOfStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no students in the database").type(MediaType.TEXT_PLAIN).build());
        }
        return Response.ok(listOfStudents).build();
    }

    // funkar ej
    @Path("{lastname}")
    @GET
    public Response getStudentByLastName(@PathParam("lastname") String lastName) {

        List<Student> foundStudent = studentService.getStudentByLastName(lastName);
        try {
            return Response.ok(foundStudent).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("Could not find student with lastname: " + lastName).type(MediaType.TEXT_PLAIN).build());
        }
    }

    //        if (foundStudent == null) {
//            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
//                    .entity("Student with lastname: " + lastName + " was not found in database")
//                    .type(MediaType.TEXT_PLAIN_TYPE).build());


    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.ok(exceptionStarter + id + " has been deleted").type(MediaType.TEXT_PLAIN_TYPE).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(exceptionStarter + id + " does not exist in the database").type(MediaType.TEXT_PLAIN_TYPE).build());
        }
    }
}
