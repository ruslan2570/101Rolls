package ru.rolls.server.entity;


public enum IssueType {
    PICKUP, DELIVERY;

    public String getRoleName() {
        switch (this) {
            case PICKUP:
                return "Самовывоз";
            case DELIVERY:
                return "Доставка";
            default:
                throw new IllegalArgumentException("Unsupported type of issue: " + this);
        }
    }
}
