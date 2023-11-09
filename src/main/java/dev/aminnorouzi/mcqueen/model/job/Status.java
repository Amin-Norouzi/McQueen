package dev.aminnorouzi.mcqueen.model.job;

public enum Status {

    POSTED,
    CHECKED,
    SUBMITTED,
    REJECTED;

    public static Status find(String value) {
        return switch (value) {
            case "checked" -> Status.CHECKED;
            case "submitted" -> Status.SUBMITTED;
            case "rejected" -> Status.REJECTED;
            default -> Status.POSTED;
        };
    }
}
