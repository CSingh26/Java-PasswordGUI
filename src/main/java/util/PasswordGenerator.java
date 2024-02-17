package main.java.util;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PasswordGenerator {

    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGIT = "1234567890";
    private static final String SPECIAL = "!@#$%^&*()_-+={}[]?/><.,~`";

    private final boolean useUpper;
    private final boolean useLower;
    private final boolean useDigits;
    private final boolean useSpecial;

    private final SecureRandom randomPwd = new SecureRandom();

    public PasswordGenerator(boolean useUpper, boolean useLower, boolean useDigits, boolean useSpecial) {
        this.useUpper = useUpper;
        this.useLower = useLower;
        this.useDigits = useDigits;
        this.useSpecial = useSpecial;
    }

    public String generate(int length) {
        StringBuilder password = new StringBuilder(length);
        List<String> charCat = new ArrayList<>(4);

        if (useUpper) {
            charCat.add(UPPERCASE);
        }

        if (useLower) {
            charCat.add(LOWERCASE);
        }

        if (useDigits) {
            charCat.add(DIGIT);
        }

        if (useSpecial) {
            charCat.add(SPECIAL);
        }
        
        for (String cat : charCat) {
            password.append(cat.charAt(randomPwd.nextInt(cat.length())));
        }

        for (int i = password.length(); i < length; i++) {
            String cat = charCat.get(randomPwd.nextInt(charCat.size()));
            password.append(cat.charAt(randomPwd.nextInt(cat.length())));
        }

        List<Character> passwordChar = new ArrayList<>(password.length());
        for (int i = 0; i < password.length(); i++) {
            passwordChar.add(password.charAt(i));
        }

        Collections.shuffle(passwordChar);

        StringBuilder finalPwd = new StringBuilder(length);
        for (char c : passwordChar) {
            finalPwd.append(c);
        }

        return finalPwd.toString();
    }

}