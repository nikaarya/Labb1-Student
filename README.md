# Labb1-Student

ENDPOINTS:

createStudent POST: http://localhost:8080/student-management-system/api/v1/students/

updateEmail PATCH: http://localhost:8080/student-management-system/api/v1/students/updateemail/{id}

getAllStudents GET: http://localhost:8080/student-management-system/api/v1/students/

getStudentByLastName GET: http://localhost:8080/student-management-system/api/v1/students/{lastname}

deleteStudent DELETE: http://localhost:8080/student-management-system/api/v1/students/{id}

UTSEENDE AV JSON-BODIES:

CreateStudent:
BODY (JSON):
 {
	"firstName": "Nika",
	"lastName": "Arya",
	"email": "nika@gmail.se",
	"phoneNumber": "0709875432"
}
QUERY: Tomt

updateEmail: 
BODY: Tomt
QUERY: name: email, value: anna.andersson@gmail.com

getAllStudents: 
BODY: Tomt
QUERY: Tomt

getStudentByLastName:
BODY: Tomt
QUERY: Tomt

deleteStudent:
BODY: Tomt
QUERY: Tomt

PROBLEM SOM JAG STÖTTE PÅ:
Hade svårt med att få mina exceptions att visas i JSON-format och är osäker på om mitt tillvägagångssätt till att lösa detta är rätt och framför allt om det är ett optimalt sätt att lösa det på.

Jag fick heller inte till getStudentByLastName med en @QueryParam så jag löste det på annat sätt



