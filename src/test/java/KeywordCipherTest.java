import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
/**
 * Created by Justin on 3/28/2018.
 */

public class KeywordCipherTest {

    KeywordCipher sut;

    @Before
    public void init() {
        sut = new KeywordCipher();
    }

    @Test
    public void givenKeywordPosA_whenSettingKeywordPos_ThenKeywordPosIsZero() {
        int expected = 0;

        sut.setKeywordPos('A');
        assertEquals(expected, sut.getKeywordPos());
    }

    @Test
    public void givenKeywordPosa_whenSettingKeywordPos_ThenKeywordPosIsZero() {
        int expected = 0;

        sut.setKeywordPos('a');
        assertEquals(expected, sut.getKeywordPos());
    }

    @Test
    public void givenKeywordOfDogAtPosA_whenMakingKeyArray_ThenMakeCorrectKeyArray() {
        char[] expectedArray = new char[]{'D', 'O', 'G', 'A', 'B', 'C', 'E', 'F', 'H', 'I', 'J', 'K', 'L',
                                        'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        ArrayList<Character> expected = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            expected.add(expectedArray[i]);
        }

        sut.setKeyword("Dog");
        sut.setKeywordPos('A');

        sut.makeKeyArray();
        assertEquals(expected, sut.getKeyArray());
    }

    @Test
    public void givenKeywordOfDogAtPosM_whenMakingKeyArray_ThenMakeCorrectKeyArray() {
        char[] expectedArray = new char[]{'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'D',
                                        'O', 'G', 'A', 'B', 'C', 'E', 'F', 'H', 'I', 'J', 'K', 'L', 'M'};
        ArrayList<Character> expected = new ArrayList<Character>();
        for (int i = 0; i < 26; i++) {
            expected.add(expectedArray[i]);
        }

        sut.setKeyword("Dog");
        sut.setKeywordPos('M');

        sut.makeKeyArray();
        assertEquals(expected, sut.getKeyArray());
    }

    @Test
    public void givenKeywordOfDogAtPosM_whenEncipheringHelloWorld_ThenCipherTestIsVSZZGJGCZR() {
        String expected = "VSZZGJGCZR";

        sut.encipher("Hello World","dog",'m');
        assertEquals(expected, sut.getCipherText());
    }
}
