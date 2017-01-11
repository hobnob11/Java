package uk.co.willhobson.hobicons;

import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import uk.co.willhobson.hobicons.managers.EntityManager;

public class Hobicons extends ApplicationAdapter
{

	SpriteBatch batch;

	private EntityManager entityManager;

	@Override
	public void create()
	{
		Engine engine = new Engine();

		batch = new SpriteBatch();
		entityManager = new EntityManager(engine, batch);
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Time.update(); // used to figure out the "performance lag"

		batch.begin();
		entityManager.update();

		batch.end();

	}

	@Override
	public void dispose()
	{
		batch.dispose();
	}
}
