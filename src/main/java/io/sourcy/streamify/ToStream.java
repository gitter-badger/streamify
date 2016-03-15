package io.sourcy.streamify;

/*
 * Copyright (c) Sourcy Software & Services GmbH 2016.
 *
 *     _____ ____   __  __ _____ _____ __  __    (_)____
 *    / ___// __ \ / / / // ___// ___// / / /   / // __ \
 *   (__  )/ /_/ // /_/ // /   / /__ / /_/ /_  / // /_/ /
 *  /____/ \____/ \__,_//_/    \___/ \__, /(_)/_/ \____/
 *                                  /____/
 *
 * Created by daniel selinger <d.selinger@sourcy.io> on 2016-02-27.
 */

import javaslang.Tuple;
import javaslang.Tuple2;
import javaslang.Value;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author daniel selinger
 * @author armin walland
 */
public final class ToStream {

    private ToStream() {
    }

    // tostream for java standard collections
    public static <T> Stream<T> toStream(final Optional<T> o) {
        return o.isPresent() ? Stream.of(o.get()) : Stream.empty();
    }

    public static <T> Stream<T> toStream(final Collection<T> c) {
        return c.stream();
    }

    public static <T> Stream<T> toStream(final T[] a) {
        return Arrays.stream(a);
    }

    public static <K, V> Stream<Tuple2<K, V>> toStream(final Map<K, V> m) {
        return m.entrySet().stream().map(e -> Tuple.of(e.getKey(), e.getValue()));
    }

    // tostream for special guava collections (not covered or awkward to use by java standard types)
    // TODO write toStream + collector tests for all immutable guava collections and check it the stream contains usable data

    // tostream for javaslang collections
    public static <T extends Value<U>, U> Stream<U> toStream(final T v) {
        return v.toJavaStream();
    }
}