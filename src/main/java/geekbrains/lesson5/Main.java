/*
1) два метода, которые cоздают одномерный длинный массив:
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);

Отличие первого метода от второго:
Первый просто бежит по массиву и вычисляет значения.
Второй разбивает массив на два массива, в двух потоках высчитывает
новые значения и потом склеивает эти массивы обратно в один.

Пример деления одного массива на два:
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

Пример обратной склейки:
System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

Примечание:
System.arraycopy() – копирует данные из одного массива в другой:
System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение,
откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
По замерам времени:
Для первого метода надо считать время только на цикл расчета:
for (int i = 0; i < size; i++) {
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
}
Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
*/
package geekbrains.lesson5;


public class Main {

    private static final int SIZE = 10000000;
    private static final int HALF = SIZE / 2;
    private static float[] arr = new float[SIZE];

    public static void main(String[] args) {
        method1();
        method2();
    }

    private static void fillArray() {
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }
    }

    private static void method1() {
        fillArray();
        long a = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.printf("Время работы 1-ого метода = %s мс.\n", System.currentTimeMillis() - a);
    }

    private static void method2() {
        fillArray();
        long a = System.currentTimeMillis();
        Thread th1 = new Thread(() -> {
            float[] arrCopy = new float[HALF];
            // 1-ая часть, копирование HALF элементов из массива arr с позиции 0, в массив arrCopy с позиции 0
            System.arraycopy(arr, 0, arrCopy, 0, HALF);
            for (int i = 0; i < HALF; i++) {
                arrCopy[i] = (float) (arrCopy[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            // склеиваем массив обратно
            System.arraycopy(arrCopy, 0, arr, 0, HALF);
        });
        th1.start();
        Thread th2 = new Thread(() -> {
            float[] arrCopy = new float[HALF];
            // 2-ая часть, копирование HALF элементов из массива arr с позиции HALF, в массив arrCopy с позиции 0
            System.arraycopy(arr, HALF, arrCopy, 0, HALF);
            for (int i = HALF; i < SIZE; i++) {
                int c = i - HALF;
                arrCopy[c] = (float) (arrCopy[c] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
            // склеиваем массив обратно
            System.arraycopy(arrCopy, 0, arr, HALF, HALF);
        });
        th2.start();
        try {
            // Главный поток main продолжит работу только после того, как th1 и th2 завершат работу
            th1.join();
            th2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("Время работы 2-ого метода = %s мс.\n", System.currentTimeMillis() - a);
    }
}
