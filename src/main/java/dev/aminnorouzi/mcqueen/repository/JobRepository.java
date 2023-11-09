package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.job.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

    @Query("select (count(j) > 0) from Job j where j.upworkId = ?1")
    boolean existsByUpworkId(String upworkId);

    @Query("select j from Job j where j.upworkId = ?1")
    Optional<Job> findByUpworkId(String upworkId);
}
