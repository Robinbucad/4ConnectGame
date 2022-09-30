package com.Connectgame.application;

import com.Connectgame.domain.FourConnect;
import com.Connectgame.domain.Game;
import com.Connectgame.domain.GameStatus;
import com.Connectgame.domain.Player;
import com.Connectgame.infraestructure.controller.dto.input.PlayerInputDTO;
import com.Connectgame.infraestructure.controller.dto.output.GameOutputDTO;
import com.Connectgame.infraestructure.repository.BoardRepository;
import com.Connectgame.infraestructure.repository.PlayersRepository;
import com.Connectgame.model.GamePlay;
import com.Connectgame.utils.exception.NotFoundException;
import com.Connectgame.utils.exception.UnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private PlayersRepository playersRepository;

    public Mono<GameOutputDTO> createGame(PlayerInputDTO player){
        Game game = new Game();

        return Mono.just(game)
                .flatMap(g -> {
                    g.setBoard(new int[7][6]);
                    g.setPlayer1(player.getName());
                    g.setGameStatus(GameStatus.NEW);
                    return boardRepository.save(g);
                })
                .flatMap(req -> playersRepository.save(new Player(player)))
                .map(p -> new GameOutputDTO(new Game(player)));

    }

    public Flux<Game> getGames(){
        return boardRepository.findAll();
    }

    public Mono<Object> connectToGame(PlayerInputDTO player2, int gameId){
        return boardRepository.findById(gameId)
                .switchIfEmpty(Mono.error(new NotFoundException("Partida no existe")))
                .flatMap(g -> {
                    g.setPlayer2(player2.getName());
                    g.setGameStatus(GameStatus.IN_PROGRESS);
                    return boardRepository.save(g);
                })
                .flatMap(p -> playersRepository.findByName(player2.getName())
                        .switchIfEmpty(playersRepository.save(new Player(player2))));
    }

    public Mono<Game> gameplay(GamePlay gamePlay){

        return boardRepository.findById(gamePlay.getGameId())
                .switchIfEmpty(Mono.error(new NotFoundException("Partido no existe")))
                .flatMap( g -> {
                    if (g.getGameStatus().equals(GameStatus.NEW) || g.getGameStatus().equals(GameStatus.IN_PROGRESS)) {

                        int[][] boardGame = g.getBoard();
                        int counterIndex = 0;
                        for (int i = 0; i < 7; i++) {
                            for (int j = 0; j < 6; j++) {

                                if (boardGame[gamePlay.getColumn()][j] == 0) {
                                    counterIndex = j;
                                }

                            }
                        }
                        boardGame[gamePlay.getColumn()][counterIndex] = gamePlay.getType().getValue();

                        boolean redWinner = false;
                        boolean greenWinner = false;

                        //Checking winner on vertical lines
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 7; j++) {
                                if (boardGame[j][i] == boardGame[j][i + 1] && (boardGame[j][i] == boardGame[j][i + 2]) && (boardGame[j][i + 3] == boardGame[j][i])) {
                                    if (boardGame[j][i] == FourConnect.GREEN.getValue() || boardGame[j][i] == FourConnect.RED.getValue()) {
                                        if (boardGame[j][i] == FourConnect.GREEN.getValue()) {
                                            greenWinner = true;
                                            g.setWinner(FourConnect.GREEN);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                        if (boardGame[j][i] == FourConnect.RED.getValue()) {
                                            redWinner = true;
                                            g.setWinner(FourConnect.RED);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                    }
                                }
                            }

                        }

                        //Check winner on horizontal lines
                        for (int i = 0; i < 6; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (boardGame[j][i] == boardGame[j + 1][i] && (boardGame[j][i] == boardGame[j + 2][i]) && (boardGame[j + 3][i] == boardGame[j][i])) {
                                    if (boardGame[j][i] == FourConnect.GREEN.getValue() || boardGame[j][i] == FourConnect.RED.getValue()) {
                                        if (boardGame[j][i] == FourConnect.GREEN.getValue()) {
                                            greenWinner = true;
                                            g.setWinner(FourConnect.GREEN);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                        if (boardGame[j][i] == FourConnect.RED.getValue()) {
                                            redWinner = true;
                                            g.setWinner(FourConnect.RED);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                    }
                                }
                            }

                        }

                        //Check winner on left diagonal lines
                        for (int i = 0; i < 3; i++) {
                            for (int j = 0; j < 4; j++) {
                                if (boardGame[j][i] == boardGame[j + 1][i + 1] && (boardGame[j][i] == boardGame[j + 2][i + 2]) && (boardGame[j][i] == boardGame[j + 3][i + 3])) {
                                    if (boardGame[j][i] == FourConnect.GREEN.getValue() || boardGame[j][i] == FourConnect.RED.getValue()) {
                                        if (boardGame[j][i] == FourConnect.GREEN.getValue()) {
                                            greenWinner = true;
                                            g.setWinner(FourConnect.GREEN);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                        if (boardGame[j][i] == FourConnect.RED.getValue()) {
                                            redWinner = true;
                                            g.setWinner(FourConnect.RED);
                                            g.setGameStatus(GameStatus.FINISHED);
                                            boardRepository.save(g);
                                        }
                                    }
                                }
                            }

                        }


                        //Check right diagonal winner

                        System.out.println(redWinner);
                        System.out.println(greenWinner);

                        return boardRepository.save(g);

                    }else {
                        throw new UnprocessableException("Partida acabada");
                        }
                    })

                ;

    }


}
