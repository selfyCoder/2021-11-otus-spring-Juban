package otus.tilegen.test.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import otus.tilegen.test.domain.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:config.properties")
public class OptionDaoImpl implements OptionDao {

    private String optionsCsvFileName;


    public OptionDaoImpl(@Value("${option.file.name}") String optionsCsvFileName) {
        this.optionsCsvFileName = optionsCsvFileName;
    }

    @Override
    public List<Option> getTestOptions(int questionId) {
        return getOptions().stream().filter(o -> o.getIdQuestion() == questionId).collect(Collectors.toList());
    }

    private List<Option> getOptions() {
        Resource resource = new ClassPathResource(optionsCsvFileName);
        List<Option> options = new ArrayList<>();
        try (Scanner scanner = new Scanner(resource.getFile())) {
            if(scanner.hasNext()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                options.add(getOptionFromLine(scanner.nextLine()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return options;
    }

    private Option getOptionFromLine(String line) throws IOException {
        Option option = new Option();
        String[] values = line.split(";");
        if(values.length == 3) {
            option.setOptionText(values[0]);
            option.setIdQuestion(Integer.parseInt(values[1]));
            option.setRightAnswer(Integer.parseInt(values[2]) == 1);
        }
        else {
            throw new IOException("options reading failed");
        }
        return option;
    }
}
