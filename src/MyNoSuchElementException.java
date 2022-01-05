public class MyNoSuchElementException extends Exception {
    MyNoSuchElementException() {
        System.out.println("Очередь пуста");
    }
}
