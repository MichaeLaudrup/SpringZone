package es.michael.spring.cap03.lab01;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class RESTFulServer {

	private static final String __ControllerRelativePath = "students";
	
	@Autowired
	StudentRepository jpaRepo; 
	
	@RequestMapping(value="/")
	public String sayHelloWorld() {
		return "[" + LocalDateTime.now() + "]" 
				+ HTMLEntities.__NonBreakingSpace
				+ HTMLEntities.__NonBreakingSpace
				+ HTMLEntities.__NonBreakingSpace
				+ " RESTFul Server. The application is currently running...";
	}
	
	@RequestMapping(value="/" + __ControllerRelativePath ) 
	@CrossOrigin(origins= {"http://localhost:9090","http://localhost:9090"})
    public ResponseEntity<Object> get() { 
        return new ResponseEntity<Object>(jpaRepo.findAll(), HttpStatus.OK);
    }
	
	@RequestMapping(value="/" + __ControllerRelativePath +"/{id}") 
	@CrossOrigin(origins="http://localhost:9090")
    public ResponseEntity<Object> get( @PathVariable("id") String id) { 
		Integer idNumber = Integer.parseInt(id); 
		StudentJPA estudianteSolicitado = jpaRepo.findById(idNumber).get(); 
		if(estudianteSolicitado != null) {
			return new ResponseEntity<Object>(estudianteSolicitado, HttpStatus.OK);
		}else{
			return new ResponseEntity<Object>("No se ha encontrado ningun estudiante con el id: " +id, HttpStatus.NOT_FOUND);
		}   
    }
	
	@RequestMapping(value="/" + __ControllerRelativePath, method=RequestMethod.POST)
    public ResponseEntity<Object> create(@RequestBody StudentJPA student) {
		jpaRepo.save(student);
        return new ResponseEntity<Object>(student, HttpStatus.CREATED);
    }
    
    @RequestMapping(value="/" + __ControllerRelativePath + "/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody StudentJPA student) {
    	Integer numberID = Integer.parseInt(id); 
        StudentJPA modifiedStudent = jpaRepo.findById(numberID).get();
        modifiedStudent.setName(student.getName());
        modifiedStudent.setEmail(student.getEmail());
        jpaRepo.save(modifiedStudent); 
    	return new ResponseEntity<Object>(modifiedStudent, HttpStatus.OK);
       
	}
    
	@RequestMapping(value="/" + __ControllerRelativePath + "/{id}", method=RequestMethod.DELETE)
	 public ResponseEntity<Object> delete(@PathVariable("id") String id) {
		 Integer idNumber = Integer.parseInt(id); 
		 StudentJPA deletedStudent = jpaRepo.findById(idNumber).get(); 
		 jpaRepo.deleteById(idNumber);
	     return new ResponseEntity<>(deletedStudent, HttpStatus.OK);
	 } 
}
