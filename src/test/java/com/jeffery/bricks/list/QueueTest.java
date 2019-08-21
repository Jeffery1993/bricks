package com.jeffery.bricks.list;

import com.jeffery.bricks.list.impl.ArrayQueue;
import com.jeffery.bricks.list.impl.LinkedQueue;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

    private static final int CAPACITY = 2;

    @Test
    public void test() {
        test(new ArrayQueue<>(CAPACITY));
        test(new LinkedQueue<>(CAPACITY));
    }

    public void test(Queue<Integer> queue) {
        Assert.assertEquals(0, queue.size());
        queue.enqueue(1);
        Assert.assertEquals(1, queue.size());
        queue.enqueue(2);
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(Integer.valueOf(1), queue.dequeue());
        Assert.assertEquals(1, queue.size());
        Assert.assertEquals(Integer.valueOf(2), queue.dequeue());
        Assert.assertEquals(0, queue.size());
    }

}
