package engine;

/**
 * Created by Julien on 08/07/13.
 */
public class SquareCollider
		extends AbstractCollider
{
	public SquareCollider (GameObject ago)
	{
		super(ago);
	}

	@Override
	public boolean isColliding (AbstractCollider other)
	{
		Vector2 posA = gameObject.transform.position;
		Vector2 posB = other.gameObject.transform.position;

		return !(
				posA.x + bounds.left > posB.x + other.bounds.right
			 || posA.x + bounds.right < posB.x + other.bounds.left
			 || posA.y + bounds.top < posB.y + other.bounds.bottom
			 || posA.y + bounds.bottom > posB.y + other.bounds.top
		);
	}
}
