package net.petrikainulainen.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This controller returns the dummy student data
 * that's read by our custom ItemReader.
 */
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(method = RequestMethod.GET)
    public List<StudentDTO> findStudents(@RequestParam int offset, @RequestParam int limit) {
        LOGGER.info("Finding all students");
        List<StudentDTO> students = createStudents(offset,limit);
        LOGGER.info("Found {} students", students.size());
        return students;
    }

    private List<StudentDTO> createStudents(int offset,int limit) {
        StudentDTO tony = new StudentDTO();
        tony.setEmailAddress("tony.tester@gmail.com");
        tony.setName("Tony Tester");
        tony.setPurchasedPackage("master");

        StudentDTO nick = new StudentDTO();
        nick.setEmailAddress("nick.newbie@gmail.com");
        nick.setName("Nick Newbie");
        nick.setPurchasedPackage("starter");

        StudentDTO ian = new StudentDTO();
        ian.setEmailAddress("ian.intermediate@gmail.com");
        ian.setName("Ian Intermediate");
        ian.setPurchasedPackage("intermediate");

        StudentDTO tony0 = new StudentDTO();
        tony0.setEmailAddress("tony.tester@gmail.com");
        tony0.setName("Tony Tester");
        tony0.setPurchasedPackage("master");

        StudentDTO nick1 = new StudentDTO();
        nick1.setEmailAddress("nick.newbie@gmail.com1");
        nick1.setName("Nick Newbie1");
        nick1.setPurchasedPackage("starter1");

        StudentDTO tony2 = new StudentDTO();
        tony2.setEmailAddress("tony.tester@gmail.com2");
        tony2.setName("Tony Tester2");
        tony2.setPurchasedPackage("master2");

        StudentDTO nick3 = new StudentDTO();
        nick3.setEmailAddress("nick.newbie@gmail.com3");
        nick3.setName("Nick Newbie3");
        nick3.setPurchasedPackage("starter3");

        StudentDTO nick4 = new StudentDTO();
        nick4.setEmailAddress("nick.newbie@gmail.com4");
        nick4.setName("Nick Newbie4");
        nick4.setPurchasedPackage("starter4");

        StudentDTO tony5 = new StudentDTO();
        tony5.setEmailAddress("tony.tester@gmail.com5");
        tony5.setName("Tony Tester5");
        tony5.setPurchasedPackage("master5");

        StudentDTO nick6 = new StudentDTO();
        nick6.setEmailAddress("nick.newbie@gmail.com6");
        nick6.setName("Nick Newbie6");
        nick6.setPurchasedPackage("starter6");
        StudentDTO nick7 = new StudentDTO();
        nick7.setEmailAddress("nick.newbie@gmail.com7");
        nick7.setName("Nick Newbie7");
        nick7.setPurchasedPackage("starter7");

        StudentDTO tony8 = new StudentDTO();
        tony8.setEmailAddress("tony.tester@gmail.com8");
        tony8.setName("Tony Tester8");
        tony8.setPurchasedPackage("master8");

        StudentDTO nick9 = new StudentDTO();
        nick9.setEmailAddress("nick.newbie@gmail.com9");
        nick9.setName("Nick Newbie9");
        nick9.setPurchasedPackage("starter9");
       return  Arrays.asList(tony, nick, ian,tony0, nick1, tony2, nick3, nick4,tony5, nick6, nick7,tony8,nick9).stream().limit(5).collect(Collectors.toList());
    }
}
