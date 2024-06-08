package org.dongguk.asap_server.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.dongguk.asap_server.type.EStatus;
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

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "section", nullable = false)
    private String section;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column(name = "last_status")
    @Enumerated(EnumType.STRING)
    private EStatus lastStatus;

    @Column(name = "edited_at")
    private LocalDateTime editedAt;

    public void update(EStatus status){
        this.lastStatus = this.status;
        this.status = status;
        editedAt = LocalDateTime.now();
    }
}
