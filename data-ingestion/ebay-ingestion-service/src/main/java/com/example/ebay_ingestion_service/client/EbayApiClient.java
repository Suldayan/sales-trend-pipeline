package com.example.ebay_ingestion_service.client;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class EbayApiClient {

    private final WebClient webClient;

    @Value("${ebay.api.app-id}")
    private String appId;

    public EbayApiClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.ebay.com/").build();
    }

    public String fetchData() {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/buy/browse/v1/item_summary/search")
                        .queryParam("q", "laptop")  // Example search query
                        .build())
                .header("Authorization", "Bearer " + getAccessToken())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    private String getAccessToken() {
        // Implement OAuth flow to get access token
        // This is a placeholder - you'll need to implement the actual OAuth flow
        return "your_access_token_here";
    }
}