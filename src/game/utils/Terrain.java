package game.utils;

import java.util.Arrays;

public class Terrain {


    public static Point getPlayer(int[][] terrain) {
        Point p = null;
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[y].length; x++) {

                if (terrain[y][x] == -1)
                    p = new Point(x, y);
            }
        }
        return p;
    }

    public static Direction getShortestDirection2(int[][] terrain1, int x, int y) {


        Direction res = Direction.NONE;

        int[][] terrain = copy(terrain1);

        if (terrain[y][x] == -1)
            return res;

        int min = terrain.length * terrain[0].length;


        if (x > 0 && terrain[y][x - 1] != 1) {
            int[][] terrainW = copy(terrain);
            if (terrainW[y][x - 1] != -1)
                terrainW[y][x - 1] = 1;
            int west = explorePath2(terrainW, x - 1, y, 2);
            if (west != -1 && min > west)
                res = Direction.X_NEGATIVE;
            min = Math.min(west, min);
        }
        if (x <= terrain[0].length && terrain[y][x + 1] != 1) {
            int[][] terrainE = copy(terrain);
            if (terrainE[y][x + 1] != -1)
                terrainE[y][x + 1] = 1;
            int east = explorePath2(terrainE, x + 1, y, 2);
            if (east != -1 && min > east)
                res = Direction.X_POSITIVE;
            min = Math.min(east, min);
        }
        if (y > 0 && terrain[y - 1][x] != 1) {
            int[][] terrainN = copy(terrain);
            if (terrainN[y - 1][x] != -1)
                terrainN[y - 1][x] = 1;
            int north = explorePath2(terrainN, x, y - 1, 2);
            if (north != -1 && min > north)
                res = Direction.Y_NEGATIVE;
            min = Math.min(north, min);

        }
        if (y <= terrain.length && terrain[y + 1][x] != 1) {
            int[][] terrainS = copy(terrain);
            if (terrainS[y + 1][x] != -1)
                terrainS[y + 1][x] = 1;
            int south = explorePath2(terrainS, x, y + 1, 2);
            if (south != -1 && min > south) {
                res = Direction.Y_POSITIVE;
            }
        }

        return res;
    }

    public static Direction getShortestDirection(int[][] terrain1, int origX, int origY, int destX, int destY) {


        Direction res = Direction.NONE;

        int[][] terrain = copy(terrain1);

        if (origX == destX && origY == destY)
            return res;

        int min = terrain.length * terrain[0].length;


        if (origX > 0 && terrain[origY][origX - 1] != 1) {
            int[][] terrainW = copy(terrain);
            if (terrainW[origY][origX - 1] != -1)
                terrainW[origY][origX - 1] = 1;
            int west = explorePath(terrainW, origX - 1, origY, destX, destY, 2);
            if (west != -1 && min > west)
                res = Direction.X_NEGATIVE;
            min = Math.min(west, min);
        }
        if (origX <= terrain[0].length && terrain[origY][origX + 1] != 1) {
            int[][] terrainE = copy(terrain);
            if (terrainE[origY][origX + 1] != -1)
                terrainE[origY][origX + 1] = 1;
            int east = explorePath(terrainE, origX + 1, origY, destX, destY, 2);
            if (east != -1 && min > east)
                res = Direction.X_POSITIVE;
            min = Math.min(east, min);
        }
        if (origY > 0 && terrain[origY - 1][origX] != 1) {
            int[][] terrainN = copy(terrain);
            if (terrainN[origY - 1][origX] != -1)
                terrainN[origY - 1][origX] = 1;
            int north = explorePath(terrainN, origX, origY - 1, destX, destY, 2);
            if (north != -1 && min > north)
                res = Direction.Y_NEGATIVE;
            min = Math.min(north, min);

        }
        if (origY <= terrain.length && terrain[origY + 1][origX] != 1) {
            int[][] terrainS = copy(terrain);
            if (terrainS[origY + 1][origX] != -1)
                terrainS[origY + 1][origX] = 1;
            int south = explorePath(terrainS, origX, origY + 1, destX, destY, 2);
            if (south != -1 && min > south) {
                res = Direction.Y_POSITIVE;
            }
        }

        return res;
    }

    public static int[][] transformMatrix(char[][] terrain) {
        int[][] res = new int[terrain.length][terrain[0].length];
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[0].length; x++) {
                if (terrain[y][x] == '1')
                    res[y][x] = 1;
                if (terrain[y][x] == 'P')
                    res[y][x] = -1;
                if (terrain[y][x] != 'P' && terrain[y][x] != '1')
                    res[y][x] = 0;
            }

        }
        return res;
    }

    public static Direction randomDir(int[][] terrain, int x, int y) {
        double r;
        Direction d;
        do {
            r = 1. + Math.random() * (4);
            d = Direction.values()[(int) r];
        } while ((d == Direction.Y_NEGATIVE && y > 0 && terrain[y - 1][x] == 1) ||
                (d == Direction.Y_POSITIVE && y < terrain.length && terrain[y + 1][x] == 1) ||
                (d == Direction.X_NEGATIVE && x > 0 && terrain[y][x - 1] == 1) ||
                (d == Direction.X_POSITIVE && x < terrain[0].length && terrain[y][x + 1] == 1));
        return d;
    }

    private static int explorePath2(int[][] terrain, int x, int y, int cpt) {

        if (terrain[y][x] == -1)
            return 1;
        terrain[y][x] = cpt;

        int noPath = 0;
        int box;
        box = terrain[y][x - 1];
        if (x > 0 && box != 1 && (box >= cpt || box == 0)) {

            int r = explorePath2(terrain, x - 1, y, cpt + 1);
            if (r == -1)
                noPath += 1;

        }
        box = terrain[y][x + 1];
        if (x < terrain[0].length && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath2(terrain, x + 1, y, cpt + 1);
            if (r == -1)
                noPath += 1;

        }
        box = terrain[y - 1][x];
        if (y > 0 && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath2(terrain, x, y - 1, cpt + 1);
            if (r == -1)
                noPath += 1;
        }
        box = terrain[y + 1][x];
        if (y < terrain.length && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath2(terrain, x, y + 1, cpt + 1);
            if (r == -1)
                noPath += 1;
        }


        if (noPath == 4 && cpt != 2) {
            terrain[y][x] = 1;
            return -1;
        }
        Point p = Terrain.getPlayer(terrain);

        if (cpt == 2) {
            int res = terrain.length * terrain[0].length;
            if (x > 0 && terrain[p.getY()][p.getX() - 1] != 1 && terrain[p.getY()][p.getX() - 1] != 0)
                res = Math.min(res, terrain[p.getY()][p.getX() - 1]);

            if (x <= terrain[0].length && terrain[p.getY()][p.getX() + 1] != 1 && terrain[p.getY()][p.getX() + 1] != 0)
                res = Math.min(res, terrain[p.getY()][p.getX() + 1]);

            if (y > 0 && terrain[p.getY() - 1][p.getX()] != 1 && terrain[p.getY() - 1][p.getX()] != 0)
                res = Math.min(res, terrain[p.getY() - 1][p.getX()]);

            if (y <= terrain.length && terrain[p.getY() + 1][p.getX()] != 1 && terrain[p.getY() + 1][p.getX()] != 0)
                res = Math.min(res, terrain[p.getY() + 1][p.getX()]);

            return res;
        }
        return 1;

    }

    private static int explorePath(int[][] terrain, int x, int y, int destX, int destY, int cpt) {

        if (x == destX && y == destY)
            return 1;
        terrain[y][x] = cpt;

        int noPath = 0;
        int box;
        box = terrain[y][x - 1];
        if (x > 0 && box != 1 && (box >= cpt || box == 0)) {

            int r = explorePath(terrain, x - 1, y, destX, destY, cpt + 1);
            if (r == -1)
                noPath += 1;

        }
        box = terrain[y][x + 1];
        if (x < terrain[0].length && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath(terrain, x + 1, y, destX, destY, cpt + 1);
            if (r == -1)
                noPath += 1;

        }
        box = terrain[y - 1][x];
        if (y > 0 && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath(terrain, x, y - 1, destX, destY, cpt + 1);
            if (r == -1)
                noPath += 1;
        }
        box = terrain[y + 1][x];
        if (y < terrain.length && box != 1 && (box >= cpt || box == 0)) {
            int r = explorePath(terrain, x, y + 1, destX, destY, cpt + 1);
            if (r == -1)
                noPath += 1;
        }


        if (noPath == 4 && cpt != 2) {
            terrain[y][x] = 1;
            return -1;
        }

        if (cpt == 2) {
            int res = terrain.length * terrain[0].length;
            if (x > 0 && terrain[destY][destX - 1] != 1 && terrain[destY][destX - 1] != 0)
                res = Math.min(res, terrain[destY][destX - 1]);

            if (x <= terrain[0].length && terrain[destY][destX + 1] != 1 && terrain[destY][destX + 1] != 0)
                res = Math.min(res, terrain[destY][destX + 1]);

            if (y > 0 && terrain[destY - 1][destX] != 1 && terrain[destY - 1][destX] != 0)
                res = Math.min(res, terrain[destY - 1][destX]);

            if (y <= terrain.length && terrain[destY + 1][destX] != 1 && terrain[destY + 1][destX] != 0)
                res = Math.min(res, terrain[destY + 1][destX]);

            return res;
        }
        return 1;

    }

    public static int[][] copy(int[][] terrain) {

        int[][] res = new int[terrain.length][terrain[0].length];
        for (int i = 0; i < terrain.length; i++) {
            res[i] = Arrays.copyOf(terrain[i], terrain[i].length);
        }

        return res;

    }

}
