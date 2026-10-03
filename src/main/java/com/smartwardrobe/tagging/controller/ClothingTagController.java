package com.smartwardrobe.tagging.controller;

import com.smartwardrobe.tagging.model.ClothingItem;
import com.smartwardrobe.tagging.service.ClothingTagService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/clothing")
public class ClothingTagController {

    private final ClothingTagService clothingTagService;

    public ClothingTagController(ClothingTagService clothingTagService) {
        this.clothingTagService = clothingTagService;
    }

    @PostMapping("/tag")
    public ResponseEntity<ClothingItem> uploadAndTag(@RequestParam("image") MultipartFile image) {
        ClothingItem processedItem = clothingTagService.processAndSaveImage(image);
        return ResponseEntity.ok(processedItem);
    }
}
