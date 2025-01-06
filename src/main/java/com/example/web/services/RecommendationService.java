package com.example.web.services;

import com.example.web.models.Decor;
import com.example.web.models.Formality;
import com.example.web.models.Mood;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public String generateRecommendation(Formality formality, Decor decor, Mood mood) {
        String baseTheme = switch (formality) {
            case CASUAL -> "Rustic and Playful";
            case SEMI_FORMAL -> "Elegant and Refined";
            case FORMAL -> "Luxurious and Grand";
        };

        String decorDetail = switch (decor) {
            case FLOWERS -> "with fresh floral accents.";
            case CANDLES -> "with warm candlelight.";
            case LIGHTS -> "with dynamic lighting.";
            case DRAPES -> "with elegant drapery.";
        };

        String moodEffect = switch (mood) {
            case RELAXED -> "Neutral tones and soft textures create a calm ambiance.";
            case ELEGANT -> "Sophisticated details complete the look.";
            case FUN -> "Bright colors energize the setup.";
            case INSPIRED -> "Unique layouts spark imagination.";
        };

        return baseTheme + " " + decorDetail + " " + moodEffect;
    }
}
