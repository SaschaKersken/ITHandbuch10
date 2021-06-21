public class VowelCounter {
    private static final char VOWELS[] = {
        'a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'
    };

    public static boolean isVowel(char character) {
        // Annahme: kein Vokal
        boolean result = false;
        for (int i = 0; i < VOWELS.length; i++) {
            if (character == VOWELS[i]) {
                result = true;
                break;
            }
        }
        return result;
    }
        
    public static void main(String args[]) {
        // Anfangs 0 Vokale
        int vowels = 0;
        // Schleife über die Kommandozeilenargumente
        for (int i = 0; i < args.length; i++) {
            // Schleife über die Zeichen jedes Arguments
            for (int j = 0; j < args[i].length(); j++) {
                char character = args[i].charAt(j);
                if (isVowel(character)) {
                    vowels++;
                }
            }
        }
        System.out.println("Insgesamt " + vowels + " Vokal(e) gefunden.");
    }
}
                
