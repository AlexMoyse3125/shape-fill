public class BlockVector {
    public int x;
    public int y;
    public int z;

    BlockVector(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void rotateAboutX() {
        int y = this.y;
        this.y = this.z * -1;
        this.z = y;
    }

    public void rotateAboutY() {
        int x = this.x;
        this.x = this.z * -1;
        this.z = x;
    }

    public void rotateAboutZ() {
        int x = this.x;
        this.x = y;
        this.y = x * -1;
    }
}