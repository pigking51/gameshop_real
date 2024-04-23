package dw.gameshop.repository;

import dw.gameshop.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
//    public List<Game> findByGamePrice(int price);
    @Query("select g1 from Game g1 where g1.price = (select max(g2.price) from Game g2)")
    Game getGameWithMaxPrice();
    // 기존 SQL과는 다르게 *를 사용안하고 그 자체를 적어줌

    @Query("select g1 from Game g1 order by g1.price desc")
    public List<Game> getGameWithMaxPriceTop3();

}
