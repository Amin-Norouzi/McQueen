package dev.aminnorouzi.mcqueen.model.job;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Column(length = 1250)
    private String description;

    private Status status;

    private Date postedAt;

    @CreatedDate
    @Column(updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private Date modifiedAt;
}
