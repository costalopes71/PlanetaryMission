package br.com.elo7.planetarymission.model.test;

import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class DescribedSuiteRunner extends Suite {

	public DescribedSuiteRunner(Class<?> c, RunnerBuilder builder) throws InitializationError {
        super(c, builder);
    }

    @Override
    protected String getName() {
        return getTestClass()
                .getJavaClass()
                .getAnnotation(SuiteDescription.class)
                .value();
    }
}
