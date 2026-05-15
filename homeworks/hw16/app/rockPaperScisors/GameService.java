package hw16.app.rockPaperScisors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GameService {

    private Random random;
    private Map<Move, Move> gameRules; // словкник: 1  - б'є , 2 - кого б'ють

    public GameService() {
        this.random = new Random();
        this.gameRules = new HashMap<>();
        gameRules.put(Move.ROCK, Move.SCISSORS); // Камінь б'є Ножиці
        gameRules.put(Move.PAPER, Move.ROCK);    // Папір б'є Камінь
        gameRules.put(Move.SCISSORS, Move.PAPER); // Ножиці б'ють Папір
    }

    public Move getComputerMove() {
        List<Move> movesList = List.of(Move.values());
        int randomIndex = random.nextInt(movesList.size());
        return movesList.get(randomIndex);
    }

    public GameResult getResult(Move playerMove, Move computerMove) {
        if (playerMove == computerMove) {
            return GameResult.DRAW;
        }
        if (gameRules.get(playerMove) == computerMove) {
            return GameResult.PLAYER;
        }
        return GameResult.COMPUTER;
    }
}