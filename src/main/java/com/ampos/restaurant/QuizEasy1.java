// Easy-1

package com.ampos.restaurant;

public class QuizEasy1 {
    public int run(String inputChar) {
        char[] chars = inputChar.toCharArray();
        int lCount = 0;
        int rCount = 0;
        boolean findNode = true;
        for (char c: chars) {
            if (Character.toString(c).equals("(")) {
                lCount++;
                findNode = false;
            }
            if (Character.toString(c).equals(")")) {
                rCount++;
                findNode = true;
            }
        }

        if ((lCount == rCount) && findNode) {
            return lCount;
        }

        return -1;
    }
}