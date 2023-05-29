package net.petrikainulainen.spring.batch;

import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;


public class ReaderListener implements ItemReadListener<StudentDTO> {

    private SharedData sharedData;

    public ReaderListener(SharedData sharedData) {
        this.sharedData = sharedData;
    }
    @Override
    public void beforeRead() {

    }
    @Override
    public void afterRead(StudentDTO studentDTO) {
        sharedData.setOffset(sharedData.getLimit());

        System.out.println(sharedData.getOffset()+"*** After Read");
    }

    @Override
    public void onReadError(Exception e) {

    }
}
