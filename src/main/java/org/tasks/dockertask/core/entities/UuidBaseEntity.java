package org.tasks.dockertask.core.entities;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.UUID;

/**
 * <p>Базовая часть моделей БД с идентификатором типа UUID.</p>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@FieldNameConstants
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public class UuidBaseEntity extends BaseEntity<UUID> {
    //region Fields

    /**
     * <p>ID модели.</p>
     */
    @Id
    @EqualsAndHashCode.Include
    private final UUID id;

    //endregion
    //region Public

    @Override
    public BaseEntity<UUID> setIdIfEmpty() {

        return this.toBuilder()
            .id(Optional.ofNullable(this.getId()).orElseGet(UUID::randomUUID))
            .build();
    }

    //endregion
}
