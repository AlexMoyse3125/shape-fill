import java.util.ArrayList;


class Shape {
    
    ArrayList<BlockVector> vectors;
    int x;
    int y;
    int z;
    boolean[][][] representation;
    boolean legal;

    public Shape(int x, int y, int z, ArrayList<BlockVector> vectors) {
        this.vectors = new ArrayList<BlockVector>();
        for (BlockVector v : vectors) {
            BlockVector blockVector = new BlockVector(v.x, v.y, v.z);
            this.vectors.add(blockVector);
        }
        this.x = x;
        this.y = y;
        this.z = z;
        legal = true;
        representation = new boolean[4][4][2];
        representation[x][y][z] = true;
        for (BlockVector v : this.vectors) {
            if (x + v.x >= 0 && x + v.x < 4 && y + v.y >= 0 && y + v.y < 4 && z + v.z >= 0 && z + v.z < 2) {
                representation[x+v.x][y+v.y][z+v.z] = true;
            }
            else {
                legal = false;
                break;
            }
        }
    }

    void updateRepresentation() {
        representation = new boolean[4][4][2];
        for (BlockVector v : this.vectors) {
            if (x + v.x >= 0 && x + v.x < 4 && y + v.y >= 0 && y + v.y < 4 && z + v.z >= 0 && z + v.z < 2) {
                representation[x+v.x][y+v.y][z+v.z] = true;
                System.out.println("here");
            }
            else {
                legal = false;
                break;
            }
        }
    }

    void rotateVectorsAboutX() {
        for (BlockVector v : vectors) {
            v.rotateAboutX();
        }
    }

    void rotateVectorsAboutY() {
        for (BlockVector v : vectors) {
            v.rotateAboutY();
        }
    }

    void rotateVectorsAboutZ() {
        for (BlockVector v : vectors) {
            v.rotateAboutZ();
        }
    }
    
    public ArrayList<Shape> getAllVariations() {
        ArrayList<Shape> shapes = new ArrayList<Shape>();
        for (int x = 0; x<4; x++) {
            for (int y = 0; y<4; y++) {
                for (int z = 0; z<2; z++) {
                    Shape shape = new Shape(x, y, z, this.vectors);
                    for (int i = 0; i<4; i++) {
                        shape.rotateVectorsAboutX();
                        for (int j = 0; j<4; j++) {
                            shape.rotateVectorsAboutY();
                            for (int k = 0; k<4; k++) {
                                shape.rotateVectorsAboutZ();
                                Shape newShape = new Shape(shape.x, shape.y, shape.z, shape.vectors);
                                if (newShape.legal && (!newShape.isMember(shapes))) shapes.add(newShape);
                                

                            }
                        }
                    }
                }
            }
        }
        return shapes;
    }

    public boolean isEqual(Shape s) {
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                for (int k = 0; k<2; k++) {
                    if ((s.representation[i][j][k] ^ this.representation[i][j][k])) return false;
                }
            }
        }
        return true;
        
    }

    Boolean isMember(ArrayList<Shape> shapes) {
        for (Shape s : shapes) {
            if (isEqual(s)) return true;
        }
        return false;
    }

    public String toString() {
        String s = "";
        for (int k = 0; k<2; k++) {
            for (int j = 3; j>=0; j--) {
                for (int i = 0; i<4; i++) {
                    if (representation[i][j][k])  s += "X ";
                    else s += "O ";
                }
                s += "\n";
            }
            s += "\n\n";

        }
        return s;
    }
}

