package se.iths.rest;


import se.iths.StudentBadRequestException;
import se.iths.StudentNotFoundException;
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

    @Inject
    StudentService studentService;

    @Path("")
    @POST
    public Response createStudent(Student student) {
        String message = "{\"Error when creating new student\"" + " }";
        try {
            studentService.createNewStudent(student);
            return Response.ok(student).build();
        } catch (Exception e) {
            throw new StudentBadRequestException(message);
        }
    }

    @Path("updateemail/{id}")
    @PATCH
    public Response updateEmail(@PathParam("id") Long id, @QueryParam("email") String email) {
        String message = "{\"Could not find student with ID \": " + id +" }";
        try {
            Student updatedStudentEmail = studentService.updateStudentEmail(id, email);
            return Response.ok(updatedStudentEmail).build();
        } catch (NullPointerException npe) {
            throw new StudentNotFoundException(message);
        }
    }

    @Path("")
    @GET
    public Response getAllStudents() throws StudentNotFoundException {
        List<Student> listOfStudents = studentService.getAllStudents();
        String message = "{\"There are no students in the database \"" +" }";
        if (listOfStudents.isEmpty()) {
            throw new StudentNotFoundException(message);
        }
        return Response.ok(listOfStudents).build();
    }

    @Path("{lastname}")
    @GET
    public Response getStudentByLastName(@PathParam("lastname") String lastName) {

        List<Student> foundStudent = studentService.getStudentByLastName(lastName);
        String message = "{\"Could not find student with lastname \": " + lastName + " }";
        if (foundStudent.isEmpty()) {
            throw new StudentNotFoundException(message);
        } else {
            return Response.ok(foundStudent).build();
        }
    }

    @Path("{id}")
    @DELETE
    public Response deleteStudent(@PathParam("id") Long id) {
        String message = "{\"No student in the database with ID \": " + id + " " + " }";

        try {
            studentService.deleteStudent(id);
            return Response.ok("Student with ID: " + id + " has been deleted").type(MediaType.TEXT_PLAIN).build();
        } catch (Exception e) {
            throw new StudentNotFoundException(message);
        }
    }
}
