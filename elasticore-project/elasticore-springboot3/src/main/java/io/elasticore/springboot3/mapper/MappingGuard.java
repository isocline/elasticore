package io.elasticore.springboot3.mapper;

@FunctionalInterface
public interface MappingGuard {
    boolean check(MappingContext ctx);
}
