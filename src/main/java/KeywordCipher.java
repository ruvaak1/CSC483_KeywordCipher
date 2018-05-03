import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Justin on 3/28/2018.
 */
public class KeywordCipher {

    private String plainText, cipherText, keyword;
    private int keywordPos = 0;
    private char[] lowerCaseArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private char[] baseKeyArray = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                                    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    //private char[] keyArray = new char[26];
    ArrayList<Character> keyArray = new ArrayList<Character>(Arrays.asList(new Character[26]));

    public void setPlainText(String plainText) {
        this.plainText = plainText.toLowerCase().replaceAll("[^A-Za-z]", "");
    }

    public String getPlainText() {
        return this.plainText;
    }

    public void setCipherText(String cipherText) {
        this.cipherText = cipherText.toUpperCase().replaceAll("[^A-Za-z]", "");
    }

    public String getCipherText() {
        return cipherText;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword.toUpperCase();
    }

    public String getKeyword() {
        return this.keyword;
    }

    public ArrayList<Character> getKeyArray() {
        return keyArray;
    }

    public void setKeywordPos(char keywordPos) {
        if (Character.isLetter(keywordPos)) {
            keywordPos = Character.toUpperCase(keywordPos);
            this.keywordPos = ((int) keywordPos)-65;
        }
    }

    public void setKeywordPos(int keywordPos) {
        this.keywordPos = keywordPos%26;
    }

    public int getKeywordPos() {
        return this.keywordPos;
    }

    public void makeKeyArray() {
        int keyArrayIndex, keywordLength;
        keyArrayIndex = keywordPos;
        keywordLength = keyword.length();

        for (int i = 0; i < keywordLength; i++)
        {
            char currentChar = keyword.charAt(i);
            if (!keyArray.contains(currentChar)) {
                keyArray.set(keyArrayIndex, currentChar);
                keyArrayIndex = (keyArrayIndex+1)%26;
            }
        }

        for (int i = 0; i < 26; i++) {
            char currentChar = baseKeyArray[i];
            if (!keyArray.contains(currentChar)) {
                keyArray.set(keyArrayIndex, currentChar);
                keyArrayIndex = (keyArrayIndex+1)%26;
            }
        }
    }

    public void encipher(String plainText, String keyword, char keywordPos) {
        setPlainText(plainText);
        setKeyword(keyword);
        setKeywordPos(keywordPos);
        makeKeyArray();

        this.cipherText = this.plainText;
        for (int i = 0; i < 26; i++) {
            this.cipherText = this.cipherText.replace(lowerCaseArray[i], keyArray.get(i));
        }
    }

    public void decipher(String cipherText, String keyword, char keywordPos) {
        setCipherText(cipherText);
        setKeyword(keyword);
        setKeywordPos(keywordPos);
        makeKeyArray();

        this.plainText = this.cipherText;
        for (int i = 0; i < 26; i++) {
            this.plainText = this.plainText.replace(keyArray.get(i), lowerCaseArray[i]);
        }
    }

    public String findCrib(String cipherText, String crib) {
        setCipherText(cipherText);
        String output = "";
        int letterCount = 0;
        int cribLength = crib.length();
        int loopMax = this.cipherText.length() - cribLength;
        int[] cribAsNumbers = new int[cribLength];
        int[] compareNumbers = new int[cribLength];
        ArrayList<Character> repeatLetters = new ArrayList<Character>();

        for (int i = 0; i < cribLength; i++) {
            char currChar = crib.charAt(i);

            if (!repeatLetters.contains(currChar)) {
                repeatLetters.add(currChar);
                cribAsNumbers[i] = letterCount;
                letterCount += 1;
            }
            else {
                cribAsNumbers[i] = cribAsNumbers[repeatLetters.indexOf(currChar)];
            }
        }

        for (int i = 0; i <= loopMax; i++) {
            letterCount = 0;
            repeatLetters = new ArrayList<Character>();
            boolean possiblePos = true;

            for (int j = 0; j < cribLength; j++) {
                char currChar = this.cipherText.charAt(i + j);

                if (!repeatLetters.contains(currChar)) {
                    repeatLetters.add(currChar);
                    compareNumbers[j] = letterCount;
                    letterCount += 1;
                }
                else {
                    compareNumbers[j] = compareNumbers[repeatLetters.indexOf(currChar)];
                }

                if (compareNumbers[j] != cribAsNumbers[j]) {
                    possiblePos = false;
                    j = cribLength;
                }
            }

            if (possiblePos) {
                output += (i + 1) + " - " + this.cipherText.substring(i, i + cribLength) + "\n";
            }
        }

        return output;
    }
}
