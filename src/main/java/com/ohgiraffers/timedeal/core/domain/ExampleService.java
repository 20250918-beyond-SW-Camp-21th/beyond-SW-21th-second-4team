package com.ohgiraffers.timedeal.core.domain;

import com.ohgiraffers.timedeal.core.api.controller.v1.response.ExampleResponse;
import com.ohgiraffers.timedeal.core.enums.EntityStatus;
import com.ohgiraffers.timedeal.core.support.error.CoreException;
import com.ohgiraffers.timedeal.core.support.error.ErrorType;
import com.ohgiraffers.timedeal.storage.ExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExampleService {

    private final ExampleRepository exampleRepository;

    public ExampleService(ExampleRepository exampleRepository) {
        this.exampleRepository = exampleRepository;
    }

    public ExampleResponse getExamples() {
        List<Example> examples = exampleRepository.findAll();
        return new ExampleResponse(
                examples.stream()
                        .map(Example::getExample)
                        .toList()
        );
    }

    public void postExample(String example) {
        if(exampleRepository.existsByExampleAndStatus(example, EntityStatus.ACTIVE)) {
            throw new CoreException(ErrorType.DEFAULT_ERROR);
        }

        exampleRepository.save(new Example(example));
    }
}
