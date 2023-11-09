package dev.aminnorouzi.mcqueen.bot.data;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageTemplate {

    JOB_POSTING("<b>📨 %s</b>\n%s\n\n<a href='%s'>Check on upwork</a>"),
    JOB_POSTING_ASSIGNED("<b>📨 %s</b>\n%s\n\n<a href='%s'>Check on upwork</a>\n\n<b>📎 This job is assigned to @%s.</b>"),
    JOB_POSTING_SUBMITTED("<b>📨 %s</b>\n%s\n\n<a href='%s'>Check on upwork</a>\n\n<b>✅ This job is submitted by @%s.</b>"),
    JOB_POSTING_REJECTED("<b>📨 %s</b>\n%s\n\n<a href='%s'>Check on upwork</a>\n\n<b>❌ This job is rejected by @%s.</b>");

    private final String value;
}