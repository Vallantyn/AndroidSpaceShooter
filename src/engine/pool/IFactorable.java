package engine.pool;

import engine.pool.IPoolable;

/**
 * Created by Julien on 08/07/13.
 */
public interface IFactorable<T extends IPoolable>
{
	public T createNew();
}
