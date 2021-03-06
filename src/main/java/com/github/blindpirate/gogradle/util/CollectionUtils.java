package com.github.blindpirate.gogradle.util;

import org.codehaus.groovy.runtime.DefaultGroovyMethods;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;

public class CollectionUtils {

    public static <T> boolean isEmpty(Collection<T> c) {
        return c == null || c.isEmpty();
    }

    public static <T> List<T> immutableList(T... elements) {
        return Collections.unmodifiableList(asList(elements));
    }

    public static <T> List<T> collectOptional(Optional<T>... optionals) {
        List<T> ret = new ArrayList<>();
        for (Optional<T> optional : optionals) {
            optional.ifPresent(ret::add);
        }
        return ret;
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> flatten(List<T>... lists) {
        return (List<T>) DefaultGroovyMethods.flatten(lists);
    }
}
