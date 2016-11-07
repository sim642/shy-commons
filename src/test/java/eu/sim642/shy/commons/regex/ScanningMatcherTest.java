package eu.sim642.shy.commons.regex;

import org.junit.Test;

import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class ScanningMatcherTest {

    private final String DIGIT_PATTERN_STRING = "[0-9]+";
    private final Pattern DIGIT_PATTERN = Pattern.compile(DIGIT_PATTERN_STRING);
    private final String TWO_DIGIT_PATTERN_STRING = "([0-9])([0-9])";
    private final Pattern TWO_DIGIT_PATTERN = Pattern.compile(TWO_DIGIT_PATTERN_STRING);
    private final String ALPHA_PATTERN_STRING = "[a-z]+";
    private final Pattern ALPHA_PATTERN = Pattern.compile(ALPHA_PATTERN_STRING);

    @Test
    public void testHasNextBegin() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        assertTrue(matcher.hasNext(DIGIT_PATTERN_STRING));
        assertTrue(matcher.hasNext(DIGIT_PATTERN));
        assertFalse(matcher.hasNext(ALPHA_PATTERN_STRING));
        assertFalse(matcher.hasNext(ALPHA_PATTERN));
    }

    @Test
    public void testHasNextMiddle() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);

        assertFalse(matcher.hasNext(DIGIT_PATTERN_STRING));
        assertFalse(matcher.hasNext(DIGIT_PATTERN));
        assertTrue(matcher.hasNext(ALPHA_PATTERN_STRING));
        assertTrue(matcher.hasNext(ALPHA_PATTERN));
    }

    @Test
    public void testHasNextEnd() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);
        matcher.next(ALPHA_PATTERN);

        assertFalse(matcher.hasNext(DIGIT_PATTERN_STRING));
        assertFalse(matcher.hasNext(DIGIT_PATTERN));
        assertFalse(matcher.hasNext(ALPHA_PATTERN_STRING));
        assertFalse(matcher.hasNext(ALPHA_PATTERN));
    }

    @Test
    public void testNextResultSuccess() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        MatchResult result123 = matcher.nextResult(DIGIT_PATTERN);
        assertEquals(0, result123.start());
        assertEquals(3, result123.end());
        assertEquals("123", result123.group());

        MatchResult resultabc = matcher.nextResult(ALPHA_PATTERN);
        assertEquals(3, resultabc.start());
        assertEquals(6, resultabc.end());
        assertEquals("abc", resultabc.group());
    }

    @Test
    public void testNextResultFailureBegin() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        assertNull(matcher.nextResult(ALPHA_PATTERN));

        MatchResult result123 = matcher.nextResult(DIGIT_PATTERN);
        assertEquals(0, result123.start());
        assertEquals(3, result123.end());
        assertEquals("123", result123.group());
    }

    @Test
    public void testNextResultFailureMiddle() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);

        assertNull(matcher.nextResult(DIGIT_PATTERN));

        MatchResult resultabc = matcher.nextResult(ALPHA_PATTERN);
        assertEquals(3, resultabc.start());
        assertEquals(6, resultabc.end());
        assertEquals("abc", resultabc.group());
    }

    @Test
    public void testNextResultFailureEnd() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);
        matcher.next(ALPHA_PATTERN);

        assertNull(matcher.nextResult(DIGIT_PATTERN));
        assertNull(matcher.nextResult(ALPHA_PATTERN));
    }

    @Test
    public void testNextResultString() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        MatchResult result123 = matcher.nextResult(DIGIT_PATTERN_STRING);
        assertEquals(0, result123.start());
        assertEquals(3, result123.end());
        assertEquals("123", result123.group());

        MatchResult resultabc = matcher.nextResult(ALPHA_PATTERN_STRING);
        assertEquals(3, resultabc.start());
        assertEquals(6, resultabc.end());
        assertEquals("abc", resultabc.group());
    }

    @Test
    public void testNextResultGroups() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        MatchResult result12 = matcher.nextResult(TWO_DIGIT_PATTERN);
        assertEquals("1", result12.group(1));
        assertEquals("2", result12.group(2));
    }

    @Test
    public void testNextSuccess() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        assertEquals("123", matcher.next(DIGIT_PATTERN));
        assertEquals("abc", matcher.next(ALPHA_PATTERN));
    }

    @Test
    public void testNextFailureBegin() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        assertNull(matcher.next(ALPHA_PATTERN));
        assertEquals("123", matcher.next(DIGIT_PATTERN));
    }

    @Test
    public void testNextFailureMiddle() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);

        assertNull(matcher.next(DIGIT_PATTERN));
        assertEquals("abc", matcher.next(ALPHA_PATTERN));
    }

    @Test
    public void testNextFailureEnd() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");
        matcher.next(DIGIT_PATTERN);
        matcher.next(ALPHA_PATTERN);

        assertNull(matcher.next(DIGIT_PATTERN));
        assertNull(matcher.next(ALPHA_PATTERN));
    }

    @Test
    public void testNextString() throws Exception {
        ScanningMatcher matcher = new ScanningMatcher("123abc");

        assertEquals("123", matcher.next(DIGIT_PATTERN_STRING));
        assertEquals("abc", matcher.next(ALPHA_PATTERN_STRING));
    }

    @Test
    public void testStringBuilder() throws Exception {
        StringBuilder sb = new StringBuilder();
        ScanningMatcher matcher = new ScanningMatcher(sb);

        assertFalse(matcher.hasNext(DIGIT_PATTERN));

        sb.append("123");
        assertEquals("123", matcher.next(DIGIT_PATTERN));

        sb.append("4");
        assertEquals("4", matcher.next(DIGIT_PATTERN));

        assertFalse(matcher.hasNext(DIGIT_PATTERN));
    }
}