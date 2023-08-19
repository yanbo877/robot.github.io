package com.bigdata.coin.utils;

import java.util.Collection;

public final class CollectionUtils {

    private CollectionUtils() {
    }

    /**
     * 集合是否为空.
     *
     * @param collection 集合
     * @return 是否为空
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合是否为非空.
     *
     * @param collection 集合
     * @return 是否为非空
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return false == isEmpty(collection);
    }

    /**
     * 返回集合的第一个元素.
     *
     * @param collection 集合
     * @param <E> 元素类型
     * @return 第一个元素
     */
    public static <E> E getFist(final Collection<E> collection) {
        if (isEmpty(collection)) {
            return null;
        }
        return collection.iterator().next();
    }

    /**
     * 集合元素个数.
     *
     * @param collection 集合
     * @return 元素个数
     */
    public static int size(Collection<?> collection) {
        if (isEmpty(collection)) {
            return 0;
        }
        return collection.size();
    }
}
