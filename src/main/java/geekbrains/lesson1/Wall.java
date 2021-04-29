package geekbrains.lesson1;

public class Wall implements Obstacle {
    private int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Стена выстой " + height + "м. ";
    }

    @Override
    public boolean tryMake(RunnableJumpable jumper) {
        return (jumper.getMaxHeight() >= this.height);


    }
}
