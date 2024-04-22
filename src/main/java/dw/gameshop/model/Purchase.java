package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
// ↑ lombok 사용하려면 이 4개는 필수로 써야됨
// 주의!! : lombok 중에 @Data는 절대로 사용하지 말 것!!
//          → 위의 4개를 대체할 수 있지만 나중에 복잡해짐(지금은 쓰지 말라는것)
// DTO를 사용할때는 @Setter 사용안함
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // ↓ oneToMany를 쓰게되면 아래 필드는 리스트로 정의해야됨
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "purchase_time")
    private LocalDateTime purchaseTime;
    // ↑ 프론트에서 줄 수도 있지만 별로 좋지않은 방법
}
