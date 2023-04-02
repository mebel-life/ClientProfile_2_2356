package org.client.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.Random;

@AllArgsConstructor
@Component
@Data
public class Generator {

    // Генератор случаного кода из 12 символов в диапозоне, прописанном в CODE_PATTERN -->
    public static final String CODE_PATTERN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static String generateString() {
        String characters = CODE_PATTERN;

        Random rnd = new Random();
        char[] text = new char[12];
        for (int i = 0; i < 12; i++) {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        String result = new String(text);

        return new String(text);
    }

}
