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
            case FLOWERS -> generateFlowerRecommendation(mood);
            case CANDLES -> generateCandleRecommendation(mood);
            case LIGHTS -> generateLightRecommendation(mood);
            case DRAPES -> generateDrapeRecommendation(mood);
        };

        String moodEffect = switch (mood) {
            case RELAXED -> "Neutral tones and soft textures create a calm ambiance.";
            case ELEGANT -> "Sophisticated details complete the look.";
            case FUN -> "Bright colors energize the setup.";
            case INSPIRED -> "Unique layouts spark imagination.";
        };

        String themeName = generateThemeName(formality, decor, mood);

        return "Theme: " + themeName + "\n" + baseTheme + " event." + " " + decorDetail + " " + moodEffect;
    }

    // Method to generate the name of the theme based on formality, decor, and mood
    private String generateThemeName(Formality formality, Decor decor, Mood mood) {
        String themeName = "";

        // Creating a memorable and catchy theme name
        themeName = switch (formality) {
            case CASUAL -> "Casual Charm";
            case SEMI_FORMAL -> "Semi-Formal Elegance";
            case FORMAL -> "Grand Opulence";
        };

        // Add decor type to theme name
        themeName += " with " + switch (decor) {
            case FLOWERS -> "Floral Whispers";
            case CANDLES -> "Candlelit Serenity";
            case LIGHTS -> "Radiant Glow";
            case DRAPES -> "Elegant Drapes";
        };

        // Add mood descriptor to theme name
        themeName += " for a " + switch (mood) {
            case RELAXED -> "Relaxed,";
            case ELEGANT -> "Sophisticated,";
            case FUN -> "Fun,";
            case INSPIRED -> "Creative,";
        };

        return themeName;
    }

    // Method to generate flower recommendations based on mood
    private String generateFlowerRecommendation(Mood mood) {
        return switch (mood) {
            case RELAXED -> "Think of a laid-back garden party, where fresh wildflowers like daisies and lavender are thoughtfully arranged in mason jars, scattered across rustic wooden tables. The flowers spill over in natural, unstructured arrangements, capturing the effortless charm of a countryside gathering.";
            case ELEGANT -> "Create a refined atmosphere with elegant floral arrangements, featuring soft blush roses, ivory peonies, and creamy white lilies, arranged in delicate glass vases. A lace table runner complements the romantic vibe, while the flowers’ subtle tones blend seamlessly with the soft, muted lighting.";
            case FUN -> "Bright and playful wildflowers in mismatched, colorful vases bring a cheerful energy to the space. Vibrant yellow sunflowers, orange marigolds, and pops of pink gerbera daisies are paired with playful, colorful ribbons or twine wrapped around the vases.";
            case INSPIRED -> "Transform the space into a whimsical floral wonderland with cascading floral chandeliers overhead, bursting with rich colors like deep purples, fiery reds, and sunny yellows. Floral hoops, arches, or suspended blooms give the illusion of walking through a dreamy floral paradise.";
        };
    }

    // Method to generate candle recommendations based on mood
    private String generateCandleRecommendation(Mood mood) {
        return switch (mood) {
            case RELAXED -> "Soft, flickering lanterns with warm candles line the pathways, guiding guests through a tranquil, evening garden. Simple wooden tables adorned with linen tablecloths host single candles in rustic jars, creating pools of soft, golden light.";
            case ELEGANT -> "Tall, slender candle holders made of fine crystal or gold host soft, flickering candles that cast a warm glow across the room. The candles are arranged in clusters on long, luxurious satin table runners, with delicate greenery accents winding around the holders.";
            case FUN -> "Add a playful twist with vibrant candles housed in colorful holders—bright reds, turquoise blues, and neon pinks add a joyful and fun energy to the space. Mismatched candle holders and creative lighting effects will provide a quirky and festive touch.";
            case INSPIRED -> "Create artistic candle setups with varying heights, creative holders, and intricate details. Think of candles inside glass jars with unique engravings, or floating candles in water-filled containers for a surreal and imaginative look.";
        };
    }

    // Method to generate lighting recommendations based on mood
    private String generateLightRecommendation(Mood mood) {
        return switch (mood) {
            case RELAXED -> "Think string lights over picnic tables, fairy lights wrapped around trees, and cozy seating nooks. Casual yet magical.";
            case ELEGANT -> "Chandeliers paired with soft uplighting, creating a warm and sophisticated ambiance. Delicate and timeless.";
            case FUN -> "Bright neon lights, string lights, and playful disco-style lighting effects. The energy is lively and fun, perfect for dancing and socializing.";
            case INSPIRED -> "Unique lighting installations like hanging lanterns, glowing spheres, or projection mapping create an avant-garde atmosphere.";
        };
    }

    // Method to generate drape recommendations based on mood
    private String generateDrapeRecommendation(Mood mood) {
        return switch (mood) {
            case RELAXED -> "Light, flowing drapes in earthy tones paired with wooden furniture and soft pillows. The vibe is casual yet stylish.";
            case ELEGANT -> "Sheer white drapes with small floral details create a serene, timeless setup. Perfect for a cozy yet chic gathering.";
            case FUN -> "Bright drapery in bold colors, combined with vibrant patterns and textures. A cheerful, festive atmosphere.";
            case INSPIRED -> "Layered drapes in earthy hues, paired with eclectic details like handwoven textiles and unique decor pieces.";
        };
    }
}
