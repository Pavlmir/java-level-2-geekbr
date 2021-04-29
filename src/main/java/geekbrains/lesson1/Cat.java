package geekbrains.lesson1;

public class Cat implements  RunnableJumpable {
    int maxHeight;
    int maxLength;
    String name;
    boolean winner = false;

    public String getName() {
        return name;
    }

    public Cat() {
        this.name = "Безымянный";
        this.maxHeight = 2;
        this.maxLength = 50;
    }

    public Cat(String name, int maxHeight, int maxLength) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.maxLength = maxLength;
    }

    @Override
    public void run() {
        System.out.println("Котик бежит!");

    }

    @Override
    public void jump() {
        System.out.println("Котик прыгает!");

    }

    @Override
    public String toString() {
        return "Кот может пробежать = " + maxLength + " м. может прыгнуть на " + maxHeight + "м.) ";
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public int getMaxLength() {
        return maxLength;
    }
}
