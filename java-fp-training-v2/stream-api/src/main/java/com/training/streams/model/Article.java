package com.training.streams.model;

import java.util.List;

/**
 * A blog article with a list of tags. Used to practise {@code flatMap}:
 * one article has many tags, and we often want "all tags of all articles".
 *
 * @param title the article title
 * @param tags  zero or more tags, for example ["java", "streams"]
 */
public record Article(String title, List<String> tags) {
}
