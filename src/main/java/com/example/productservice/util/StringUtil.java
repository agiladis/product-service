package com.example.productservice.util;

import java.text.Normalizer;
import java.util.Locale;
import java.util.Random;

public class StringUtil {

    private static String randomAlphanumeric(int length) {
        final Random RANDOM = new Random();

        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public static String toSlug(String input) {
        // Normalisasi teks untuk menghapus diakritik dan buat jadi lowercase
        String slug = Normalizer.normalize(input.trim(), Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase(Locale.ENGLISH);

        slug = slug.replaceAll("[^a-z0-9\\s-]", "")
                .replaceAll("[\\s_]+", "-")      // Ganti spasi/underscore dengan -
                .replaceAll("-+", "-")           // Ganti multiple - dengan single
                .replaceAll("^-|-$", "");        // Hapus - di awal/akhir

        return slug;
    }

    public static String toUpper(String input) {
        return input != null ? input.toUpperCase() : null;
    }

    public static String toLower(String input) {
        return input != null ? input.toLowerCase() : null;
    }

    public static String generateSlugWithUnique(String input) {
        String base = toSlug(input);
        String randomCode = randomAlphanumeric(6);

        return base + "-" + randomCode;
    }
}
