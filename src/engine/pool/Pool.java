package engine.pool;

import android.util.Log;

import java.util.Stack;

/**
 * An Object Pool
 */
public class Pool<T extends IPoolable>
{
	protected final int         POOL_MAX_SIZE;
	protected       Stack<T>    pool;
	protected       IFactorable factory;
	protected int freeObjIndex = -1;

	/**
	 * Constructor
	 *
	 * @param factory
	 * 		The object pool Factory
	 * @param maxSize
	 * 		The maximum pool size
	 */
	public Pool (IFactorable factory, int maxSize)
	{
		this.factory = factory;
		pool = new Stack<T>();
		pool.setSize(maxSize);
		POOL_MAX_SIZE = maxSize - 1;
	}

	/**
	 * Create new Object OR return a free object from the pool
	 *
	 * @return a IPollable instance already initialized
	 */
	public synchronized T newObject ()
	{
		T obj = null;

		if (freeObjIndex == -1)
		{
			obj = (T) factory.createNew();
		}
		else
		{
			obj = pool.get(freeObjIndex);

			freeObjIndex--;
		}

		obj.init();

		Log.i("POOL GET", String.valueOf(freeObjIndex));

		return obj;
	}

	/**
	 * Stores an object instance in the pool to make it available for a call to newObject()
	 * (It will be considered free)
	 *
	 * @param obj
	 * 		the object to store in the pool
	 */
	public synchronized void freeObject (T obj)
	{
		if (obj != null)
		{
			obj.reset();

			if (freeObjIndex < POOL_MAX_SIZE)
			{
				freeObjIndex++;

				pool.set(freeObjIndex, obj);
			}
		}

		Log.i("POOL RESET", String.valueOf(freeObjIndex));
	}
}
