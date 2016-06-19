package cc.picc.commons;

/**
 * 
 * @author Justin
 *
 */
public interface HasValue<T> {
	/**
	 * 
	 * @return the value of it
	 */
	T getValue();

	/**
	 * convert from value to object
	 * 
	 * @param value
	 * @return
	 */
	HasValue<T> fromValue(T value);
}
