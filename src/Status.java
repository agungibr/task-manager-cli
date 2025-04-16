public enum Status {
    NOT_STARTED("Belum Dimulai"),
    IN_PROGRESS("Sedang Dikerjakan"),
    COMPLETED("Selesai"),
    ON_HOLD("Ditunda"),
    CANCELLED("Dibatalkan");
    
    private final String description;
    
    Status(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
    
    public boolean isCompleted() {
        return this == COMPLETED;
    }
    
    public boolean isActive() {
        return this == NOT_STARTED || this == IN_PROGRESS;
    }
    
    public static Status[] getAllStatuses() {
        return Status.values();
    }
}