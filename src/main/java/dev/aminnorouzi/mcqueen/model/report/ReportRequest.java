package dev.aminnorouzi.mcqueen.model.report;

import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportRequest {

    private List<Job> jobs;
    private List<Job> submitted;
    private List<Job> rejected;
    private User hero;
    private Instant date;
}
