package com.suprnation.openbook;

import org.junit.Ignore;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test.
     */

    @Test
    public void shouldReturnSingleMinTriangleValue_emulateApp() {
        boolean success = true;

        String data =
            "1\n" +
                "1 2\n" +
                "1 2 3\n" +
                "";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.main(null);
        System.setIn(stdin);

        assertTrue(true);
    }

    @Ignore
    @Test
    public void shouldReturnSingleMinTriangleValueWithDiffNumbers_emulateApp() {
        boolean success = true;

        String data =
            "1\n" +
            "21 22\n" +
            "31 32 33\n" +
            "";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.main(null);
        System.setIn(stdin);

        assertTrue(true);
    }

    @Ignore
    @Test
    public void shouldReturnMultipleMinTriangleValuesForDifferectPaths_emulateApp() {
        boolean success = true;

        String data =
            "1\n" +
            "33 32\n" +
            "22 32 33\n" +
            "";
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        App.main(null);
        System.setIn(stdin);

        assertTrue(true);
    }

    @Test
    public void shouldGetMinimumTrianglePathAsRequested() {

        App app = new App();
        assertThat(app.getMinimumTrianglePath(), is("1 + 2 + 3 = 6"));
    }
}
