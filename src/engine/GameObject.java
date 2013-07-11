package engine;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;

public class GameObject
		extends AbstractGameObject
{
	public Transform transform = new Transform();

	private ArrayList<AbstractBehaviour> behaviours = new ArrayList<AbstractBehaviour>();
	protected OnRenderListener renderListener = null;
	protected OnCollideListener collideListener = null;
	public AbstractCollider collider = null;
	public String TAG = null;

	private AbstractCollider lastCollided = null;

	public GameObject ()
	{
		transform.gameObject = this;
	}

	@Override
	public void start ()
	{
		Iterator<AbstractBehaviour> B = behaviours.iterator();

		while (B.hasNext())
		{
			AbstractBehaviour b = B.next();
			b.start();
		}

		transform.start();

		started = true;
	}

	@Override
	public void update (float dt)
	{
		if (!started) start();

		Iterator<AbstractBehaviour> B = behaviours.iterator();

		while (B.hasNext())
		{
			AbstractBehaviour b = B.next();
			b.update(dt);
		}

		transform.update(dt);
	}

	@Override
	public void lateUpdate (float dt)
	{
		Iterator<AbstractBehaviour> B = behaviours.iterator();

		while (B.hasNext())
		{
			AbstractBehaviour b = B.next();
			b.lateUpdate(dt);
		}

		transform.lateUpdate(dt);
	}

	public void addBehaviour (AbstractBehaviour b)
	{
		b.gameObject = this;
		b.transform = transform;

		behaviours.add(b);
	}

	public void sendMessage (String s, Object param)
	{
		Iterator<AbstractBehaviour> B = behaviours.iterator();

		while (B.hasNext())
		{
			AbstractBehaviour b = B.next();

			if (b instanceof IReceiver)
			{
				IReceiver r = (IReceiver) b;
				r.receiveMessage(s, param);
			}
		}
	}

	public void OnTrigger(AbstractCollider ac)
	{
		if (!ac.equals(lastCollided)) lastCollided = ac;
		else return;

		for (AbstractBehaviour ab:behaviours)
			if (ab instanceof ICollidable) ((ICollidable) ab).onCollide(ac);

	}

	public interface OnRenderListener
	{
		public void OnRender(Canvas c);
	}

	public abstract class OnCollideListener
	{
		public abstract void OnCollide(AbstractCollider ac);
	}

	public void setOnRenderListener(OnRenderListener orl)
	{
		renderListener = orl;
	}

	public void setOnCollideListener(OnCollideListener ocl, AbstractCollider ac)
	{
		collider = ac;
		collideListener = ocl;
	}
}
