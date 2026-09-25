package com.training.streams.model;

/**
 * An employee. Used in the Collectors exercise.
 *
 * @param id         unique employee id
 * @param name       first name
 * @param department department name, for example "Engineering"
 * @param salary     yearly salary in whole currency units
 */
public record Employee(int id, String name, String department, int salary) {
}
