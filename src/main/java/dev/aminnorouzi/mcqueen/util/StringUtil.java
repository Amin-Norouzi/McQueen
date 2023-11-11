package dev.aminnorouzi.mcqueen.util;

import dev.aminnorouzi.mcqueen.bot.data.MessageTemplate;
import dev.aminnorouzi.mcqueen.model.job.Job;
import dev.aminnorouzi.mcqueen.model.job.Status;
import dev.aminnorouzi.mcqueen.model.user.User;
import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String generateJobPostingMessage(Job job) {
        return MessageTemplate.JOB_POSTING.getValue().formatted(
                job.getTitle(), job.getPostedAt(), job.getDescription(), job.getSalary(), job.getCategory(), job.getCountry(), job.getSkills(), job.getUrl());
    }

    public String generateJobPostingStatusMessage(Job job, User user) {
        if (job.getStatus().equals(Status.SUBMITTED)) {
            return MessageTemplate.JOB_POSTING_SUBMITTED.getValue().formatted(
                    job.getTitle(), job.getPostedAt(), job.getDescription(), job.getSalary(), job.getCategory(), job.getCountry(), job.getSkills(), job.getUrl(), user.getUsername());
        } else {
            return MessageTemplate.JOB_POSTING_REJECTED.getValue().formatted(
                    job.getTitle(), job.getPostedAt(), job.getDescription(), job.getSalary(), job.getCategory(), job.getCountry(), job.getSkills(), job.getUrl(), user.getUsername());
        }
    }

    public String generateJobPostingSAssignMessage(Job job, User user) {
        return MessageTemplate.JOB_POSTING_ASSIGNED.getValue().formatted(
                job.getTitle(), job.getPostedAt(), job.getDescription(), job.getSalary(), job.getCategory(), job.getCountry(), job.getSkills(), job.getUrl(), user.getUsername());
    }
}
