package ru.rolls.server.entity;

public enum EmployeeRole {
    ADMIN, DELIVERER, OPERATOR, CHEF;

    public String getRoleName() {
        switch (this) {
            case ADMIN:
                return "Администратор";
            case DELIVERER:
                return "Курьер";
            case OPERATOR:
                return "Оператор ГИС";
            case CHEF:
                return "Сушист";
            default:
                throw new IllegalArgumentException("Unsupported role: " + this);
        }
    }
}