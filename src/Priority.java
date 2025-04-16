public enum Priority {
    URGENT("Mendesak", 3),
    IMPORTANT("Penting", 2),
    NOT_URGENT("Tidak Mendesak", 1),
    NOT_IMPORTANT("Tidak Penting", 0);

    private final String description;
    private final int level;

    Priority(String description, int level) {
        this.description = description;
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public int getLevel() {
        return level;
    }

    public boolean isHighPriority() {
        return level >= 2;
    }

    public boolean isLowPriority() {
        return level <= 1;
    }

    public static Priority getByLevel(int level) {
        for (Priority p : values()) {
            if (p.getLevel() == level) {
                return p;
            }
        }
        return null;
    }
}