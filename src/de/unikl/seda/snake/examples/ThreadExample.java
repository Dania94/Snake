package de.unikl.seda.snake.examples;

class SpamThread extends Thread {
    String message;
    int timeout;

    SpamThread(String message, int timeout) {
        this.message = message;
        this.timeout = timeout;
    }

    @Override
    public void run() {
        while(true) {
            System.out.println(message);

            try {
                Thread.sleep(timeout);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadExample {
    public static void main(String[] args) {
        SpamThread st = new SpamThread("Me!", 150);
        st.start();

        SpamThread st2 = new SpamThread("No me!", 500);
        st2.start();
    }
}

