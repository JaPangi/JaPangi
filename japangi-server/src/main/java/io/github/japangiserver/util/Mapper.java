package io.github.japangiserver.util;

public interface Mapper <D,E>{
    default D toDomain(E dto){
        throw new IllegalStateException("Failed to transfer Dto to Domain");
    }

    default E toEntityFromDomain(D domain){
        throw new IllegalStateException("Failed to transfer Entity to Domain");
    }

    default D toEntityFromDto(E dto){
        throw new IllegalStateException("Failed to transfer Entity to Dto");
    }
}
