package geekbrains.lesson1;

public class Robot implements RunnableJumpable {

    private String name;
    private int maxLength;
    private int maxHeight;

    public String getName() {
        return name;
    }

    public Robot(String name, int maxLength, int maxHeight) {
        this.name = name;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
    }

    @Override
    public void jump() {
        System.out.println("Робот прыгает!");

    }

    @Override
    public void run() {
        System.out.println("Робот бежит!");
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