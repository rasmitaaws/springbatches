package net.petrikainulainen.spring.batch;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class SpringBatchStepListener  implements StepExecutionListener {

    @Value("${limit}")
    private String  limitValue;

    private SharedData sharedData;

    public SpringBatchStepListener(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println(limitValue+"limit value");
        sharedData.setCurrentchunkSize(sharedData.getCurrentchunkSize()+5);

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        System.out.println(limitValue+"after step");
        if(Long.parseLong(limitValue) == sharedData.getCurrentchunkSize()) {
            return new ExitStatus("FINISHED");
        } else {
            return new ExitStatus("CONTINUE");
        }


    }
}
