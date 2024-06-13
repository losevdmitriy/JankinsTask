package org.tasks.dockertask.core.entities;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.Optional;

/**
 * <p>Базовая часть всех моделей БД.</p>
 *
 * @param <U> Тип ID модели.
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(callSuper = false, onlyExplicitlyIncluded = true)
@SuperBuilder(toBuilder = true)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> {
    //region Constants

    /**
     * <p>Начальное значение версии модели.</p>
     */
    private static final Long INITIAL_VERSION_VALUE = 1L;

    //endregion
    //region Fields

    /**
     * <p>Момент времени, когда модель была создана.</p>
     */
    @CreatedDate
    protected Instant createdDate;

    /**
     * <p>Момент времени последнего обновления модели.</p>
     */
    @LastModifiedDate
    protected Instant modifiedDate;

    /**
     * <p>Версия модели для контроля одновременных обновлений из разных процессов.</p>
     */
    @Version
    protected Long version;

    //endregion
    //region Public

    /**
     * <p>Возвращает ID модели.</p>
     *
     * @return ID модели.
     */
    public abstract U getId();

    /**
     * <p>Устанавливает значение идентификатора модели, если оно не задано.</p>
     *
     * @return Модель с инициализированным значением идентификатора.
     */
    public abstract BaseEntity<U> setIdIfEmpty();

    /**
     * <p>Устанавливает значение версии модели, если оно не задано.</p>
     *
     * @return Модель с инициализированной версией.
     */
    public BaseEntity<U> setDefaultVersionIfEmpty() {

        return this.toBuilder()
            .version(Optional.ofNullable(this.version).orElse(BaseEntity.INITIAL_VERSION_VALUE))
            .build();
    }

    /**
     * <p>Увеличивает значение версии модели на единицу.</p>
     *
     * @return Модель с увеличенной на единицу версией.
     */
    public BaseEntity<U> increaseVersion() {

        final long entityVersion = this.getVersionOrDefault();

        return this.toBuilder()
            .version(entityVersion + 1)
            .build();
    }

    /**
     * <p>Обновляет поля после модификации сущности: версия и дата модификации.</p>
     *
     * @return Сущность с обновлёнными метками.
     */
    public BaseEntity<U> touchForModification() {

        Instant now = Instant.now();

        return this.touchForModification(now);
    }

    /**
     * <p>Обновляет поля после модификации сущности: версия и дата модификации.</p>
     *
     * @param time Время модификации.
     *
     * @return Сущность с обновлёнными метками.
     */
    public BaseEntity<U> touchForModification(Instant time) {

        return this.toBuilder()
            .modifiedDate(time)
            .version(this.getVersionOrDefault() + 1)
            .build();
    }

    //endregion
    //region Protected

    /**
     * <p>Возвращает строителя класса.</p>
     *
     * @return Строитель класса.
     */
    protected abstract BaseEntityBuilder<U, ?, ?> toBuilder();

    //endregion
    //region Private

    /**
     * <p>Возвращает текущую версию сущности или значение по умолчанию, если версия не задана.</p>
     *
     * @return Версия сущности.
     */
    private long getVersionOrDefault() {

        long entityVersion = BaseEntity.INITIAL_VERSION_VALUE;
        if (this.version != null) {

            entityVersion = this.version;
        }

        return entityVersion;
    }

    //endregion
    //region Nested types

    /**
     * <p>Формализованные поля модели {@link BaseEntity}.</p>
     */
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Fields {
        //region Constants

        /**
         * <p>Имя поля ID модели.</p>
         *
         * <p>Имя поля ID модели объявлено здесь, так как поле ID модели появится только в дочернем классе, реализующем
         * конкретный тип идентификатора модели. А имя поля необходимо для методов базового репозитория, не зависящего
         * от типа идентификатора.</p>
         */
        public static final String ID = "id";

        /**
         * @see BaseEntity#createdDate
         */
        public static final String CREATED_DATE = "createdDate";

        /**
         * @see BaseEntity#modifiedDate
         */
        public static final String MODIFIED_DATE = "modifiedDate";

        /**
         * @see BaseEntity#version
         */
        public static final String VERSION = "version";

        //endregion
    }

    //endregion
}

