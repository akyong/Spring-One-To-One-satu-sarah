package com.bobbyakyong.InstructorApp;

import com.bobbyakyong.InstructorApp.dao.AppDao;
import com.bobbyakyong.InstructorApp.entity.Instructor;
import com.bobbyakyong.InstructorApp.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InstructorAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstructorAppApplication.class, args);
	}

    @Bean
    public CommandLineRunner commandLineRunner(AppDao theAppDao) {
        return runner -> {
            // create the instructor
            // Instructor tempInstructor = new Instructor("Bobby", "Akyong", "

            // createInstructor(theAppDao);

            // findInstructor(theAppDao);

            deleteInstuctor(theAppDao);
        };

    }

    public void createInstructor(AppDao theAppDao) {
//        Instructor instructor = new Instructor("Bobby", "Akyong", "bobbyakyong@gmail.com");
//
//        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/bobbyakyong", "Gaming");
//        instructor.setInstructorDetail(instructorDetail);

        Instructor instructor = new Instructor("Jeni", "Silvia", "jeni.yimei@gmail.com");

        InstructorDetail instructorDetail = new InstructorDetail("http://www.youtube.com/jeniyimei", "Nonton");
        instructor.setInstructorDetail(instructorDetail);



        System.out.println("Saved instructor: " + instructor);

        theAppDao.saveInstructor(instructor);
        // save the instructor
        // karena cascade ALL, maka instructorDetail juga akan tersave.
    }

    public void findInstructor(AppDao theAppDao) {
        int theId = 1;
        Instructor tempInstructor = theAppDao.findInstructorById(theId);

        System.out.println("Found instructor: " + tempInstructor);
        System.out.println("With instructor detail: " + tempInstructor.getInstructorDetail());
    }

    public void deleteInstuctor(AppDao theAppDao){
        int theId = 2;
        theAppDao.deleteInstructorById(theId);
        System.out.println("Deleted instructor id: " + theId);
    }
}
