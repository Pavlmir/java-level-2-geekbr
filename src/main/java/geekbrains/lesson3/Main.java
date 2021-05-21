package geekbrains.lesson3;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        /*
        1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
         Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
         Посчитать сколько раз встречается каждое слово.
        */
        System.out.println("Задание 1:");
        wordCount();

        /*
        2. Написать простой класс ТелефонныйСправочник, который хранит
         в себе список фамилий и телефонных номеров. В этот телефонный справочник
         с помощью метода add() можно добавлять записи.
         С помощью метода get() искать номер телефона по фамилии.
         Следует учесть, что под одной фамилией может быть несколько телефонов
         (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
        */
        System.out.println("Задание 2:");
        PhoneDirectory phoneDirectory = new PhoneDirectory();
        phoneDirectory.add("Каменский", "79013218890");
        phoneDirectory.add("Бодров", "79013218891");
        phoneDirectory.add("Бодров", "79013218892");
        String surname = "Бодров";
        System.out.printf("Телефонные номера по фамилии %s - %s \n", surname, phoneDirectory.get(surname));
        surname = "Каменский";
        System.out.printf("Телефонные номера по фамилии %s - %s \n", surname, phoneDirectory.get(surname));

    }

    private static void wordCount() {
        String[] words = {
                "балкон",
                "енот",
                "каньон",
                "смех",
                "шоколад",
                "кот",
                "енот",
                "банан",
                "кекс",
                "шоколад"
        };

        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        for (String word : words) {
            Integer c = hashMap.getOrDefault(word, 0);
            hashMap.put(word, c + 1);
        }
        System.out.printf("Список уникальных слов %s \n", hashMap.keySet());
        System.out.printf("Каждое слово встречается: %s \n", hashMap);
    }

}
