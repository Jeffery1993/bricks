package com.jeffery.bricks.list;

import com.jeffery.bricks.list.impl.ArrayList;
import com.jeffery.bricks.list.impl.LinkedList;
import org.junit.Assert;
import org.junit.Test;

public class ListTest {

    @Test
    public void test() {
        test(new ArrayList<>());
        test(new ArrayList<>(1));
        test(new LinkedList<>());
    }

    public void test(List<Integer> list) {
        Assert.assertEquals("[]", list.toString());
        list.add(0);
        Assert.assertEquals("[0]", list.toString());
        list.add(2);
        list.add(3);
        Assert.assertEquals("[0, 2, 3]", list.toString());
        list.insert(1, 1);
        Assert.assertEquals("[0, 1, 2, 3]", list.toString());
        list.delete(0);
        Assert.assertEquals("[1, 2, 3]", list.toString());
        Assert.assertEquals(3, list.size());
        list.clear();
        Assert.assertEquals("[]", list.toString());
    }

}
