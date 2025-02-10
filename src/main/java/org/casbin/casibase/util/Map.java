/*
 * 	Copyright 2025 The casbin Authors. All Rights Reserved.
 *
 * 	Licensed under the Apache License, Version 2.0 (the "License");
 * 	you may not use this file except in compliance with the License.
 * 	You may obtain a copy of the License at
 *
 * 	     http://www.apache.org/licenses/LICENSE-2.0
 *
 * 	Unless required by applicable law or agreed to in writing, software
 * 	distributed under the License is distributed on an "AS IS" BASIS,
 * 	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * 	See the License for the specific language governing permissions and
 * 	limitations under the License.
 */

package org.casbin.casibase.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.stream.Collectors;

public class Map {

    /**
     * Converts a map of key-value pairs into a URL-encoded query string.
     * If the map is null or empty, an empty string is returned.
     *
     * @param map The map containing key-value pairs to be converted into a query string.
     * @return A URL-encoded query string in the format "key1=value1&amp;key2=value2".
     *         Returns an empty string if the map is null or empty.
     */
    public static String mapToUrlParams(@Nullable java.util.Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        return map.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
    }

    /**
     * Merges two maps into a single map. If either map is null, the other map is returned.
     * If both maps are null, an empty map is returned.
     *
     * @param map1 The first map to merge.
     * @param map2 The second map to merge.
     * @param <T> The type of the keys in the maps.
     * @param <V> The type of the values in the maps.
     * @return A new map containing all entries from both input maps. If both maps are null, an empty map is returned.
     */
    @NotNull
    public static <T, V> java.util.Map<T, V> mergeMap(@Nullable java.util.Map<T, V> map1, @Nullable java.util.Map<T, V> map2) {
        if (map1 == null) {
            return map2 == null ? new HashMap<>() : map2;
        }
        if (map2 == null) {
            return map1;
        }
        map1.putAll(map2);
        return map1;
    }

    /**
     * Creates a map from a variable number of key-value pairs.
     * The input array must contain an even number of elements, where each pair of elements represents a key and its corresponding value.
     *
     * @param kv A variable number of key-value pairs. The array length must be even.
     * @param <T> The type of the keys and values in the map.
     * @return A map containing the provided key-value pairs.
     * @throws IllegalArgumentException If the number of arguments is odd.
     */
    @SafeVarargs
    @NotNull
    public static <T> java.util.Map<T, T> of(@NotNull T... kv) {
        java.util.Map<T, T> map = new HashMap<>(kv.length / 2 + 1);
        for (int i = 0; i < kv.length; i += 2) {
            map.put(kv[i], kv[i + 1]);
        }
        return map;
    }

    /**
     * Creates a map with exactly two key-value pairs.
     *
     * @param k1 The first key.
     * @param v1 The value associated with the first key.
     * @param k2 The second key.
     * @param v2 The value associated with the second key.
     * @param <T> The type of the keys in the map.
     * @param <V> The type of the values in the map.
     * @return A map containing the two provided key-value pairs.
     */
    public static <T, V> java.util.Map<T, V> of(@NotNull T k1, @NotNull V v1, @NotNull T k2, @NotNull V v2) {
        java.util.Map<T, V> map = new HashMap<>(2);
        map.put(k1, v1);
        map.put(k2, v2);
        return map;
    }
}
