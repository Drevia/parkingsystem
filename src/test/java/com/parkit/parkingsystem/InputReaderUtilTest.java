package com.parkit.parkingsystem;

import com.parkit.parkingsystem.util.InputReaderUtil;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputReaderUtilTest {


    @Test
    public void testReadSelection() {
        String input = "123";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        InputReaderUtil inputReaderUtil = new InputReaderUtil();
        int result = inputReaderUtil.readSelection();

        assertEquals(123, result);
    }
    @Test
    public void testReadSelectionWithException() {
        String input = "not_a_number\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        InputReaderUtil inputReaderUtil = new InputReaderUtil();
        int result = inputReaderUtil.readSelection();
        assertEquals(-1, result);
    }

}
