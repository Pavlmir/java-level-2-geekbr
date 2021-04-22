package geekbrains.lesson1;

public class Person implements RunnableJumpable {

    private String name;
    private int maxLength;
    private int maxHeight;

    public Person(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return name;
    }

    @Override
    public void jump() {
        System.out.println("Человек прыгает!");

    }

    @Override
    public void run() {
        System.out.println("Человек бежит!");

    }

    @Override
    public int getMaxLength() {
        return this.maxLength;
    }

    @Override
    public int getMaxHeight() {
        return this.maxHeight;
    }
}
