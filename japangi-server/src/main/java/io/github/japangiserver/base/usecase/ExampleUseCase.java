package io.github.japangiserver.base.usecase;

import io.github.japangiserver.base.dto.request.ExampleRequest;
import io.github.japangiserver.base.dto.response.SocketResponse;
import org.springframework.stereotype.Component;

@Component
public class ExampleUseCase extends BaseUseCase<ExampleRequest> {

    @Override
    SocketResponse core(ExampleRequest data) {
        return SocketResponse.success(data.name());
    }
}
