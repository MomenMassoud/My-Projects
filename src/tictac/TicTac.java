package tictac;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;

public class TicTac {

    public static String player;
    public static String comp;
    public static String player1 = "X";
    public static String player2 = "O";
    public static String[] bord = new String[9];
    public static Scanner input = new Scanner(System.in);
    public static int state;

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        start_game();
    }

    public static void start_game() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("setto_ana.wav");
        AudioInputStream audiostream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audiostream);
        clip.start();
        game();
        while (true) {
            System.out.println("Choose From This List!");
            System.out.println("1)Play Again!");
            System.out.println("2)Exit!");
            int answer = input.nextInt();
            if (answer == 1) {
                game();
            } else {
                break;
            }
        }
    }

    public static int cheeck(int x) {
        while (true) {
            if (x == 1 || x == 2) {
                return x;
            } else {
                System.out.println("Error!");
                System.out.println("Try Again");
                System.out.println("Choose From This list:");
                System.out.println("1)Two Player");
                System.out.println("2)Player VS Computer");
                System.out.println("Enter Your Choose:");
                int again = input.nextInt();
                x = again;
            }
        }
    }

    public static void insert() {
        for (int i = 0; i < 9; i++) {
            bord[i] = "";
        }
    }

    public static void note() {
        System.out.println("-------");
        System.out.println("|1|2|3|");
        System.out.println("-------");
        System.out.println("|4|5|6|");
        System.out.println("-------");
        System.out.println("|7|8|9|");
        System.out.println("-------");
    }

    public static void draw() {
        System.out.println("-------");
        System.out.println("| " + bord[0] + " | " + bord[1] + " | " + bord[2] + " |");
        System.out.println("-------");
        System.out.println("| " + bord[3] + " | " + bord[4] + " | " + bord[5] + " |");
        System.out.println("-------");
        System.out.println("| " + bord[6] + " | " + bord[7] + " | " + bord[8] + " |");
        System.out.println("-------");
    }

    public static boolean win_case() {
        if (bord[0] == bord[1] && bord[0] == bord[2] && bord[0] != "") {
            return true;
        } else if (bord[3] == bord[4] && bord[3] == bord[5] && bord[3] != "") {
            return true;
        } else if (bord[6] == bord[7] && bord[6] == bord[8] && bord[6] != "") {
            return true;
        } else if (bord[0] == bord[3] && bord[0] == bord[6] && bord[0] != "") {
            return true;
        } else if (bord[1] == bord[4] && bord[1] == bord[7] && bord[1] != "") {
            return true;
        } else if (bord[2] == bord[5] && bord[2] == bord[8] && bord[2] != "") {
            return true;
        } else if (bord[0] == bord[4] && bord[0] == bord[8] && bord[0] != "") {
            return true;
        } else if (bord[2] == bord[4] && bord[2] == bord[6] && bord[2] != "") {
            return true;
        }
        return false;
    }

    public static int free(int position) {
        while (true) {
            if (bord[position] == "") {
                break;
            } else {
                System.out.println("Can't add your letter in this position!");
                System.out.println("Try Again!");
                System.out.println("Please Enter Your Position:");
                int x = input.nextInt();
                position = x;
            }
        }
        return position;
    }

    public static void in_player1() {
        System.out.println("Turn Of Player 1!");
        System.out.println("Please Enter Your Position:");
        int position = input.nextInt();
        position = free(position - 1);
        bord[position] = player1;
    }

    public static void in_player2() {
        System.out.println("Turn Of Player 2!");
        System.out.println("Please Enter Your Position:");
        int position = input.nextInt();
        position = free(position - 1);
        bord[position] = player2;
    }

    public static void heristic() {
        int m = 0, n = 0;
        String[] copy = new String[9];
        for (int i = 0; i < 9; i++) {
            copy[i] = "";
        }
        for (int i = 0; i < 9; i++) {
            copy[i] = bord[i];
        }
        for (int i = 0; i < 9; i++) {
            if (copy[i] == "") {
                copy[i] = "X";
            }
        }
        if (copy[0] == copy[1] && copy[0] == copy[2] && copy[0] != "") {
            m++;
        }
        if (copy[3] == copy[4] && copy[3] == copy[5] && copy[3] != "") {
            m++;
        }
        if (copy[6] == copy[7] && copy[6] == copy[8] && copy[6] != "") {
            m++;
        }
        if (copy[0] == copy[3] && copy[0] == copy[6] && copy[0] != "") {
            m++;
        }
        if (copy[1] == copy[4] && copy[1] == copy[7] && copy[1] != "") {
            m++;
        }
        if (copy[2] == copy[5] && copy[2] == copy[8] && copy[2] != "") {
            m++;
        }
        if (copy[0] == copy[4] && copy[0] == copy[8] && copy[0] != "") {
            m++;
        }
        if (copy[2] == copy[4] && copy[2] == copy[6] && copy[2] != "") {
            m++;
        }
        for (int i = 0; i < 9; i++) {
            copy[i] = "";
        }
        for (int i = 0; i < 9; i++) {
            copy[i] = bord[i];
        }
        for (int i = 0; i < 9; i++) {
            if (copy[i] == "") {
                copy[i] = "O";
            }
        }
        if (copy[0] == copy[1] && copy[0] == copy[2] && copy[0] != "") {
            n++;
        }
        if (copy[3] == copy[4] && copy[3] == copy[5] && copy[3] != "") {
            n++;
        }
        if (copy[6] == copy[7] && copy[6] == copy[8] && copy[6] != "") {
            n++;
        }
        if (copy[0] == copy[3] && copy[0] == copy[6] && copy[0] != "") {
            n++;
        }
        if (copy[1] == copy[4] && copy[1] == copy[7] && copy[1] != "") {
            n++;
        }
        if (copy[2] == copy[5] && copy[2] == copy[8] && copy[2] != "") {
            n++;
        }
        if (copy[0] == copy[4] && copy[0] == copy[8] && copy[0] != "") {
            n++;
        }
        if (copy[2] == copy[4] && copy[2] == copy[6] && copy[2] != "") {
            n++;
        }
        System.out.println("H(n)=" + m + "-" + n + "=" + (m - n));
    }

    public static void game() {
        insert();
        int counter = 0;
        int choose = level();
        System.out.println("Enter State To Calc H(n):");
        state = input.nextInt();
        if (choose == 1) {
            note();
            draw();
            while (true) {
                in_player1();
                draw();
                if (win_case()) {
                    System.out.println("Player 1 Wins!");
                    break;
                }
                in_player2();
                draw();
                if (win_case()) {
                    System.out.println("Player 2 Wins!");
                    break;
                }
                counter++;
                if (state == counter) {
                    heristic();
                }
            }
            if (state > counter) {
                heristic();
            }
        } else {
            System.out.println("Choose From This List:");
            System.out.println("1)X");
            System.out.println("2)O");
            int x = input.nextInt();
            x = cheeck_start(x);
            if (x == 1) {
                player = "X";
                comp = "O";
                while (!win_case()) {
                    playerMove();
                    if(win_case()){
                        System.out.println("Player Wins!");
                        break;
                    }
                    botMove();
                    if(win_case()){
                        break;
                    }
                    counter++;
                    if (state == counter) {
                        heristic();
                    }
                }
                if (state > counter) {
                    heristic();
                }
            } else {
                player = "O";
                comp = "X";
                while (!win_case()) {
                    botMove();
                    if(win_case()){
                        
                        break;
                    }
                    playerMove();
                    if(win_case()){
                        System.out.println("Player Wins!");
                        break;
                    }
                    counter++;
                    if (state == counter) {
                        heristic();
                    }
                }
                if (state > counter) {
                    heristic();
                }
            }
        }
    }

    public static int cheeck_start(int x) {
        int i = 0;
        while (i == 0) {
            if (x == 1 || x == 2) {
                break;
            } else {
                System.out.println("Error!");
                System.out.println("Choose From This List:");
                System.out.println("1)X");
                System.out.println("2)O");
                int xx = input.nextInt();
                x = xx;
            }
        }
        return x;
    }

    public static boolean checkWhichMarkWon(String mark) {
        if (bord[0] == bord[1] && bord[0] == bord[2] && bord[0] == mark) {
            return true;
        } else if (bord[3] == bord[4] && bord[3] == bord[5] && bord[3] == mark) {
            return true;
        } else if (bord[6] == bord[7] && bord[6] == bord[8] && bord[6] == mark) {
            return true;
        } else if (bord[0] == bord[6] && bord[0] == bord[3] && bord[0] == mark) {
            return true;
        } else if (bord[1] == bord[4] && bord[1] == bord[7] && bord[1] == mark) {
            return true;
        } else if (bord[2] == bord[5] && bord[2] == bord[8] && bord[2] == mark) {
            return true;
        } else if (bord[0] == bord[4] && bord[0] == bord[8] && bord[0] == mark) {
            return true;
        } else if (bord[2] == bord[4] && bord[2] == bord[6] && bord[2] == mark) {
            return true;
        } else {
            return false;
        }
    }

    public static int level() {
        System.out.println("Choose From This list:");
        System.out.println("1)Two Player");
        System.out.println("2)Player VS Computer");
        System.out.println("Enter Your Choose:");
        int choose = input.nextInt();
        choose = cheeck(choose);
        return choose;
    }

    public static void playerMove() {
        int position;
        System.out.println("Enter the position:");
        position = input.nextInt();
        position = position - 1;
        insertLetter(player, position);
    }

    public static void botMove() {
        int bestScore = -800;
        int bestMove = 0, score;
        for (int i = 0; i < 9; i++) {
            if (bord[i] == "") {
                bord[i] = comp;
                score = minimax(0, false);
                bord[i] = "";
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }
        }
        insertLetter(comp, bestMove);
    }

    public static int minimax(int depth, boolean isMaximizing) {
        int bestscore, score;
        if (checkWhichMarkWon(comp)) {
            return 1;
        } else if (checkWhichMarkWon(player)) {
            return -1;
        } else if (checkDraw()) {
            return 0;
        }
        if (isMaximizing) {
            bestscore = -800;
            for (int i = 0; i < 9; i++) {
                if (bord[i] == "") {
                    bord[i] = comp;
                    score = minimax(depth + 1, false);
                    bord[i] = "";
                    if (score > bestscore) {
                        bestscore = score;
                    }
                }
            }
            return bestscore;
        } else {
            bestscore = 800;
            for (int i = 0; i < 9; i++) {
                if (bord[i] == "") {
                    bord[i] = player;
                    score = minimax(depth + 1, true);
                    bord[i] = "";
                    if (score < bestscore) {
                        bestscore = score;
                    }
                }
            }
            return bestscore;
        }
    }

    public static void insertLetter(String letter, int position) {
        if (spaceIsFree(position)) {
            bord[position] = letter;
            draw();
            if (checkDraw()) {
                System.out.println("Draw!");
                return;
            }
            if (win_case()) {
                if (letter == comp) {
                    System.out.println("Bot Wins!");
                } else {
                    System.out.println("Player Wins!");
                }
            }
        } else {
            System.out.println("Can't insert there!");
            System.out.println("Please enter new position:");
            int x = input.nextInt();
            position = x;
            insertLetter(letter, position);
        }
    }

    public static boolean spaceIsFree(int position) {
        if (bord[position] == "") {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDraw() {
        for (int i = 0; i < 9; i++) {
            if (bord[i] == "") {
                return false;
            }
        }
        return true;
    }

}
