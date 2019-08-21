package com.jeffery.bricks.list;

import com.jeffery.bricks.list.impl.ArrayStack;
import com.jeffery.bricks.list.impl.LinkedStack;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {

    private static final int CAPACITY = 2;

    @Test
    public void test() {
        test(new ArrayStack<>(CAPACITY));
        test(new LinkedStack<>(CAPACITY));
    }

    public void test(Stack<Integer> stack) {
        Assert.assertEquals(0, stack.size());
        stack.push(1);
        Assert.assertEquals(1, stack.size());
        stack.push(2);
        Assert.assertEquals(2, stack.size());
        Assert.assertEquals(Integer.valueOf(2), stack.pop());
        Assert.assertEquals(1, stack.size());
        Assert.assertEquals(Integer.valueOf(1), stack.pop());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    public void testNumberConversion() {
        for (int i = 1; i < 1024; i++) {
            Assert.assertEquals(Integer.toBinaryString(i), toBinaryString(i));
        }
    }

    private static String toBinaryString(int num) {
        Stack<Integer> stack = new LinkedStack<>(10);
        while (num != 0) {
            stack.push(num % 2);
            num = num / 2;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    @Test
    public void testBrackets() {
        Assert.assertEquals(true, matchesBrackets("()"));
        Assert.assertEquals(true, matchesBrackets("([])"));
        Assert.assertEquals(true, matchesBrackets("(([]))"));
        Assert.assertEquals(false, matchesBrackets("("));
        Assert.assertEquals(false, matchesBrackets("()]"));
        Assert.assertEquals(false, matchesBrackets("(()]"));
    }

    private static boolean matchesBrackets(String input) {
        char[] arr = input.toCharArray();
        Stack<Character> stack = new LinkedStack<>(10);
        for (char ch : arr) {
            switch (ch) {
                case '(': {
                    stack.push(ch);
                    break;
                }
                case '[': {
                    stack.push(ch);
                    break;
                }
                case ')': {
                    if (!stack.isEmpty() && stack.peek().charValue() == '(') {
                        stack.pop();
                    } else {
                        stack.push(ch);
                    }
                    break;
                }
                case ']': {
                    if (!stack.isEmpty() && stack.peek().charValue() == '[') {
                        stack.pop();
                    } else {
                        stack.push(ch);
                    }
                    break;
                }
            }
        }
        return stack.isEmpty();
    }

}
