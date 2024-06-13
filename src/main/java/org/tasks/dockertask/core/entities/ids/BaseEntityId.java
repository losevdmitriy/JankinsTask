package org.tasks.dockertask.core.entities.ids;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.tasks.dockertask.core.entities.BaseEntity;

import java.util.Objects;

/**
 * <p>Базовый класс для типизации ID какой-либо модели.</p>
 *
 * @param <E> Тип модели.
 * @param <U> Тип ID модели.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
public abstract class BaseEntityId<E extends BaseEntity<U>, U> implements EntityId<E, U> {
    //region Fields

    /**
     * <p>Значение ID модели.</p>
     */
    @EqualsAndHashCode.Include
    private final U value;

    //endregion
    //region Public

    @Override
    public boolean isPresent() {

        return (this.get() != null);
    }

    @Override
    public boolean isEmpty() {

        return (this.get() == null);
    }

    @Override
    public boolean matches(EntityId<E, U> entityId) {

        if (entityId == null) {

            return false;
        }

        return this.matches(entityId.get());
    }

    @Override
    public boolean matches(U comparingValue) {

        return Objects.equals(comparingValue, this.get());
    }

    @Override
    public U get() {

        return this.value;
    }

    @Override
    public String toString() {

        return String.format("%s[value=%s]", this.getClass(), this.value);
    }

    //endregion
}
