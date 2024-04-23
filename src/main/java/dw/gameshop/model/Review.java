package dw.gameshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "game", nullable = false)
    private Game game;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(name = "point", nullable = false)
    private int point;

    @Column(name = "review_text", nullable = false, length = 655355)
    private String reviewText;

    @Column(name = "local_at", updatable = false)
    private LocalDateTime createdAt;
    // ↑ 생성날짜 변수명은 보통 created~로 기입한다고 함
    // updatable : 데이터를 갱신가능하게 할 지 여부
    // insertable : 데이터를 SQL로 입력 가능하게 할 지 여부
}
