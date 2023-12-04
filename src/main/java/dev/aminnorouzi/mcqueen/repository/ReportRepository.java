package dev.aminnorouzi.mcqueen.repository;

import dev.aminnorouzi.mcqueen.model.report.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {

}
