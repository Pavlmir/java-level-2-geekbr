package geekbrains.lesson2;

public class Main {

    // 1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
    // при подаче массива другого размера необходимо бросить исключение MyArraySizeException.

    // 2. Далее метод должен пройтись по всем элементам массива, преобразовать в int, и просуммировать.
    // Если в каком-то элементе массива преобразование не удалось
    // (например, в ячейке лежит символ или текст вместо числа), должно быть брошено исключение MyArrayDataException,
    // с детализацией в какой именно ячейке лежат неверные данные.

    // 3. В методе main() вызвать полученный метод, обработать возможные исключения
    // MySizeArrayException и MyArrayDataException, и вывести результат расчета.

    private static final int MAX_SIZE = 4;

    public static void main(String[] args) throws MyArraySizeException, MyArrayDataException {

        String[][] myWrongArray1 = new String[][]{
                {"1", "2", "3", "4"},
                {"1", "2", "5", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}};

        String[][] myWrongArray2 = new String[][]{
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "G"}};

        String[][] myRightArray = new String[][]{
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}};

        try {
            System.out.println("Сумма элементов равна: " + myMethod(myWrongArray1) +  "\n");
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов равна: " + myMethod(myWrongArray2) +  "\n");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Сумма элементов равна: " + myMethod(myRightArray) +  "\n");
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        }

    }

    public static int myMethod(String[][] myArray) throws MyArrayDataException, MyArraySizeException {
        int summa = 0;
        if ((myArray.length > MAX_SIZE || myArray[0].length > MAX_SIZE)) {
            throw new MyArraySizeException("Размер должен быть 4x4 \n");
        } else {
            for (int i = 0; i < myArray.length; i++) {
                for (int j = 0; j < myArray[i].length; j++) {
                    System.out.printf("%2s", myArray[i][j]);
                    try {
                        summa += Integer.parseInt(myArray[i][j]);
                    } catch (NumberFormatException e) {
                        String s = String.format("\nВ строке: %s, колонке: %s, находится символ: %s\n",
                                (i + 1), (j + 1), myArray[i][j]);
                        throw new MyArrayDataException(s, summa);
                    }
                }
                System.out.println();
            }
        }
        return summa;
    }

}
