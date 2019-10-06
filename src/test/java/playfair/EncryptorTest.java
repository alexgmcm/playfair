package playfair;

import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;


@DisplayName("Playfair Encryption tests")
class EncryptorTest {

    private Encryptor testEncryptor;



    @BeforeEach
    void beforeEach() {
        System.out.println("Create Encryptor object with test plaintext and key.");
        testEncryptor = new Encryptor("playfair example","Hide the gold in the tree stump");
    }


    @Test
    @DisplayName("Should generate correct key grid from key.")
    void shouldGenerateCorrectKeyGrid() {
        char[][] EXPECTED = {
                {'P', 'L', 'A', 'Y', 'F'},
                {'I', 'R', 'E', 'X', 'M'},
                {'B', 'C', 'D', 'G', 'H'},
                {'K', 'N', 'O', 'Q', 'S'},
                {'T', 'U', 'V', 'W', 'Z'}
        };
        char[][] ACTUAL = testEncryptor.generatekeyGrid();
        assertArrayEquals(EXPECTED, ACTUAL,
                () -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));

    }

    @Test
    @DisplayName("Should remove duplicate characters, keeping first occurrences.")
    void shouldRemoveDupeCharsKeepFirst() {
        String EXPECTED = "ALEX";
        String ACTUAL = testEncryptor.removeDuplicateChars("AALEEEXX",true);
        assertEquals(EXPECTED, ACTUAL);

    }


    @Test
    @DisplayName("Should remove duplicate characters.")
    void shouldRemoveDupeChars() {
        String EXPECTED = "L";
        String ACTUAL = testEncryptor.removeDuplicateChars("AALEEEXX",false);
        assertEquals(EXPECTED, ACTUAL);

    }

    @Test
    @DisplayName("Should clean key string.")
    void shouldCleanKeyString() {
        String EXPECTED = "PLAYFAIREXAMPLE";
        String ACTUAL = testEncryptor.cleanKeyString();
        assertEquals(EXPECTED, ACTUAL);

    }

    @Test
    @DisplayName("Should create correct bigram array.")
    void shouldCreateBigramArray() {
        String[] EXPECTED = {"HI","DE","TH","EG", "OL", "DI", "NT", "HE", "TR", "EX", "ES", "TU", "MP"};
        String[] ACTUAL = testEncryptor.plaintextToBigrams();
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));

    }
    @Test
    @DisplayName("Should pad final bigram.")
    void shouldPadFinalBigram() {
        testEncryptor.setPlaintext("Hide the gold in the tree stum");
        String[] EXPECTED = {"HI","DE","TH","EG", "OL", "DI", "NT", "HE", "TR", "EX", "ES", "TU", "MX"};
        String[] ACTUAL = testEncryptor.plaintextToBigrams();
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));

    }
}
