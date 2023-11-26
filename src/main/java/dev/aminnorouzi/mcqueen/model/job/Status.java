package dev.aminnorouzi.mcqueen.model.job;

public enum Status {

    POSTED,
    ASSIGNED,
    SUBMITTED,
    REJECTED;

    public static Status from(String value) {
        return switch (value) {
            case "assigned" -> Status.ASSIGNED;
            case "submitted" -> Status.SUBMITTED;
            case "rejected" -> Status.REJECTED;
            default -> Status.POSTED;
        };
    }
}
