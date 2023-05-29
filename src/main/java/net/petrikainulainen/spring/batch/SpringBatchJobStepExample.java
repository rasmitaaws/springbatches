/*
package net.petrikainulainen.spring.batch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SpringBatchJobStepExample implements EnvironmentAware {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    private static final String PROPERTY_REST_API_URL = "rest.api.url";


    private List<Step> jobSteps(List<JobParameters> jobParams) {
        List<Step> jobSteps = new ArrayList<Step>();

        for (JobParameters jobParam : jobParams) {
            jobSteps.add(
                    jobStep(
                            new JobParametersBuilder()
                                    .addString("param1", jobParam.param1)
                                    .addString("param2", jobParam.param2))
            );
        }

        return jobSteps;
    }

    private Step jobStep(JobParameters jobParameters) {
        return stepBuilderFactory
                .get("step-{some-unique-name-to-identify-each-job}")
                .job(<Job which needs to be executed for different params>)
		.parametersExtractor(new JobParametersExtractor() {
            @Override
            public JobParameters getJobParameters(Job job, StepExecution stepExecution){
                return jobParameters;
            }
        })
                .build();
    }
    private Job superJob(List<Step> steps){
        FlowJobBuilder jobBuilder = jobBuilderFactory.get("superJob").flow("put a dummy start step here");

        FlowBuilder<FlowJobBuilder> builder = null;

        for(Step step: steps){
            if(builder == null){
                builder = jobBuilder.on("*").to(step);
            }
            else{
                builder = builder.on("*").to(step);
            }
        }

        return builder.end().build();
    }
    @Bean
    public DefaultJobParametersExtractor jobParametersExtractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"input.file"});
        return extractor;
    }

    @Override
    public void setEnvironment(Environment environment) {

    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment environment;


    @Bean
    public ItemReader<StudentDTO> itemReader(Environment environment, RestTemplate restTemplate, String learningId) {
        return new RESTStudentReader(environment.getRequiredProperty(PROPERTY_REST_API_URL), restTemplate,learningId);
    }


    @Bean
    public ItemWriter<StudentDTO> itemWriter() {
        return new LoggingItemWriter();
    }


    @Bean(name="exampleJobStep")
    public Step exampleJobStep(ItemReader<StudentDTO> reader,
                               ItemWriter<StudentDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<StudentDTO, StudentDTO>chunk(3)
                .reader(reader)
                .writer(writer)
                .build();
    }

}
*/
