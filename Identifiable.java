/**
 * An interface Identifiable with parameter type T
 * that represents entity with an identifier
 *
 * @author Gabriela Montoya
 * @version 1.1, 2023-10-20
 */
public interface Identifiable<T> {
    T getIdentifier();
}
