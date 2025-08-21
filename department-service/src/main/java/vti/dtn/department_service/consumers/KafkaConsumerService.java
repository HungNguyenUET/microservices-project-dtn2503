package vti.dtn.department_service.consumers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumerService {

    @KafkaListener(topics = "test-topic", groupId = "department-group")
    public void consumeMessage(String message) {
        log.info("Received message: {}", message);
    }
}
