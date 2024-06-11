package io.github.japangirelay.controller;

import io.github.japangirelay.controller.dto.RelayRequest;
import io.github.japangirelay.relay.RelayManager;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relay")
public class RelayController {

    private final RelayManager relayManager;

    public RelayController(RelayManager relayManager) {
        this.relayManager = relayManager;
    }

    @PostMapping
    public ResponseEntity<String> relay(@RequestBody RelayRequest request) {
        return ResponseEntity.ok(relayManager.relay(request));
    }
}
