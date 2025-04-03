package com.dio.jdbc_sample.util.persistence.Entity;

import java.util.stream.Stream;

public enum OperationEnum {
    INSERT,
    UPDATE,
    DELETE;

    public static OperationEnum getByDbOperation(final String dbOperation){
        return Stream.of(OperationEnum.values())
                .filter(o -> o.name().startsWith(dbOperation.toUpperCase()))
                .findFirst()
                .orElseThrow();
    }
}
