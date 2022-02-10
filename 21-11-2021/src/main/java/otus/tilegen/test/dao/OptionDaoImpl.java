package otus.tilegen.test.dao;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import otus.tilegen.test.domain.Option;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class OptionDaoImpl implements OptionDao {

    private List<Option> options;


    public OptionDaoImpl(String optionsCsvFileName) {
        init(optionsCsvFileName);
    }

    @Override
    public List<Option> getTestOptions(int questionId) {
        return options.stream().filter(o -> o.getIdQuestion() == questionId).collect(Collectors.toList());
    }

    private void init(String optionsCsvFileName) {
        Resource resource = new ClassPathResource(optionsCsvFileName);
        options = new ArrayList<>();
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
            throw new IOException("options куфвшта failed");
        }
        return option;
    }
}
