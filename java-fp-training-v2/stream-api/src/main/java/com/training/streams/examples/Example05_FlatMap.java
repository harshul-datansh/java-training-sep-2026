package com.training.streams.examples;

import com.training.streams.model.Article;
import com.training.streams.model.SampleData;

import java.util.List;

/**
 * Example 05 - flatMap.
 *
 * <p>Idea: when each element has a LIST inside it, {@code map} gives you a stream of lists,
 * but {@code flatMap} gives you one flat stream of all the inner elements.
 */
public class Example05_FlatMap {

    public static void main(String[] args) {
        List<Article> articles = SampleData.articles();

        // map: one article -> one List<String>. Result: a list of lists.
        List<List<String>> nested = articles.stream()
                .map(Article::tags)
                .toList();
        System.out.println("map     : " + nested);
        // prints [[java, streams, beginner], [java, records], [lambdas, java, advanced], []]

        // flatMap: one article -> a STREAM of its tags, and all those streams are joined into one.
        List<String> flat = articles.stream()
                .flatMap(article -> article.tags().stream())
                .toList();
        System.out.println("flatMap : " + flat);
        // prints [java, streams, beginner, java, records, lambdas, java, advanced]

        // Typical follow-up: remove duplicates.
        System.out.println("distinct: " + flat.stream().distinct().toList());
        // prints [java, streams, beginner, records, lambdas, advanced]
    }
}
