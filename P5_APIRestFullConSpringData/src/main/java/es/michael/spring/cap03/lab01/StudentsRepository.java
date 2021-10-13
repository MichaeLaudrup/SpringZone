package es.michael.spring.cap03.lab01;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;




public class StudentsRepository {
	

	private static Map<String, Student> repo = new HashMap<>();
	private static int nextIndex; 
    static {
        Student student;
        student = new Student();
        student.setId("1");
        student.setName("Bethanie G. Addams");   
        student.setEmail("addams@gmail.com");
        repo.put(student.getId(), student);
    
        student = new Student();
        student.setId("2");
        student.setName("John F. McReally");        
        repo.put(student.getId(), student);

        student = new Student();
        student.setId("3");
        student.setName("Claudia M. Kirshner");       
        repo.put(student.getId(), student);

        student = new Student();
        student.setId("4");
        student.setName("Nelson B. Ransom");
        repo.put(student.getId(), student);

        student = new Student();
        student.setId("5");
        student.setName("Roxane L. Russell");
        repo.put(student.getId(), student);
        nextIndex = 6; 
    }

    /*public Student findByEmail(String email)
    {
        if (email != null &&
            email.length() > 0)
        {
            for(Student student : repo.values())
            {
                if (student.getEmail() != null &&
                    student.getEmail().equals(email))
                    return student;
            }
        }
        return null;
    }*/
    
    public static Student addNewStudent(Student estudiante) {
    	estudiante.setId(getNewID());
    	repo.put(estudiante.getId(), estudiante); 
    	return estudiante; 
    }
    
    public static Collection<Student> getValues() {
    	return repo.values(); 
    }

	public static Student getStudent(String id) {
		return repo.get(id);
	}
	
	public static Student deleteStudent(String id) {
		Student studentEliminated = repo.remove(id); 
		return studentEliminated; 
	}
	
	private static String getNewID() {
		String newID = nextIndex+""; 
		nextIndex++; 
		return newID; 
	}
	
	/*
	 * Metodo que actualiza un estudiante existente
	 * @param String id - identificador estudiante
	 * @param Student student - Estudiante con nuevos datos
	 */
	public static Student update(String id, Student student) {
		if(repo.containsKey(id)) {
			Student studentModified = repo.get(id); 
			studentModified.setName(student.getName());
			return studentModified; 
		}else {
			return null; 
		}
		
	}
	
	
}
