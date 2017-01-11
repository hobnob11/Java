package uk.co.willhobson.hobicons.managers;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.co.willhobson.hobicons.Time;
import uk.co.willhobson.hobicons.components.PositionComponent;
import uk.co.willhobson.hobicons.components.RenderableComponent;
import uk.co.willhobson.hobicons.components.SpriteComponent;
import uk.co.willhobson.hobicons.components.VelocityComponent;
import uk.co.willhobson.hobicons.systems.MovementSystem;
import uk.co.willhobson.hobicons.systems.RenderSystem;

public class EntityManager
{
	private Engine engine;

	public EntityManager(Engine e, SpriteBatch batch)
	{
		engine = e;

		MovementSystem cms = new MovementSystem();
		engine.addSystem(cms);
		RenderSystem rs = new RenderSystem(batch);
		engine.addSystem(rs);

		//@formatter:off
		//WARNING: HAZARDOUS FLOOR CONDITIONS AHEAD, 2 SLICK 4 U
		Entity entity = new Entity();
		entity.add(new PositionComponent(250, 250))
			  .add(new VelocityComponent(1, 1))
			  .add(new SpriteComponent(new Texture(Gdx.files.local("Hob-Co.png"))))
			  .add(new RenderableComponent());
		//@formatter:on
		engine.addEntity(entity);

	}

	public void update()
	{
		engine.update((float) Time.time);
	}
}
