package dev.aminnorouzi.mcqueen.model.report;

import dev.aminnorouzi.mcqueen.model.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`report`")
@EntityListeners(AuditingEntityListener.class)
public class Report {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer total;
    private Integer submitted;
    private Integer rejected;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User hero;

    @Enumerated(EnumType.STRING)
    private Type type;

    private Instant date;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant modifiedAt;
}
