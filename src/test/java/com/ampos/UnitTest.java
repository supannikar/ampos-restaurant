package com.ampos;

import org.junit.Test;
import com.ampos.restaurant.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UnitTest {
    @Test
    public void testCase1() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run("(a)(b)()c");
        assertThat(result, is(3));
    }

    @Test
    public void testCase2() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run("(a(b(c)d))");
        assertThat(result, is(3));
    }

    @Test
    public void testCase3() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run("oauaoeuu");
        assertThat(result, is(0));
    }

    @Test
    public void testCase4() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run("o(uaoeuu");
        assertThat(result, is(-1));
    }

    @Test
    public void testCase5() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run(")(uaoeuu");
        assertThat(result, is(-1));
    }
    
    @Test
    public void testCase6() {
        QuizEasy1 answerClass = new QuizEasy1();
        int result = answerClass.run("()a)(uaoeuu");
        assertThat(result, is(-1));
    }
}