package se.iths.rest;


import se.iths.entity.Student;
import se.iths.service.StudentService;

import javax.inject.Inject;
import javax.json.JsonException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.FileNotFoundException;
import java.net.URI;
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
        String message = "{\"Error when creating new student \" " + " }";
        try {
            studentService.createNewStudent(student);
            return Response.ok(student).build();
        } catch (Exception e) {

            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST)
                    .entity(message).type(MediaType.APPLICATION_JSON).build());
        }
    }

    @Path("updateemail/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        try {
            Student updatedStudentEmail = studentService.updateStudentEmail(id, email);
            return Response.ok(updatedStudentEmail).build();
        } catch (NullPointerException npe) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(exceptionStarter + id + " was not found " + npe).type(MediaType.APPLICATION_JSON).build());
        }
    }

    @Path("")
    @GET
    public Response getAllStudents() throws JsonException{
        List<Student> listOfStudents = studentService.getAllStudents();
        if (listOfStudents.isEmpty()) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity("There are no students in the database").type(MediaType.APPLICATION_JSON).build());
        }
        return Response.ok(listOfStudents).build();
    }

    // exception funkar ej
    @Path("{lastname}")
    @GET
    public Response getStudentByLastName(@PathParam("lastname") String lastName) {

        List<Student> foundStudent = studentService.getStudentByLastName(lastName);
        String message = "{\"Could not find student with lastname \": " + lastName + " }";


        if (foundStudent.size() == 0) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(message).type(MediaType.APPLICATION_JSON).build());
        } else {
            return Response.ok(foundStudent).build();
        }
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        try {
            studentService.deleteStudent(id);
            return Response.ok(exceptionStarter + id + " has been deleted").type(MediaType.TEXT_PLAIN_TYPE).build();
        } catch (Exception e) {
            throw new WebApplicationException(Response.status(Response.Status.NOT_FOUND)
                    .entity(exceptionStarter + id + " does not exist in the database " + e.getMessage())
                    .type(MediaType.APPLICATION_JSON).build());
        }
    }
}
