package eu.sim642.shy.commons.regex;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Matcher which continuously scans the input.
 * Matching is performed after the last successful match, not from the beginning of the input each time.
 * Unlike {@link java.util.Scanner} no tokenization is done using delimiters.
 * @see Matcher
 * @author Simmo Saan
 */
public class ScanningMatcher {
    /**
     * Matcher used underlying operations.
     */
    private final Matcher matcher;

    /**
     * Construct a matcher from given input.
     * @param input input character sequence
     */
    public ScanningMatcher(CharSequence input) {
        matcher = Pattern.compile("").matcher(input); // empty pattern is a placeholder
    }

    /**
     * Matches the next prefix matching given pattern.
     * On successful match the start position is moved after the match.
     * @param pattern pattern to match
     * @return matched prefix, or {@code null} if prefix did not match the pattern
     */
    public String next(Pattern pattern) {
        MatchResult result = nextResult(pattern);
        return result != null ? result.group() : null;
    }

    /**
     * Matches the next prefix matching given pattern.
     * On successful match the start position is moved after the match.
     * Equivalent to {@code next(Pattern.compile(pattern))}
     * @param pattern pattern to match
     * @return matched prefix, or {@code null} if prefix did not match the pattern
     */
    public String next(String pattern) {
        return next(Pattern.compile(pattern));
    }

    /**
     * Matches the next prefix matching given pattern.
     * On successful match the start position is moved after the match.
     * @param pattern pattern to match
     * @return match result, or {@code null} if prefix did not match the pattern
     */
    public MatchResult nextResult(Pattern pattern) {
        if (hasNext(pattern)) {
            MatchResult result = matcher.toMatchResult();
            matcher.region(matcher.end(), matcher.regionEnd());
            return result;
        }
        else
            return null;
    }

    /**
     * Matches the next prefix matching given pattern.
     * On successful match the start position is moved after the match.
     * Equivalent to {@code nextResult(Pattern.compile(pattern))}
     * @param pattern pattern to match
     * @return match result, or {@code null} if prefix did not match the pattern
     */
    public MatchResult nextResult(String pattern) {
        return nextResult(Pattern.compile(pattern));
    }

    /**
     * Checks whether the next prefix matches given pattern.
     * @param pattern pattern to match
     * @return whether the prefix matches
     */
    public boolean hasNext(Pattern pattern) {
        matcher.usePattern(pattern);
        return matcher.lookingAt();
    }

    /**
     * Checks whether the next prefix matches given pattern.
     * Equivalent to {@code hasNext(Pattern.compile(pattern))}
     * @param pattern pattern to match
     * @return whether the prefix matches
     */
    public boolean hasNext(String pattern) {
        return hasNext(Pattern.compile(pattern));
    }
}