package com.example.einvoicing.controllers;

import com.example.einvoicing.helpers.TranslationHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class TranslationController {
    @Autowired
    private TranslationHelper translationService;

    @PostMapping("/translate")
    public ResponseEntity<String> translate(@RequestParam("text") String text, @RequestParam String sourceLang, @RequestParam String targetLang) {
        String translatedText = translationService.getTranslation(text);
        return ResponseEntity.ok(translatedText);
    }

}

