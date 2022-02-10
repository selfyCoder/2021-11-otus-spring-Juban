package otus.tilegen.test;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.tilegen.test.service.TestService;

public class Runner {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        TestService testService = context.getBean(TestService.class);
        testService.printTest();
    }
}
