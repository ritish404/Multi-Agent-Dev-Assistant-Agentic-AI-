package com.smartwardrobe.tagging.service;

import com.smartwardrobe.tagging.dto.TaggingResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

@Service
public class VisionLLMClientService {

    private final WebClient webClient;

    @Value("${vision-llm.api-key}")
    private String apiKey;

    public VisionLLMClientService(WebClient.Builder webClientBuilder, @Value("${vision-llm.api-url}") String apiUrl) {
        this.webClient = webClientBuilder.baseUrl(apiUrl).build();
    }

    public TaggingResponseDTO extractAttributes(MultipartFile file) {
        try {
            MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
            body.add("image", new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            });

            return webClient.post()
                    .header("Authorization", "Bearer " + apiKey)
                    .contentType(MediaType.MULTIPART_FORM_DATA)
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(TaggingResponseDTO.class)
                    .block();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Vision LLM API failed: " + e.getStatusCode() + " " + e.getResponseBodyAsString(), e);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read the file for Vision LLM API", e);
        } catch (Exception e) {
            throw new RuntimeException("Vision LLM API error or unparseable response: " + e.getMessage(), e);
        }
    }
}
