package com.github.gordonforce.demo.springboot3webflux.api;

import java.util.*;

public class CollectionBuilder<T> {

  private List<T> collection;

  public CollectionBuilder<T> add(T item) {
    collection = Optional.ofNullable(collection).orElseGet(ArrayList::new);
    collection.add(item);
    return this;
  }

  public Collection<T> build(final T item) {
    return Collections.singletonList(item);
  }

  public Collection<T> build() {
    return Optional.ofNullable(collection)
        .filter(c -> !c.isEmpty())
        .map(c -> (c.size() == 1) ? build(c.get(0)) : c)
        .orElseGet(Collections::emptyList);
  }
}
