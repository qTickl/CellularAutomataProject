package com.qtickl.javafx_1;

public class CellularAutomata {
    private Rule rule;
    public static byte[] initRow;

    public CellularAutomata(Rule r, byte[] startRow) {
        this.rule = r;

        if (startRow.length < 3) {
            throw new Error("startRow must have at least 3 cells");
        }
        checkRow(startRow);

        this.initRow = startRow;
    }

    /**
     * Ensures that a row contains only 0's and 1's; raises an error if it doesn't
     *
     * @param row any byte[]
     */
    public void checkRow(byte[] row) {
        for (byte b : row) {
            if (b != 0 && b != 1) {
                throw new Error("Row must contain 0's and positive 1's only");
            }
        }
    }

    /**
     * @param row a valid byte[]
     * @return a grid representing a completed cellular automata model
     */
    public byte[] iterateOnce(byte[] row) {
        checkRow(row);
        byte[] result = new byte[row.length];
        int l = row.length - 1;
        //calculates the first and last cells of the next row
        result[0] = rule.getNext(new byte[]{row[l], row[0], row[1]});
        result[l] = rule.getNext(new byte[]{row[l], row[0], row[1]});

        //calculates the remaining section
        for (int i = 1; i < l; i++) {
            result[i] = rule.getNext(new byte[]{row[i - 1], row[i], row[i + 1]});
        }
        return result;
    }

    /**
     * @param iterations the number of subsequent rows to be generated
     * @return a grid representing a completed cellular automata model
     */
    public byte[][] iterate(int iterations) {
        byte[][] result = new byte[iterations + 1][initRow.length];
        result[0] = initRow;
        for (int i = 1; i <= iterations; i++) {
            result[i] = iterateOnce(result[i - 1]);
        }
        return result;
    }

    /**
     * @param row byte[]
     * @return the string representation of byte[] row
     */
    public String toString(byte[] row) {
        String result = "";
        for (byte b : row) {
            result += b;
        }
        return result;
    }

}