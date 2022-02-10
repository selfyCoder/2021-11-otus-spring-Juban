package otus.tilegen.test.dao;

import otus.tilegen.test.domain.Option;

import java.util.List;

public interface OptionDao {

    List<Option> getTestOptions(int testId);


}
