package playfair;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Playfair KeyGrid tests")
public class KeyGridTest {

    private KeyGrid testKeyGrid;


    @BeforeEach
    void beforeEach() {
        System.out.println("Create KeyGrid object with test key.");
        testKeyGrid = new KeyGrid("playfair example");
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
        char[][] ACTUAL = testKeyGrid.getKeyGrid();
        assertArrayEquals(EXPECTED, ACTUAL,
                () -> String.format("Actual Array: %s", Arrays.deepToString(ACTUAL)));

    }

    @Test
    @DisplayName("Should remove duplicate characters, keeping first occurrences.")
    void shouldRemoveDupeCharsKeepFirst() {
        String EXPECTED = "ALEX";
        String ACTUAL = testKeyGrid.removeDuplicateChars("AALEEEXX",true);
        assertEquals(EXPECTED, ACTUAL);

    }


    @Test
    @DisplayName("Should remove duplicate characters.")
    void shouldRemoveDupeChars() {
        String EXPECTED = "L";
        String ACTUAL = testKeyGrid.removeDuplicateChars("AALEEEXX",false);
        assertEquals(EXPECTED, ACTUAL);

    }

    @Test
    @DisplayName("Should clean key string.")
    void shouldCleanKeyString() {
        String EXPECTED = "PLAYFAIREXAMPLE";
        String ACTUAL = testKeyGrid.cleanKeyString("playfair example");
        assertEquals(EXPECTED, ACTUAL);

    }

    @Test
    @DisplayName("Should find char in keygrid.")
    void shouldSearchKeygrid() {
        char targetChar='X';
        int[] EXPECTED = {1,3};
        int[] ACTUAL = testKeyGrid.searchKeyGrid(targetChar);
        assertArrayEquals(EXPECTED, ACTUAL);
    }

}
