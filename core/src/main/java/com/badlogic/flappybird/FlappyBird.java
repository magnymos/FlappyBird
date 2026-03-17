package com.badlogic.flappybird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class FlappyBird {

    final int boardWidth = 720;
    final int boardHeight = 1280;
    Texture bird;

    float velocityY = 0;
    float gravity = -1000;
    Vector2 position;

    int health = 3;


    public FlappyBird() {
        this.bird = new Texture("flappybird.png");
        position = new Vector2(boardWidth / 8, boardHeight / 2);
    }

    public void render(SpriteBatch batch) {
        batch.draw(bird, position.x, position.y);
    }

    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            velocityY = 450;
            position.y = Math.min(position.y, 1280);
        }
        velocityY += gravity * dt;
        position.y += velocityY * dt;
        position.y = Math.max(position.y, 0);
    }
    public void takeOneDamage(){
        health--;
        if (health<0){

        }
    }

}
