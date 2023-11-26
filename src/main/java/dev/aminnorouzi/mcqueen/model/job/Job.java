package dev.aminnorouzi.mcqueen.model.job;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "`job`")
@EntityListeners(AuditingEntityListener.class)
public class Job {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private String upworkId;
    private String url;
    private String title;

    @ToString.Exclude
    @Column(length = 1250)
    private String description;

    private String salary;
    private String country;
    private String category;

    @ToString.Exclude
    @Column(length = 1250)
    private String skills;

    @Enumerated(EnumType.STRING)
    private Status status;

    private Instant assignedAt;
    private Instant appliedAt;
    private Instant postedAt;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private Instant modifiedAt;
}
