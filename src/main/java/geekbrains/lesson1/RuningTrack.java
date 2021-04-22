package geekbrains.lesson1;

public class RuningTrack implements Obstacle {
    private int length;

    public RuningTrack(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Дорожка длиной " + length + "м. ";
    }

    @Override
    public boolean tryMake(RunnableJumpable runner) {
        return (runner.getMaxLength() >= this.length);
    }
}
