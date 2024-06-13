package org.tasks.dockertask.core.entities.ids;

import org.tasks.dockertask.core.entities.BaseEntity;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * <p>Базовый интерфейс для типизации значения ID какой-либо модели.</p>
 *
 * <p>Этот интерфейс должен использоваться для типизации значений ID моделей внутри разрабатываемого сервиса.
 * Если типизированное значение ID модели необходимо передать за пределы сервиса, то необходимо использовать
 * {@link }, так как такое определение типизированного значения ID модели не зависит от класса самой модели,
 * которого не будет за пределами сервиса.</p>
 *
 * <p>Значение ID модели может быть равно {@code null}. Для работы с {@code null}-значениями предусмотрены методы
 * проверки {@code isPresent()} и {@code isEmpty()}.</p>
 *
 * @param <E> Тип модели.
 * @param <U> Тип значения ID модели.
 *
 * @se
 */
public interface EntityId<E extends BaseEntity<U>, U> {
    //region Public static

    /**
     * <p>Возвращает список значений ID модели по списку типизированных ID модели.</p>
     *
     * @param typedIds Список типизированных ID модели.
     *
     * @param <E> Тип модели.
     * @param <U> Тип значения ID модели.
     *
     * @return Список значений ID модели.
     */
    static <E extends BaseEntity<U>, U> Collection<U> extract(Collection<? extends EntityId<E, U>> typedIds) {

        return typedIds.stream()
            .map(EntityId::get)
            .collect(Collectors.toUnmodifiableList());
    }

    //endregion
    //region Public

    /**
     * <p>Значение ID модели не равно {@code null}?</p>
     *
     * @return Да/Нет.
     */
    boolean isPresent();

    /**
     * <p>Значение ID модели равно {@code null}?</p>
     *
     * @return Да/Нет.
     */
    boolean isEmpty();

    /**
     * <p>Переданное значение ID равно значению данного типизированного ID?</p>
     *
     * @param comparingValue Значение ID.
     *
     * @return Да/Нет.
     */
    boolean matches(U comparingValue);

    /**
     * <p>Переданный ID модели равен значению данного типизированного ID?</p>
     *
     * @param entityId ID модели.
     *
     * @return Да/Нет.
     */
    boolean matches(EntityId<E, U> entityId);

    /**
     * <p>Возвращает значение ID модели.</p>
     *
     * @return Значение ID модели.
     */
    U get();

    //endregion
}
