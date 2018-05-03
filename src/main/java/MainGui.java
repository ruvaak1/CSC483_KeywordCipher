import java.util.Scanner;

/**
 * Created by Justin on 5/2/2018.
 */
public class MainGui {

    public static void main(String args[]) {
        //CLI Version:
        Scanner in = new Scanner(System.in);
        String input, text, keyword, keywordPos, crib;
        input = "";
        KeywordCipher keywordCipher = new KeywordCipher();

        while (!input.equals("q")) {
            printMenu();
            input = in.nextLine();

            if (input.equals("d")) {
                newLineWarning();
                System.out.println("Text to decipher:");
                text = in.nextLine();

                System.out.println("Keyword:");
                keyword = in.nextLine();

                System.out.println("Keyword Position:");
                keywordPos = in.nextLine();

                keywordCipher.decipher(text, keyword, keywordPos.charAt(0));
                System.out.println(keywordCipher.getPlainText());
            }
            else if (input.equals("e")) {
                newLineWarning();
                System.out.println("Text to encipher:");
                text = in.nextLine();

                System.out.println("Keyword:");
                keyword = in.nextLine();

                System.out.println("Keyword Position:");
                keywordPos = in.nextLine();

                keywordCipher.encipher(text, keyword, keywordPos.charAt(0));
                System.out.println(keywordCipher.getCipherText());
            }
            else if (input.equals("f")) {
                newLineWarning();
                System.out.println("Cipher Text:");
                text = in.nextLine();

                System.out.println("Crib:");
                crib = in.nextLine();

                System.out.println(keywordCipher.findCrib(text, crib));
            }
            else {
                System.out.println("Input not recognized");
            }
        }

        System.out.println("Quitting...");

        in.close();
    }

    private static void printMenu() {
        System.out.println("(f)ind crib, (d)ecipher, (e)ncipher, or (q)uit");
    }

    private static void newLineWarning() {
        System.out.println("Be sure to not use any new line (Enter Key) characters in cipher text and plain text");
    }
}
