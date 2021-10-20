package Project.junit;

import Project.PolishNotation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class toPolishNotationTest {

    @Test
    void RecordToPolishAccess() {
        var polsk = PolishNotation.toPolishNotation("(44+6)*2/(7+3)");
        assertEquals("44 6 + 2 * 7 3 + /", polsk);
        var answer = PolishNotation.getAnswer("44 6 + 2 * 7 3 + /");
        assertEquals(10,answer);
    }
}
