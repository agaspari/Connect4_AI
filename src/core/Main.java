package core;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isHumanTurn = true;
        Scanner scanner = new Scanner(System.in);

        Grid grid = new Grid();
        AI ai = new AI(grid, 6);
        AI ai2 = new AI(grid, 8);

        System.out.println(grid);
        while(true) {
            if (isHumanTurn) {
                System.out.print("Enter a Line: ");
                String userInput = scanner.nextLine();
                int lineNumber = Integer.parseInt(userInput);

                boolean hasWon = grid.determineWin(lineNumber, PlayerType.HUMAN);
                if (hasWon) {
                    grid.insert(lineNumber, PlayerType.HUMAN);
                    System.out.println("Human has Won!");
                    System.out.println(grid);
                    break;
                }

                grid.insert(lineNumber, PlayerType.HUMAN);
//                int move = ai2.determineMove().getLineNum();
//                boolean hasWon = grid.determineWin(move, PlayerType.HUMAN);
//
//                if (hasWon) {
//                    grid.insert(move, PlayerType.HUMAN);
//                    System.out.println("Computer with lower depth has Won!");
//                    System.out.println(grid);
//                    break;
//                }
//
//                grid.insert(move, PlayerType.HUMAN);
            } else {
                int move = ai.determineMove().getLineNum();
                boolean hasWon = grid.determineWin(move, PlayerType.CPU);

                if (hasWon) {
                    grid.insert(move, PlayerType.CPU);
                    System.out.println("Computer with higher depth has has Won!");
                    System.out.println(grid);
                    break;
                }

                grid.insert(move, PlayerType.CPU);
            }
            System.out.println("Human Heuristic Value: " + grid.calculateHeuristic(PlayerType.HUMAN));
            System.out.println("Computer Heuristic Value: " + grid.calculateHeuristic(PlayerType.CPU));

            isHumanTurn = !isHumanTurn;
            System.out.println(grid);
        }
    }
}
