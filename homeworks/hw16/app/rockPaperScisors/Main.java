package hw16.app.rockPaperScisors;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=====Rock Paper Scissors game starts=====");
        //Scanner sc = new Scanner(System.in);

        System.out.println("Please enter your name:");
        String name = sc.nextLine();
        Player player = new Player(name);

        System.out.println("Please enter number of games:");
        int totalGames = sc.nextInt();
        sc.nextLine();

        System.out.println(String.format("%s wants to play %s games", player.getName(), totalGames));

        GameService gs = new GameService();
        int counter = 1;
        int computerScore = 0;
        do {
            System.out.println("\n=============");
            System.out.println(String.format("Game %d of %d", counter, totalGames));

            Move playerMove = playerMoveStart();
            Move computerMove = gs.getComputerMove();

            System.out.println("========");
            System.out.println("Player move:    " + playerMove);
            System.out.println("Computer move:  " + computerMove);

            GameResult result = gs.getResult(playerMove, computerMove);
            computerScore = processRoundResult(result, player, computerScore);

            counter++;
        } while (counter <= totalGames);

        printFinalStatistics(player, computerScore);

    }

    private static Move playerMoveStart() {
        Move playerMove = null;
        while (playerMove == null) {
            System.out.printf("Make your move:%n [1] ROCK%n [2] PAPER%n [3] SCISSORS%n");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:  playerMove = Move.ROCK; break;
                case 2: playerMove = Move.PAPER; break;
                case 3: playerMove = Move.SCISSORS; break;
                default: System.out.println("Invalid choice. Try again."); break;
            }
        }
        return playerMove;
    }

    private static int processRoundResult(GameResult result, Player player, int computerScore) {
        if (result == GameResult.PLAYER) {
            System.out.println("~~~~~Result: PLAYER WINS!~~~~~");
            player.addScore();
        } else if (result == GameResult.COMPUTER) {
            System.out.println("~~~~~Result: COMPUTER WINS!~~~~~");
            computerScore++;
        } else {
            System.out.println("~~~~~Result: DRAW!~~~~~");
        }
        return computerScore;
    }

    private static void printFinalStatistics(Player player, int computerScore) {
        System.out.println("\n======= THE END =======");
        System.out.println("Final Score:");
        System.out.println(player.getName() + " : " + player.getScore());
        System.out.println("Computer : " + computerScore);

        if (player.getScore() > computerScore) {
            System.out.println("CONGRATULATIONS! YOU WON THE MATCH!");
        } else if (player.getScore() < computerScore) {
            System.out.println("GAME OVER! COMPUTER WON THE MATCH!");
        } else {
            System.out.println("THE MATCH IS A DRAW!");
        }
    }
}