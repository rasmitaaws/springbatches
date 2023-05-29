package net.petrikainulainen.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.job.flow.support.SimpleFlow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import java.util.stream.Collectors;

@Configuration
public class SpringBatchExampleJobConfig implements EnvironmentAware {

    private static final String PROPERTY_REST_API_URL = "rest.api.url";
    List<String> learningIds = Arrays.asList("10001", "10002");

/*
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;
*/

 /*   @Autowired
    RestTemplate restTemplate;*/

    @Autowired
    private Environment environment;

    @Bean
    public SharedData sharedData() {
        return new SharedData();
    }

    @Bean
    @JobScope
    @StepScope
    public ItemReader<StudentDTO> itemReader(Environment environment, RestTemplate restTemplate,SharedData sharedData) {
        sharedData.setOffset(sharedData.getLimit());
        sharedData.setLimit(sharedData.getOffset()+1);
        return new RESTStudentReader(environment.getRequiredProperty(PROPERTY_REST_API_URL)+"?"+"offset="+sharedData.getOffset()+"&"+"limit="+sharedData.getLimit(), restTemplate,sharedData);
    }


    @JobScope
    @StepScope
    @Bean
    public ReaderListener readerListener(SharedData sharedData) {
        return new ReaderListener(sharedData);
    }

    @JobScope
    @StepScope
    @Bean
    public SpringBatchStepListener stepListener(SharedData sharedData) {
        return new SpringBatchStepListener(sharedData);
    }
@Bean
    public ItemWriter<StudentDTO> itemWriter() {
        return new LoggingItemWriter();
    }


   @Bean(name="exampleJobStep")
    public Step exampleJobStep(ItemReader<StudentDTO> reader,
                               ItemWriter<StudentDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {

        return stepBuilderFactory.get("exampleJobStep").listener(stepListener(sharedData()))
                .<StudentDTO, StudentDTO>chunk(5)
                .reader(reader)
                .listener(readerListener(sharedData()))
                .writer(writer)
                .build();
    }


    @Bean
    public Job exampleJob(Step exampleJobStep,
                          JobBuilderFactory jobBuilderFactory) {

        Flow flow =  new FlowBuilder<SimpleFlow>("myFlow").from(exampleJobStep).on("CONTINUE").to(exampleJobStep).on("FINISHED").end().build();
        return jobBuilderFactory.get("exampleJob").start(flow).on("COMPLETED").end().end().build();



                /*.start(exampleJobStep).
                on("CONTINUE").to(exampleJobStep).on("FINISHED")
                .end()
                .build();*/

    }

  /*  // helper method to create a step
    private Step createStep(String learningId) {
        {
            return stepBuilderFactory.get("exampleJobStep"+learningId)
                    .<StudentDTO, StudentDTO>chunk(1).reader(itemReader(environment,restTemplate,learningId))
                .writer(itemWriter())
            .build();
        }
    }
    // helper method to create a split flow out of a List of steps
    private static Flow createParallelFlow(List<Step> steps) {
        SimpleAsyncTaskExecutor taskExecutor = new SimpleAsyncTaskExecutor();
        taskExecutor.setConcurrencyLimit(steps.size());
        List<Flow> flows = steps.stream() // we have to convert the steps to a flows
                .map(step -> //
                        new FlowBuilder<Flow>("flow_" + step.getName()) //
                                .start(step) //
                                .build()) //
                .collect(Collectors.toList());

        return new FlowBuilder<SimpleFlow>("parallelStepsFlow").split(taskExecutor)
                .add(flows.toArray(new Flow[flows.size()])) //
                .build();
    }
*/
    @Override
    public void setEnvironment(Environment environment) {

    }
}
