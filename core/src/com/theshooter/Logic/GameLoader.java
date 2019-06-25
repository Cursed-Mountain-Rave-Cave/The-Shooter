package com.theshooter.Logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.util.*;
import java.util.Map;

public class GameLoader {
    private File floor, walls, environment, enemies, player;
    private int[] floorColors, wallsColors, environmentColors, enemiesColors, playerColors;

    public void load(String name) throws IOException {
        initColors();
        initTxtFiles(Gdx.files.getLocalStoragePath() + "levels\\" + name);

        loadFloor(name);
        loadObjects(name);
    }

    private BufferedImage getImage(String name) {
        try {
            return ImageIO.read(new File(Gdx.files.getLocalStoragePath() + name));
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
            Gdx.app.exit();
        }
        return null;
    }

    private void initColors() {
        BufferedImage img;
        int width;

        img = getImage("textures_colors\\floor.png");
        width = img.getWidth();
        floorColors = new int[width];
        for(int j = 0; j < width; ++j)
            floorColors[j] = img.getRGB(j, 0);

        img = getImage("textures_colors\\walls.png");
        width = img.getWidth();
        wallsColors = new int[width];
        for(int j = 0; j < width; ++j)
            wallsColors[j] = img.getRGB(j, 0);

        img = getImage("textures_colors\\environment.png");
        width = img.getWidth();
        environmentColors = new int[width];
        for(int j = 0; j < width; ++j)
            environmentColors[j] = img.getRGB(j, 0);

        img = getImage("textures_colors\\enemies.png");
        width = img.getWidth();
        enemiesColors = new int[width];
        for(int j = 0; j < width; ++j)
            enemiesColors[j] = img.getRGB(j, 0);

        img = getImage("textures_colors\\player.png");
        width = img.getWidth();
        playerColors = new int[width];
        for(int j = 0; j < width; ++j)
            playerColors[j] = img.getRGB(j, 0);
    }

    private BufferedImage initLevel(String levelName, String fileName) {
        try {
            BufferedImage img = ImageIO.read(new File(Gdx.files.getLocalStoragePath() + "levels\\" + levelName + "\\" + fileName));
            return img;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void initTxtFiles(String name) {
        floor = new File(name, "floor.txt");
        walls = new File(name, "walls.txt");
        environment = new File(name, "environment.txt");
        enemies = new File(name, "enemies.txt");
        player = new File(name, "player.txt");

        File[] files = { floor, walls, environment, enemies, player };
        for(File tmp : files)
            if(!tmp.exists()) {
                try { tmp.createNewFile(); }
                catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
    }

    private Map<Integer, Character> setAndWriteMap(FileOutputStream file,Character c) throws  IOException {
        Map<Integer, Character> symbols = new TreeMap<>();
        for(int i = 0; i < floorColors.length; ++i) {
            file.write((c + " " + (i + 1) + System.lineSeparator()).getBytes());
            symbols.put(floorColors[i], c++);
        }
        file.write(("map" + System.lineSeparator()).getBytes());
        return symbols;
    }

    private String checkFloorColor(int color, Map<Integer, Character> symbols) {
        for(int i = 0; i < floorColors.length; ++i)
           if(floorColors[i] == color)
               return symbols.get(color) + "";
        System.out.println("Unknown symbol");
        return "\"";
    }

    private void loadFloor(String name) throws IOException {
        FileOutputStream fout = new FileOutputStream(floor);

        char c = 33;
        Map<Integer, Character> symbols = setAndWriteMap(fout, c);

        BufferedImage img = initLevel(name, "floor.png");
        int height = img.getHeight(), width = img.getWidth();

        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {
                int color = img.getRGB(j, i);
                fout.write(checkFloorColor(color, symbols).getBytes());
            }
            fout.write(System.lineSeparator().getBytes());
        }
    }

    private boolean[][] objectsMap;
    private void loadObjects(String name) throws IOException {
        FileOutputStream wallsOut = new FileOutputStream(walls);
        FileOutputStream environmentOut = new FileOutputStream(environment);
        FileOutputStream enemiesOut = new FileOutputStream(enemies);
        FileOutputStream playerOut = new FileOutputStream(player);

        BufferedImage img = initLevel(name, "objects.png");
        int height = img.getHeight(), width = img.getWidth();
        StringBuilder output = new StringBuilder();
        Integer fileOut;

        objectsMap = new boolean[width][height];


        for(int i = 0; i < height; ++i) {
            for(int j = 0; j < width; ++j) {

                if(objectsMap[j][i]) continue;

                int color = img.getRGB(j, i);
                fileOut = checkTile(img, output, color, j, i);
                switch (fileOut) {
                    case 1:
                        wallsOut.write((output.toString() + System.lineSeparator()).getBytes());
                        output = new StringBuilder();
                        break;
                    case 2:
                        environmentOut.write((output.toString() + System.lineSeparator()).getBytes());
                        output = new StringBuilder();
                        break;
                    case 3:
                        enemiesOut.write((output.toString() + System.lineSeparator()).getBytes());
                        output = new StringBuilder();
                        break;
                    case 4:
                        playerOut.write((output.toString() + System.lineSeparator()).getBytes());
                        output = new StringBuilder();
                        break;
                        default:
                            break;
                }
            }
        }
    }

    private int checkTile(BufferedImage img, StringBuilder output, int color, int x, int y) {
        output.append(checkWallColor(img, color, x, y));
        if(!output.toString().isEmpty())
            return 1;

        output.append(checkEnvironmentColor(img, color, x, y));
        if(!output.toString().isEmpty())
            return 2;

        output.append(checkEnemyColor(color, x, y));
        if(!output.toString().isEmpty())
            return 3;

        output.append(checkPlayerColor(color, x, y));
        if(!output.toString().isEmpty())
            return 4;
        return 0;
    }

    private String checkWallColor(BufferedImage img, int color, Integer x, Integer y) {
        MyInteger endX = new MyInteger(x);
        MyInteger endY = new MyInteger(y);
        if(wallsColors[0] == color && !objectsMap[x][y]) {
            System.out.println("Before = " + x + " " + y);
            wallBFS(img, color, endX, endY);
            System.out.println("After = " + x + " " + y);
            return "placeWall " + y + " " + x + " " + (endY.value + 1) + " " + (endX.value + 1);
        }
        if(wallsColors[1] == color && !objectsMap[x][y]) {
            wallBFS(img, color, endX, endY);
            return "placeInvisibleWall " + y + " " + x + " " + (endY.value + 1) + " " + (endX.value + 1);
        }
        return "";
    }

    private String checkEnvironmentColor(BufferedImage img, int color, int x, int y) {
        if(environmentColors[0] == color)
            return "placeVase " + y + " " + x;
        if(environmentColors[1] == color) {
            bfs(img, color, new MyInteger(x), new MyInteger(y));
            return "placeTend " + y + " " + x;
        }
        if(environmentColors[2] == color) {
            bfs(img, color, new MyInteger(x), new MyInteger(y));
            return "placeGate " + y + " " + x;
        }
        if(environmentColors[3] == color)
            return "placeHookah " + y + " " + x;
        if(environmentColors[4] == color)
            return "placePalm " + y + " " + x;
        if(environmentColors[5] == color)
            return "placeWoman " + y + " " + x;
        if(environmentColors[6] == color) {
            bfs(img, color, new MyInteger(x), new MyInteger(y));
            return "placeHome " + y + " " + x;
        }
        return "";
    }

    private String checkEnemyColor(int color, int x, int y) {
        if(enemiesColors[0] == color)
            return "spawnTrain " + y + " " + x;
        if(enemiesColors[1] == color)
            return "spawnKeanu " + y + " " + x;
        if(enemiesColors[2] == color)
            return "spawnPlane " + y + " " + x;
        if(enemiesColors[3] == color)
            return "spawnTrain " + y + " " + x;
        if(enemiesColors[4] == color)
            return "spawnBoss" + y + " " + x;
        return "";
    }

    private String checkPlayerColor(int color, int x, int y) {
        if(playerColors[0] == color) return (y * 50) + " " + (x * 50);
        return "";
    }

    private class coor {
        public int x, y;
        public coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    private void bfs(BufferedImage img, int color, MyInteger x, MyInteger y) {
        Queue<coor> remainingCells = new Queue<>();
        remainingCells.addLast(new coor(x.value, y.value));

        int height = img.getHeight();
        int width = img.getWidth();
        while(!remainingCells.isEmpty()) {
            x.value = remainingCells.first().x;
            y.value = remainingCells.first().y;
            remainingCells.removeFirst();

            objectsMap[x.value][y.value] = true;

            ++x.value;
            ++y.value;
            if(x.value < width && img.getRGB(x.value, y.value) == color)
                remainingCells.addLast(new coor(x.value, y.value));
            if(y.value < height && img.getRGB(x.value, y.value) == color)
                remainingCells.addLast(new coor(x.value, y.value));
        }
    }

    private void wallBFS(BufferedImage img, int color, MyInteger x, MyInteger y) {
        int height = img.getHeight();
        int width = img.getWidth();

        if(x.value < width && img.getRGB(x.value, y.value) == color)
            while(x.value < width && img.getRGB(x.value, y.value) == color) {
                objectsMap[x.value++][y.value] = true;
            }
        else
            while(y.value < height && img.getRGB(x.value, y.value) == color)
                objectsMap[x.value][y.value++] = true;
    }

    private class MyInteger {
        public Integer value;
        public MyInteger(int value) { this.value = value; }
    }
}