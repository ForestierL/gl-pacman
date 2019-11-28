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

    public static Direction getShortestDirection(char[][] terrain, int x, int y){

        Direction res = Direction.Y_NEGATIVE;

        int min = terrain.length*terrain[0].length;
        terrain = copy(terrain, x, y);

        if(x>0 && terrain[y][x-1] != '1') {
            int north = explorePath(terrain, x - 1, y);
            if (north != -1 && min > north)
                res =  Direction.X_NEGATIVE;
            min = Math.min(north, min);
        }
        if(x<=terrain[0].length && terrain[y][x+1] != '1') {
            int south = explorePath(terrain, x + 1, y);
            if (south != -1 && min > south)
                res =  Direction.X_POSITIVE;
            min = Math.min(south, min);
        }
        if(y>0 && terrain[y-1][x] != '1') {
            int west = explorePath(terrain, x, y-1);
            if (west != -1 && min > west)
                res =  Direction.Y_NEGATIVE;
            min = Math.min(west, min);

        }
        if(y<=terrain.length && terrain[y+1][x] != '1') {
            int east = explorePath(terrain, x, y+1);
            if (east != -1 && min > east) {
                res = Direction.Y_POSITIVE;
                System.out.println(res);
            }
        }

        return res;
    }
    private static int explorePath(char[][] terrain, int x, int y){

        if(terrain[y][x] == 'P')
            return 1;

        int min = terrain.length*terrain[0].length;
        terrain = copy(terrain, x, y);


        if(x>0 && terrain[y][x-1] != '1') {
            int n = explorePath(terrain, x - 1, y);
            if (n != -1)
                min = Math.min(n, min);
        }
        if(x<=terrain[0].length && terrain[y][x+1] != '1') {
            int s = explorePath(terrain, x + 1, y);
            if (s != -1)
                min = Math.min(s, min);
        }
        if(y>0 && terrain[y-1][x] != '1') {
            int w = explorePath(terrain, x, y-1);
            if (w != -1)
                min = Math.min(w, min);
        }
        if(y<=terrain.length && terrain[y+1][x] != '1') {
            int w = explorePath(terrain, x, y+1);
            if (w != -1)
                min = Math.min(w, min);
        }


        if(min == terrain.length*terrain[0].length)
            return -1;

        return min+1;
    }
    public static void printTerrain(char[][] terrain, int x, int y)
    {
        for(int y1 = 0; y1 < terrain.length; y1++)
        {
            for(int x1 = 0; x1 < terrain[0].length; x1++)
            {
                System.out.print(terrain[y1][x1]);
            }
            System.out.println();
        }
    }
    public static char[][] copy(char[][] terrain, int x, int y) {


        /*char[][] copyTerrain = terrain;
        copyTerrain[x] = new char[terrain[x].length];

        for(int i = 0; i < terrain[x].length;i++){
            copyTerrain[x][i] = terrain[x][i];
        }*/
        char[][] res = new char[terrain.length][terrain[0].length];
        for(int i=0; i< terrain.length; i++)
            res[i] = terrain[i];

        res[y] = new char[terrain[0].length];
        for(int i=0; i< terrain[0].length; i++)
            res[y][i] = terrain[y][i];

        res[y][x]='1';
        //char[][] copyTerrain = terrain.clone();
        //copyTerrain[x][y] = '1';
        return res;

    }

}
