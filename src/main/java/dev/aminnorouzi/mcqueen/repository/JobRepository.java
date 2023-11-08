package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {


}
