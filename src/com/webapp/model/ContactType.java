package com.webapp.model;

public enum ContactType {
    PHONE("Тел."),
    MOBILE("Мобильный"),
    HOME("Домашний тел."),
    SKYPE("Skype"),
    MAIL("Почта"),
    GITHUB("Профиль GitHub");

    private final String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }
}
