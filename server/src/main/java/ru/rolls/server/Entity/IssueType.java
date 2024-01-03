package ru.rolls.server.Entity;


public enum IssueType {
    PICKUP, DELIVERY;

    public String getRoleName() {
        switch (this) {
            case PICKUP:
                return "Самовывоз";
            case DELIVERY:
                return "Доставка";
            default:
                throw new IllegalArgumentException("Unsupported role: " + this);
        }
    }
}
