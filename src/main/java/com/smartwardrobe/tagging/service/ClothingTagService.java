package com.smartwardrobe.tagging.service;

import com.smartwardrobe.tagging.dto.TaggingResponseDTO;
import com.smartwardrobe.tagging.model.ClothingItem;
import com.smartwardrobe.tagging.repository.ClothingItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ClothingTagService {

    private final VisionLLMClientService visionLLMClientService;
    private final ClothingItemRepository clothingItemRepository;

    public ClothingTagService(VisionLLMClientService visionLLMClientService, ClothingItemRepository clothingItemRepository) {
        this.visionLLMClientService = visionLLMClientService;
        this.clothingItemRepository = clothingItemRepository;
    }

    public ClothingItem processAndSaveImage(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File cannot be empty");
        }

        // 1. Call Vision LLM
        TaggingResponseDTO metadata = visionLLMClientService.extractAttributes(file);

        if (metadata == null) {
            throw new RuntimeException("Vision LLM returned empty metadata");
        }

        // 2. Save to database
        ClothingItem item = new ClothingItem();
        item.setColor(metadata.getColor());
        item.setFabric(metadata.getFabric());
        item.setPattern(metadata.getPattern());
        item.setSuitableOccasions(metadata.getSuitableOccasions());
        item.setImageUrl("mock-url-or-s3-path/" + file.getOriginalFilename());

        return clothingItemRepository.save(item);
    }
}
