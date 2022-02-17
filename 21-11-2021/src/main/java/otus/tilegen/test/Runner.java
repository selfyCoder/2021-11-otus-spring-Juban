package otus.tilegen.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import otus.tilegen.test.service.TestService;

@Configuration
@ComponentScan
public class Runner {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Runner.class);
        TestService testService = context.getBean(TestService.class);
        testService.quiz();
    }
}
