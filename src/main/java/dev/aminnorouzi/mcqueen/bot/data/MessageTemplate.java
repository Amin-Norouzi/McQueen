package dev.aminnorouzi.mcqueen.bot.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageTemplate {

    JOB_POSTING("<b>🔔 %s</b>\n%s\n\n<b>Description:</b> %s\n\n<b>Hourly Range:</b> %s\n<b>Category:</b> %s\n<b>Country:</b> %s\n<b>Skills:</b> %s\n\n<a href='%s'>Check on upwork</a>"),
    JOB_POSTING_ASSIGNED(JOB_POSTING.value + "\n\n<b>📎 This job is assigned to @%s.</b>"),
    JOB_POSTING_SUBMITTED(JOB_POSTING.value + "\n\n<b>✅ This job is submitted by @%s.</b>"),
    JOB_POSTING_REJECTED(JOB_POSTING.value + "\n\n<b>❌ This job is rejected by @%s.</b>"),

    DAILY_REPORT("<b>🗂️ Daily report: %s</b>\n\n<b>Total jobs:</b> %s\n<b>Submitted jobs:</b> %s\n<b>Rejected jobs:</b> %s\n<b>Most active user:</b> @%s");

    private final String value;
}