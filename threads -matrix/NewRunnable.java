
public class NewRunnable implements  Runnable {

    private int[] arr;


    public NewRunnable(int[] a) {
        arr = new int[a.length];
        for (int i = 0; i < a.length; i++) arr[i] = a[i];

    }

    @Override
    public void run() {
        int id = Integer.parseInt(Thread.currentThread().getName());
        System.out.println("Hello from " + Thread.currentThread().getName());
        System.out.println("id" + id);


        switch (id) {
            case 1:
                for (int i = 0; i < arr.length / 2; i++) System.out.println(arr[i]);
                break;
            case 2:
                for (int i = arr.length / 2; i < arr.length; i++) System.out.println(arr[i]);
                break;
        }

    }


    public static void main(String args[]) throws Exception {



        int [] array = {1,2,3,4,5,6,7,8,9,10};
        Thread t0 = Thread.currentThread();

        // 4. Create 2 threads
        Thread t1 = new Thread(new NewRunnable(array));
        Thread t2 = new Thread(new NewRunnable(array));

        // 5. Set the name of each thread
        t0.setName("Main Thread");

        t1.setName("1");
        t2.setName("2");

        t1.start();
        t2.start();

        // 7. Wait for them to join the current thread
        t1.join();
        t2.join();

        System.out.println("All printed");
    }
}
