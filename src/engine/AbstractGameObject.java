package engine;

import java.util.ArrayList;

/**
 * An abstract representation of a GameObject
 * */
public abstract class AbstractGameObject
{

	/**
	 * The destroy listener
	 * */
	protected OnDestroyListener destroyListener = null;

	/**
	 * True if the object has started
	 * */
	protected boolean           started         = false;

	/**
	 * Add a new GameObject to the current scene
	 *
	 * @param go
	 * 		the GameObject to add
	 * @param p
	 * 		the position where add the GameObject
	 * @param r
	 * 		the rotation to give to the GameObject
	 */
	public static void Instanciate (GameObject go, Vector2 p, float r)
	{
		if (p != null) go.transform.Translate(p);
		if (r != 0.0f) go.transform.Rotate(r);

		GameEngine.getInstance().getScene().addChild(go);
		go.started = false;
	}

	/**
	 * Remove the GameObject from the scene
	 *
	 * @param go
	 * 		the GameObject to remove
	 */
	public static void Destroy (GameObject go)
	{
		if (go.destroyListener != null) go.destroyListener.OnDestroy();

		ArrayList<GameObject> objects = GameEngine.getInstance().getScene().objToRemove;
		objects.add(go);
	}

	/**
	 * Executed before first update
	 * */
	public abstract void start ();

	/**
	 * Executed each frame
	 * */
	public abstract void update (float dt);

	/**
	 * Executed each physic frame
	 * */
	public abstract void lateUpdate (float dt);

	/**
	 * Define a listener for when the GameObject is about to be removed from the scene
	 *
	 * @param odl the Listeneer
	 * */
	public void setOnDestroyListener (OnDestroyListener odl)
	{
		destroyListener = odl;
	}

	/**
	 * On destroy listener dood
	 * */
	public interface OnDestroyListener
	{
		public void OnDestroy ();
	}
}
