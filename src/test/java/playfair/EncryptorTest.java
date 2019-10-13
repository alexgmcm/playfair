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
    @DisplayName("Should encrypt bigram with letters on same row")
    void shouldEncryptBigramSameRow() {
        String bigram ="YF";
        char[] EXPECTED = {'F','P'};
        char[] ACTUAL = testEncryptor.encryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should encrypt bigram with letters on same col")
    void shouldEncryptBigramSameCol() {
        String bigram ="NU";
        char[] EXPECTED = {'U','L'};
        char[] ACTUAL = testEncryptor.encryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should encrypt bigram with via opposite corners")
    void shouldEncryptBigramOppCorners() {
        String bigram ="TH";
        char[] EXPECTED = {'Z','B'};
        char[] ACTUAL = testEncryptor.encryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should return encrypted plaintext")
    void shouldEncrypt() {
        String EXPECTED = "BM OD ZB XD NA BE KU DM UI XM MO UV IF";
        String ACTUAL = testEncryptor.encrypt();
        assertEquals(EXPECTED, ACTUAL);
    }
}
