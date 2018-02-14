package info233v2018.labuke2;

public interface BagInterface<T> {
	/**
	 * adds object to bag
	 * @param t - object to add
	*/
	void add(T t);
	/**
	 * removes objects from bag
	 */
	T remove();

	boolean isEmpty();
}
