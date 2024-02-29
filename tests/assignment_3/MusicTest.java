package assignment_3;

import assignment_3.instruments.decorators.ShiftedMusicalInstrument;
import assignment_3.instruments.decorators.StaccatoMusicalInstrument;
import assignment_3.instruments.decorators.TempoScaledMusicalInstrument;
import assignment_3.instruments.*;
import assignment_3.player.*;
import org.junit.jupiter.api.*;

import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class MusicTest {

    private static int sleepTime = 1;

    private String expected, output;
    private SongLibrary library = SongLibrary.library;

    private static MusicalInstrument piano;
    static TextCollector textCollector;
    static PrintStream origOut;

    @BeforeAll
    static void setup(){

        // Collecting the text from System.out:

        try {
            piano = new Piano();
        } catch (UnknownInstrumentException e) {
            throw new RuntimeException(e);
        }
        origOut = System.out;
        textCollector = new TextCollector(System.out);
        System.setOut(new PrintStream(textCollector));
    }

    @AfterAll
    static void tearDown(){
        // restore original output
        System.setOut(origOut);
    }

    @BeforeEach
    void methodSetup() {
        // Verifying the output string, expected string, and text buffer are empty:
        output = "";
        expected = "";
        textCollector.collect();
    }

    @AfterEach
    void methodTearDown(){
        // Printing mismatches between expected and obtained outputs
        // (if there are any), and sleeping:
        if (!expected.equals(output)) {
            printMismatch(expected, output);
        }
        sleep(sleepTime);
    }

    @Test
    void testUnknownInstrumentException() {
        Exception exception = assertThrows(UnknownInstrumentException.class, () -> {
            MusicalInstrument drums = new Drums();
        });

        String expectedMessage = "Unknown instrument: Drums.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage), formatDiff(expectedMessage, exception));
    }

    @Test
    void testSoundDurationException() {
        // Checking for nonempty sound:
        Exception exception1 = assertThrows(SoundDurationException.class, () -> {
            piano.play(new SoundSet(-5, "C4"));
        });

        String expectedMessage = "Sound duration must be nonnegative, got -5ms.";
        String actualMessage = exception1.getMessage();
        assertTrue(actualMessage.equals(expectedMessage), formatDiff(expectedMessage, exception1));

        // Checking for break:
        Exception exception2 = assertThrows(SoundDurationException.class, () -> {
            piano.play(new SoundSet(-80));
        });

        expectedMessage = "Sound duration must be nonnegative, got -80ms.";
        actualMessage = exception2.getMessage();
        assertTrue(actualMessage.equals(expectedMessage), formatDiff(expectedMessage, exception2));
    }

    @Test
    void testPitchOutOfRangeException() {

        // Checking range boundaries:
        assertDoesNotThrow(() -> {
            piano.play(new SoundSet(10, 0));
        });
        assertDoesNotThrow(() -> {
            piano.play(new SoundSet(10, 127));
        });

        // Checking exceptions are thrown when outside the range:
        Exception exception1 = assertThrows(PitchOutOfRangeException.class, () -> {
            piano.play(new SoundSet(10, -1));
        });

        String expectedMessage = "Invalid pitch. Pitch value must be between 0 and 127, got -1.";
        String actualMessage = exception1.getMessage();
        assertTrue(actualMessage.equals(expectedMessage),
                formatDiff(expectedMessage, exception1));

        Exception exception2 = assertThrows(PitchOutOfRangeException.class, () -> {
            piano.play(new SoundSet(10, 128));
        });

        expectedMessage = "Invalid pitch. Pitch value must be between 0 and 127, got 128.";
        actualMessage = exception2.getMessage();
        assertTrue(actualMessage.equals(expectedMessage),
                formatDiff(expectedMessage, exception2));


        // Checking out of range within a chord:
        Exception exception3 = assertThrows(PitchOutOfRangeException.class, () -> {
            piano.play(new SoundSet(10, 60, 64, 200));
        });

        expectedMessage = "Invalid pitch. Pitch value must be between 0 and 127, got 200.";
        actualMessage = exception3.getMessage();
        assertTrue(actualMessage.equals(expectedMessage),
                formatDiff(expectedMessage, exception3));

    }


    @Test
    void testTempoScaled() {

        Song littleJonathan = SongLibrary.library.getSong("Little Jonathan");
        MusicalInstrument tempoScaledPiano = null;
        tempoScaledPiano = new TempoScaledMusicalInstrument(piano, 0.5);
        tempoScaledPiano.playSong(littleJonathan);

        expected =
                "Playing Little Jonathan on 0.5 tempo scaled Piano\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 125ms, notes: E4\n" +
                        "Playing Duration: 250ms, notes: E4\n" +
                        "Playing Duration: 125ms, notes: F4\n" +
                        "Playing Duration: 125ms, notes: D4\n" +
                        "Playing Duration: 250ms, notes: D4\n" +
                        "Playing Duration: 125ms, notes: C4\n" +
                        "Playing Duration: 125ms, notes: D4\n" +
                        "Playing Duration: 125ms, notes: E4\n" +
                        "Playing Duration: 125ms, notes: F4\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 250ms, notes: G4\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 125ms, notes: E4\n" +
                        "Playing Duration: 250ms, notes: E4\n" +
                        "Playing Duration: 125ms, notes: F4\n" +
                        "Playing Duration: 125ms, notes: D4\n" +
                        "Playing Duration: 250ms, notes: D4\n" +
                        "Playing Duration: 125ms, notes: C4\n" +
                        "Playing Duration: 125ms, notes: E4\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 125ms, notes: G4\n" +
                        "Playing Duration: 500ms, notes: C4\n" +
                        "Done playing Little Jonathan\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }

    @Test
    void testStaccato() {

        Song littleJonathan = SongLibrary.library.getSong("Little Jonathan");
        MusicalInstrument staccatoPiano = null;
        staccatoPiano = new StaccatoMusicalInstrument(piano);
        staccatoPiano.playSong(littleJonathan);

        expected =
                "Playing Little Jonathan on Piano with staccato\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 480ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: F4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: D4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: D4\n" +
                        "Playing Duration: 480ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: C4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: D4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: F4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 480ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 480ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: F4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: D4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: D4\n" +
                        "Playing Duration: 480ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: C4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: E4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: G4\n" +
                        "Playing Duration: 230ms, notes: break\n" +
                        "Playing Duration: 20ms, notes: C4\n" +
                        "Playing Duration: 980ms, notes: break\n" +
                        "Done playing Little Jonathan\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }

    @Test
    void testShifted() {

        Song littleJonathan = SongLibrary.library.getSong("Little Jonathan");
        MusicalInstrument shiftedPiano = null;
        shiftedPiano = new ShiftedMusicalInstrument(piano, 5);
        shiftedPiano.playSong(littleJonathan);

        expected =
                "Playing Little Jonathan on 5 shifted Piano\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 250ms, notes: A4\n" +
                        "Playing Duration: 500ms, notes: A4\n" +
                        "Playing Duration: 250ms, notes: A#4\n" +
                        "Playing Duration: 250ms, notes: G4\n" +
                        "Playing Duration: 500ms, notes: G4\n" +
                        "Playing Duration: 250ms, notes: F4\n" +
                        "Playing Duration: 250ms, notes: G4\n" +
                        "Playing Duration: 250ms, notes: A4\n" +
                        "Playing Duration: 250ms, notes: A#4\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 500ms, notes: C5\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 250ms, notes: A4\n" +
                        "Playing Duration: 500ms, notes: A4\n" +
                        "Playing Duration: 250ms, notes: A#4\n" +
                        "Playing Duration: 250ms, notes: G4\n" +
                        "Playing Duration: 500ms, notes: G4\n" +
                        "Playing Duration: 250ms, notes: F4\n" +
                        "Playing Duration: 250ms, notes: A4\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 250ms, notes: C5\n" +
                        "Playing Duration: 1000ms, notes: F4\n" +
                        "Done playing Little Jonathan\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }

    @Test
    void testShiftedRepeats() {
        try {
            MusicalInstrument shiftedPiano = new ShiftedMusicalInstrument(piano, -5);
            SoundSet C4maj = new SoundSet(200, "C4", "E4", "G4");
            piano.play(C4maj);
            shiftedPiano.play(C4maj);
            piano.play(C4maj);
            shiftedPiano.play(C4maj);
        } catch (SoundException e) {
            fail(e);
        }

        expected =
                "Playing Duration: 200ms, notes: C4, E4, G4\n" +
                        "Playing Duration: 200ms, notes: G3, B3, D4\n" +
                        "Playing Duration: 200ms, notes: C4, E4, G4\n" +
                        "Playing Duration: 200ms, notes: G3, B3, D4\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }

    @Test
    void testShiftedOutOfRange() {
        MusicalInstrument shiftedPiano = new ShiftedMusicalInstrument(piano, -65);
        SoundSet C4maj = new SoundSet(200, "C4", "E4", "G4");

        Exception exception = assertThrows(PitchOutOfRangeException.class, () -> {
            shiftedPiano.play(C4maj);
        });

        String expectedMessage = "Invalid pitch. Pitch value must be between 0 and 127, got -5.";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.equals(expectedMessage), formatDiff(expectedMessage, exception));
    }

    @Test
    void testDoubleDecorated() {

        Song littleJonathan = SongLibrary.library.getSong("Little Jonathan");
        MusicalInstrument shifted = new ShiftedMusicalInstrument(piano, 5);
        MusicalInstrument tempoScaledShifted = new TempoScaledMusicalInstrument(shifted, 0.4);
        tempoScaledShifted.playSong(littleJonathan);

        expected =
                "Playing Little Jonathan on 0.4 tempo scaled 5 shifted Piano\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 100ms, notes: A4\n" +
                        "Playing Duration: 200ms, notes: A4\n" +
                        "Playing Duration: 100ms, notes: A#4\n" +
                        "Playing Duration: 100ms, notes: G4\n" +
                        "Playing Duration: 200ms, notes: G4\n" +
                        "Playing Duration: 100ms, notes: F4\n" +
                        "Playing Duration: 100ms, notes: G4\n" +
                        "Playing Duration: 100ms, notes: A4\n" +
                        "Playing Duration: 100ms, notes: A#4\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 200ms, notes: C5\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 100ms, notes: A4\n" +
                        "Playing Duration: 200ms, notes: A4\n" +
                        "Playing Duration: 100ms, notes: A#4\n" +
                        "Playing Duration: 100ms, notes: G4\n" +
                        "Playing Duration: 200ms, notes: G4\n" +
                        "Playing Duration: 100ms, notes: F4\n" +
                        "Playing Duration: 100ms, notes: A4\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 100ms, notes: C5\n" +
                        "Playing Duration: 400ms, notes: F4\n" +
                        "Done playing Little Jonathan\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }

    @Test
    void testTripleDecorated() {

        Song littleJonathan = SongLibrary.library.getSong("Little Jonathan");
        MusicalInstrument shifted = new ShiftedMusicalInstrument(piano, 5);
        MusicalInstrument tempoScaledShifted = new TempoScaledMusicalInstrument(shifted, 0.8);
        MusicalInstrument staccatoTempoScaledShifted = new StaccatoMusicalInstrument(tempoScaledShifted);
        staccatoTempoScaledShifted.playSong(littleJonathan);

        expected =
                "Playing Little Jonathan on 0.8 tempo scaled 5 shifted Piano with staccato\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 384ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A#4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: G4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: G4\n" +
                        "Playing Duration: 384ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: F4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: G4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A#4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 384ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 384ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A#4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: G4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: G4\n" +
                        "Playing Duration: 384ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: F4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: A4\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: C5\n" +
                        "Playing Duration: 184ms, notes: break\n" +
                        "Playing Duration: 16ms, notes: F4\n" +
                        "Playing Duration: 784ms, notes: break\n" +
                        "Done playing Little Jonathan\n";

        output = textCollector.collect();
        assertTrue(expected.equals(output));
    }



    private static void printMismatch(String expected, String output) {
        int maxIx = Math.min(expected.length(), output.length());
        int i = 0;
        for (; i < maxIx && output.charAt(i) == expected.charAt(i); i++);
        if (i == maxIx) {
            // One string is a prefix of the other
            if (maxIx == output.length()) {
                // output is shorter than expected
                System.err.println("Output contains only " + maxIx +
                        " characters out of an expected " + expected.length() +
                        " characters. Missing expected suffix:");
                System.err.println("\"" + expected.substring(maxIx) + "\"");
            }
            else {
                // output is longer than expected
                System.err.println("Expected output contains only " + maxIx +
                        " characters, output contained " + output.length() +
                        " characters. Unexpected suffix:");
                System.err.println("\"" + output.substring(maxIx) + "\"");
            }
        }
        else {
            String prefix = expected.substring(0, i + 1);
            int rowIx = (int) prefix.chars().filter(ch -> ch == '\n').count() + 1;
            int rowStart = prefix.lastIndexOf('\n') + 1;
            int mismatchIx = i - rowStart + 1;
            String expectedSuffix = expected.substring(rowStart);
            String outputSuffix = output.substring(rowStart);
            int expectedRowEnd = expectedSuffix.indexOf('\n');
            if (expectedRowEnd == -1) {
                expectedRowEnd = expectedSuffix.length();
            }
            int outputRowEnd = outputSuffix.indexOf('\n');
            if (outputRowEnd == -1) {
                outputRowEnd = outputSuffix.length();
            }

            System.err.println("First output mismatch at row " + rowIx + ", character " + mismatchIx + ":");
            System.err.println("Expected: \"" + expectedSuffix.substring(0, expectedRowEnd) + "\"");
            System.err.println("Obtained: \"" + outputSuffix.substring(0, outputRowEnd) + "\"");
        }
    }

    private static String formatDiff(String expectedMessage, Exception exception) {
        return "Expected exception message was:\n\"" + expectedMessage +
                "\"\nObtained message was:\n\"" + exception.getMessage() + "\"";
    }

    private static void sleep(int seconds) {
        // Auxiliary function to delay the program for a given amount of seconds.
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private class Drums extends MusicalInstrumentImpl {
        public Drums() throws UnknownInstrumentException {
            super("Drums", 4);
        }
    }
}