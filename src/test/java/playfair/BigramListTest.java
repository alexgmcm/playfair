package playfair;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@DisplayName("Playfair BigramList tests")
public class BigramListTest {


    @Test
    @DisplayName("Should create correct bigram array.")
    void shouldCreateBigramArray() {
        BigramList testBigramList = new BigramList("Hide the gold in the tree stump");
        String[] EXPECTED = {"HI","DE","TH","EG", "OL", "DI", "NT", "HE", "TR", "EX", "ES", "TU", "MP"};
        String[] ACTUAL = testBigramList.plaintextToBigrams();
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));

    }
    @Test
    @DisplayName("Should pad final bigram.")
    void shouldPadFinalBigram() {
        BigramList testBigramList = new BigramList("Hide the gold in the tree stum");
        String[] EXPECTED = {"HI","DE","TH","EG", "OL", "DI", "NT", "HE", "TR", "EX", "ES", "TU", "MX"};
        String[] ACTUAL = testBigramList.plaintextToBigrams();
        assertArrayEquals(EXPECTED, ACTUAL,() -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));
    }
}
