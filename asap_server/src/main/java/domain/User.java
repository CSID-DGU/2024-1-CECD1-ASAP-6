package domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sec", nullable = false)
    private String sec;

    @Column(name = "add", nullable = false)
    private String add;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "last_status")
    private String lastStatus;

    @Column(name = "edited_at")
    private LocalDateTime editedAt;


}
