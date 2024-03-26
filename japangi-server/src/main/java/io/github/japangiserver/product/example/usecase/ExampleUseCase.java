package io.github.japangiserver.product.example.usecase;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.japangiserver.base.dto.response.SocketResponse;
import io.github.japangiserver.base.usecase.BaseUseCase;
import io.github.japangiserver.product.example.dto.request.ExampleRequest;
import org.springframework.stereotype.Component;

@Component
public class ExampleUseCase extends BaseUseCase<ExampleRequest> {

    public ExampleUseCase(ObjectMapper objectMapper) {
        super(objectMapper);
    }

    @Override
    public SocketResponse core(ExampleRequest data) {
        return SocketResponse.success(data.name());
    }

    @Override
    public Class<ExampleRequest> support() {
        return ExampleRequest.class;
    }
}
