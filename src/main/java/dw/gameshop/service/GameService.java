package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.model.User;
import dw.gameshop.repository.GameRepository;
import dw.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {

    GameRepository gameRepository;

    UserRepository userRepository;

    @Autowired // 안써도 되는데 그냥 써 놓은것임(만약 필드에 쓰면 각각 써야함(다수일 경우))
    public GameService(GameRepository gameRepository, UserRepository userRepository) {
        this.gameRepository = gameRepository;
        this.userRepository = userRepository;
    }
    
    public List<Game> getAllGames(){
        return gameRepository.findAll();
    }
    public Game getGameById(long id){
        Optional<Game> game = gameRepository.findById(id);
        if(game.isEmpty()){
            throw new ResourceNotFoundException("Game", "ID", id);
        }else{
            return game.get();
        }
    }

    public Game updateGameById(long id, Game games){
        Optional<Game> game2 = gameRepository.findById(id);
        if(game2.isPresent()){
            game2.get().setTitle(games.getTitle());
            game2.get().setGenre(games.getGenre());
            game2.get().setImage(games.getImage());
            game2.get().setPrice(games.getPrice());
            game2.get().setText(games.getText());
            gameRepository.save(game2.get());
            // 여기에는 입력한 값을 주소창에 입력한 id값에 해당하는 내용을 업데이트하는거
            return game2.get();
        } else{
            throw new ResourceNotFoundException("Game", "ID", id);
        }
    }
    
    public User saveUser(User user){
        return userRepository.save(user);
    }


    // 제일 비싼 게임의 정보
    public Game getGameWithMaxPrice() {
        List<Game> games = gameRepository.findAll();
        // 람다식이 아닌 일반 자바코드 사용 예
        if (games.size() <= 1) {
            throw new ResourceNotFoundException("Max Price", " ", "");
        }
        Game max = games.get(0);
        for (int i = 0; i < games.size() - 1; i++) {
            if (games.get(i).getPrice() > games.get(i + 1).getPrice()) {
                max = games.get(i);
            }
        }

        return max;
        // 람다식 사용 예

//        return games.stream().sorted(Comparator.comparingInt(Game::getPrice()) // 바로 아래 코드와 상동
//        return games.stream().sorted(Comparator.comparingInt((Game g) -> g.getPrice())
//                .reversed())
//                .findFirst()
//                .orElseThrow(() -> new ResourceNotFoundException("Max Price", " ", " "));
        // SQL 사용 예
//            return gameRepository.getGameWithMaxPrice();
    }
    // 제일 비싼 게임 Top3
    public List<Game> getGameWithMaxPriceTop3(){
        List<Game> games = gameRepository.findAll();
        // 보통 sort를 적용하여 해결하면 되는데, 여기서는 거기에 람다식까지 적용
//        games.sort(Comparator.comparingInt((Game g) -> g.getPrice()).reversed());
//        // ↑ 객체의 sort는 기준점을 잡아줘야되기 때문에 안에 Comparator로 조건설정
//        List<Game> newGames = new ArrayList<>();
//        newGames.add(games.get(0));
//        newGames.add(games.get(1));
//        newGames.add(games.get(2));
//        return newGames;
//        // 람다식 적용
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
        // SQL 사용 예
        return gameRepository.getGameWithMaxPriceTop3()
                .stream().limit(3).collect(Collectors.toList());
    }
}
