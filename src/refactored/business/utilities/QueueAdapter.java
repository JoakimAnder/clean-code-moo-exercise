package refactored.business.utilities;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class QueueAdapter<T> implements SimpleQueue<T> {
    BlockingQueue<T> queue = new ArrayBlockingQueue<>(100);

    @Override
    public void push(T element) {
        try {
            queue.put(element);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T pull() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public int length() {
        return queue.size();
    }
}
