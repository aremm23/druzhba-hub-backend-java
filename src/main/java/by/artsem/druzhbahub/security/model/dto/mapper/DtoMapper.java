package by.artsem.druzhbahub.security.model.dto.mapper;

public interface DtoMapper<T, R> {
    R mapToDto(T entity);
    T mapToEntity(R dto);
}
