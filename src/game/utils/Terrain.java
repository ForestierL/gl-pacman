package game.utils;

import engine.graphics.Orientation;

public class Terrain {

    public static Point getPlayer(char[][] terrain) {
        Point p = null;
        for (int y = 0; y < terrain.length; y++) {
            for (int x = 0; x < terrain[y].length; x++) {

                if (terrain[y][x] == 'P')
                    p = new Point(y, x);
            }
        }
        return p;
    }

    public static Orientation getShortestOrientation(char[][] terrain, int x, int y){

        Orientation res = Orientation.NONE;

        int min = terrain.length*terrain[0].length;
        copy(terrain, x, y);

        if(x>0 && terrain[x-1][y] != 1) {
            int north = explorePath(terrain, x - 1, y);
            if (north != -1 && min > north)
                res =  Orientation.NORTH;
                min = Math.min(north, min);
        }
        if(x>0 && terrain[x+1][y] != 1) {
            int south = explorePath(terrain, x - 1, y);
            if (south != -1 && min > south)
                res =  Orientation.SOUTH;
                min = Math.min(south, min);
        }
        if(y>0 && terrain[x][y-1] != 1) {
            int west = explorePath(terrain, x - 1, y);
            if (west != -1 && min > west)
                res =  Orientation.WEST;
                min = Math.min(west, min);
        }
        if(y>0 && terrain[x][y+1] != 1) {
            int east = explorePath(terrain, x - 1, y);
            if (east != -1 && min > east)
                res =  Orientation.EAST;
        }

        return res;
    }
    private static int explorePath(char[][] terrain, int x, int y){

        if(terrain[x][y] == 'P')
            return 1;

        int min = terrain.length*terrain[0].length;
        copy(terrain, x, y);

        if(x>0 && terrain[x-1][y] != 1) {
            int n = explorePath(terrain, x - 1, y);
            if (n != -1)
                min = Math.min(n, min);
        }
        if(x>0 && terrain[x+1][y] != 1) {
            int s = explorePath(terrain, x - 1, y);
            if (s != -1)
                min = Math.min(s, min);
        }
        if(y>0 && terrain[x][y-1] != 1) {
            int w = explorePath(terrain, x - 1, y);
            if (w != -1)
                min = Math.min(w, min);
        }
        if(y>0 && terrain[x][y+1] != 1) {
            int w = explorePath(terrain, x - 1, y);
            if (w != -1)
                min = Math.min(w, min);
        }


        if(min == terrain.length*terrain[0].length)
            return -1;

        return min+1;
    }

    public static char[][] copy(char[][] terrain, int x, int y){

        char[][] copyTerrain = terrain;
        copyTerrain[x] = new char[terrain[x].length];

        for(int i = 0; i < terrain[x].length;i++){
            copyTerrain[x][i] = terrain[x][i];
        }

        terrain = copyTerrain;
        terrain[x][y] = '1';
        return terrain;

    }
}
