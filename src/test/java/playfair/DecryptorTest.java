package playfair;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Playfair Decryption tests")
public class DecryptorTest {

    private Decryptor testDecryptor;

    @BeforeEach
    void beforeEach() {
        System.out.println("Create Decryptor object with test ciphertext and key.");
        testDecryptor = new Decryptor("playfair example","BM OD ZB XD NA BE KU DM UI XM MO UV IF");
    }

    @Test
    @DisplayName("Should decrypt bigram with letters on same row")
    void shouldDecryptBigramSameRow() {
        String bigram ="PL";
        char[] EXPECTED = {'F','P'};
        char[] ACTUAL = testDecryptor.decryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should decrypt bigram with letters on same col")
    void shouldDecryptBigramSameCol() {
        String bigram ="AE";
        char[] EXPECTED = {'V','A'};
        char[] ACTUAL = testDecryptor.decryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should decrypt bigram with via opposite corners")
    void shouldDecryptBigramOppCorners() {
        String bigram ="ZB";
        char[] EXPECTED = {'T','H'};
        char[] ACTUAL = testDecryptor.decryptBigram(bigram);
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", new String(ACTUAL)));
    }

    @Test
    @DisplayName("Should return decrypted ciphertext")
    void shouldEncrypt() {
        String EXPECTED = "HI DE TH EG OL DI NT HE TR EX ES TU MP";
        String ACTUAL = testDecryptor.decrypt();
        assertEquals(EXPECTED, ACTUAL);
    }
}
