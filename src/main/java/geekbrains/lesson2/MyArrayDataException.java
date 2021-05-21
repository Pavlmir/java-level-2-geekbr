package geekbrains.lesson2;

public class MyArrayDataException extends Exception {
    private final int summa;

    public MyArrayDataException(String message, int summa) {
        super(message);
        this.summa = summa;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "Сумма элементов на момент ошибки составляет: " + this.summa +  "\n";
    }
}