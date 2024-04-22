package dw.gameshop.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {

//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //  → 이거 @Column이랑 같이쓰면 충돌남, 둘 중 하나만 쓸 것
    @Id
    @Column(name = "user_id", length = 100)
    private String userId;
    @Column(name = "user_name", nullable = false, length = 255)
    private String userName;
    @Column(name = "email", nullable = false, length = 255)
    private String email;

    public User() {
    }

    public User(String userId, String userName, String email) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
