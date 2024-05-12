import java.util.ArrayList;

public class Block {
    boolean[][][] grid;
    ArrayList<Shape> shapes;

    Block() {
        grid = new boolean[4][4][2];
        shapes = new ArrayList<Shape>();
    }

    Block(Block b) {
        grid = new boolean[4][4][2];
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                for (int k = 0; k<2; k++) {
                    this.grid[i][j][k] = b.grid[i][j][k];
                }
            }
        }
        shapes = new ArrayList<Shape>();
        for (Shape shape : b.shapes) {
            shapes.add(shape);
        };
    }

    boolean addShape(Shape s) {
        for (int i = 0; i<4; i++) {
            for (int j = 0; j<4; j++) {
                for (int k = 0; k<2; k++) {
                    if (grid[i][j][k] && s.representation[i][j][k]) return false;
                    else grid[i][j][k] = grid[i][j][k] || s.representation[i][j][k];
                }
            }
        }
        shapes.add(s);
        return true;

    }

    public static void main(String args[]) {
        long startTime = System.nanoTime();

        BlockVector a1 = new BlockVector(0, 0, 1);
        BlockVector a2 = new BlockVector(1, 1, 1);
        BlockVector a3 = new BlockVector(2, 0, 1);
        BlockVector a4 = new BlockVector(0, 1, 1);
        BlockVector a5 = new BlockVector(1, 0, 1);
        BlockVector a6 = new BlockVector(2, 1, 1);
        ArrayList<BlockVector> aVectors = new ArrayList<BlockVector>();
        aVectors.add(a1);
        aVectors.add(a2);
        aVectors.add(a3);
        aVectors.add(a4);
        aVectors.add(a5);
        aVectors.add(a6);
        Shape a = new Shape(0, 0, 0, aVectors);

        BlockVector b1 = new BlockVector(0, 1, 0);
        BlockVector b2 = new BlockVector(0, 1, 1);
        BlockVector b3 = new BlockVector(1, 0, 0);
        BlockVector b4 = new BlockVector(2, 0, 0);
        BlockVector b5 = new BlockVector(2, 1, 0);
        ArrayList<BlockVector> bVectors = new ArrayList<BlockVector>();
        bVectors.add(b1);
        bVectors.add(b2);
        bVectors.add(b3);
        bVectors.add(b4);
        bVectors.add(b5);
        Shape b = new Shape(0, 0, 0, bVectors);


        BlockVector c1 = new BlockVector(1, 0, 0);
        BlockVector c2 = new BlockVector(0, 0, 1);
        BlockVector c3 = new BlockVector(0, 1, 1);
        BlockVector c4 = new BlockVector(0, 1, 0);
        BlockVector c5 = new BlockVector(1, 1, 0);
        ArrayList<BlockVector> cVectors = new ArrayList<BlockVector>();
        cVectors.add(c1);
        cVectors.add(c2);
        cVectors.add(c3);
        cVectors.add(c4);
        cVectors.add(c5);
        Shape c = new Shape(0, 0, 0, cVectors);


        BlockVector d1 = new BlockVector(0, 1, 0);
        BlockVector d2 = new BlockVector(0, 2, 0);
        BlockVector d3 = new BlockVector(1, 0, 0);
        BlockVector d4 = new BlockVector(1, 0, 1);
        ArrayList<BlockVector> dVectors = new ArrayList<BlockVector>();
        dVectors.add(d1);
        dVectors.add(d2);
        dVectors.add(d3);
        dVectors.add(d4);
        Shape d = new Shape(0, 0, 0, dVectors);


        BlockVector e1 = new BlockVector(0, 1, 0);
        BlockVector e2 = new BlockVector(1, 0, 0);
        BlockVector e3 = new BlockVector(1, 0, 1);
        ArrayList<BlockVector> eVectors = new ArrayList<BlockVector>();
        eVectors.add(e1);
        eVectors.add(e2);
        eVectors.add(e3);
        Shape e = new Shape(0, 0, 0, eVectors);


        BlockVector f1 = new BlockVector(0, 1, 0);
        BlockVector f2 = new BlockVector(1, 0, 0);
        BlockVector f3 = new BlockVector(2, 0, 0);
        ArrayList<BlockVector> fVectors = new ArrayList<BlockVector>();
        fVectors.add(f1);
        fVectors.add(f2);
        fVectors.add(f3);
        Shape f = new Shape(0, 0, 0, fVectors);

        System.out.println("\nFirst, we find the possible placements of every shape in an empty 4X4X2 field.");

        ArrayList<Shape> aVariations = a.getAllVariations();
        ArrayList<Shape> bVariations = b.getAllVariations();
        ArrayList<Shape> cVariations = c.getAllVariations();
        ArrayList<Shape> dVariations = d.getAllVariations();
        ArrayList<Shape> eVariations = e.getAllVariations();
        ArrayList<Shape> fVariations = f.getAllVariations();

        System.out.println("  A: " + aVariations.size() + " possible placements");
        System.out.println("  B: " + bVariations.size() + " possible placements");
        System.out.println("  C: " + cVariations.size() + " possible placements");
        System.out.println("  D: " + dVariations.size() + " possible placements");
        System.out.println("  E: " + eVariations.size() + " possible placements");
        System.out.println("  F: " + fVariations.size() + " possible placements\n");

        ArrayList<Block> possA = new ArrayList<Block>();
        for (Shape s : aVariations) {
            Block block = new Block();
            if (block.addShape(s)) possA.add(block);
        }

        System.out.println("Next, we find the number of ways we can place shape A and shape B together...");
        ArrayList<Block> possAB = new ArrayList<Block>();
        for (Block block : possA) {
            for (Shape shape : bVariations) {
                Block newBlock = new Block(block);
                if (newBlock.addShape(shape)) possAB.add(newBlock);
            }
        }
        System.out.println("There are " + possAB.size() + " ways to combine A and B\n");

        System.out.println("Next, we find the number of ways we can place shape ABC...");
        ArrayList<Block> possABC = new ArrayList<Block>();
        for (Block block : possAB) {
            for (Shape shape : cVariations) {
                Block newBlock = new Block(block);
                if (newBlock.addShape(shape)) possABC.add(newBlock);
            }
        }
        System.out.println("There are " + possABC.size() + " ways to combine ABC\n");

        System.out.println("Next, we find the number of ways we can place shape ABCD...");
        ArrayList<Block> possABCD = new ArrayList<Block>();
        for (Block block : possABC) {
            for (Shape shape : dVariations) {
                Block newBlock = new Block(block);
                if (newBlock.addShape(shape)) possABCD.add(newBlock);
            }
        }
        System.out.println("There are " + possABCD.size() + " ways to combine ABCD\n");

        System.out.println("Next, we find the number of ways we can place shape ABCDE...");
        ArrayList<Block> possABCDE = new ArrayList<Block>();
        for (Block block : possABCD) {
            for (Shape shape : eVariations) {
                Block newBlock = new Block(block);
                if (newBlock.addShape(shape)) possABCDE.add(newBlock);
            }
        }
        System.out.println("There are " + possABCDE.size() + " ways to combine ABCDE\n");

        System.out.println("Next, we find the number of ways we can place shape ABCDE...");
        ArrayList<Block> possABCDEF = new ArrayList<Block>();
        for (Block block : possABCDE) {
            for (Shape shape : fVariations) {
                Block newBlock = new Block(block);
                if (newBlock.addShape(shape)) possABCDEF.add(newBlock);
            }
        }
        System.out.println("There are " + possABCDEF.size() + " ways to combine ABCDEF.\nAs they are also mirrors of each other, we can choose one arbitrarily:\n");
        System.out.println("Shape A:\n" +  possABCDEF.get(0).shapes.get(0));
        System.out.println("Shape B:\n" +  possABCDEF.get(0).shapes.get(1));
        System.out.println("Shape C:\n" +  possABCDEF.get(0).shapes.get(2));
        System.out.println("Shape D:\n" +  possABCDEF.get(0).shapes.get(3));
        System.out.println("Shape E:\n" +  possABCDEF.get(0).shapes.get(4));
        System.out.println("Shape F:\n" +  possABCDEF.get(0).shapes.get(5));


        long endTime = System.nanoTime();
        long elapsedTime = (endTime - startTime) / 1_000_000; 
        System.out.println("\nExecution time: " + elapsedTime + " milliseconds\n");

    }
    
}
