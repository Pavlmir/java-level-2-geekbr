package geekbrains.lesson3;

import java.util.HashMap;
import java.util.HashSet;

public class PhoneDirectory {
    private final HashMap<String, HashSet<String>> phoneDir = new HashMap<>();

    public void add(String surname, String phone) {
        HashSet<String> phones = this.phoneDir.getOrDefault(surname, new HashSet<>());
        phones.add(phone);
        this.phoneDir.put(surname, phones);
    }

    public HashSet<String> get(String surname) {
        return phoneDir.get(surname);
    }
}
