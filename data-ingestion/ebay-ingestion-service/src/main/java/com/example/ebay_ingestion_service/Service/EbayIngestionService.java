package com.example.ebay_ingestion_service.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EbayIngestionService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private EbayApiClient ebayApiClient;

    @Scheduled(fixedRate = 3600000) // Run every hour
    public void fetchAndPublishEbayData() {
        try {
            String ebayData = ebayApiClient.fetchData();
            kafkaTemplate.send("ebay-raw-data", ebayData);
        } catch (Exception ignored) {

        }
    }
}