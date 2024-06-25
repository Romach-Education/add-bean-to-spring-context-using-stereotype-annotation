package org.example;

import org.springframework.stereotype.Component;

@Component
public class Book {
    private final String title;

    public Book() {
        this.title = "One Hundred Years of Solitude";
    }

    public String getTitle() {
        return title;
    }
}
