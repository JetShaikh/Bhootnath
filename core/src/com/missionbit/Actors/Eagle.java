package com.missionbit.Actors;


import com.missionbit.States.InGame;

public class Eagle extends Character {
    public Eagle(int x, int y, InGame game) {
        super(50, 100, game);
    }

    public Eagle(int hp, InGame game, int x, int y) {
        super(hp, game, x, y);
    }

//    public Eagle(int hp, int x, int y) {
//        super(hp, game, x, y);
//    }

//    public Eagle() {
//        this.state = state;
//        texture = new Texture("Characters/Eagle.png");
//        moveSpeed = 150;
//        anim = new Animation(new TextureRegion(texture), 8, 1f, 3, 3);
//        velocity = new Vector2(0, 0);
//        position = new Vector2(x, y);
//        bounds = new Rectangle(position.x, getTexture().getRegionWidth(), getTexture().getRegionHeight());
//    }
//
//    public Eagle(int x, int y) {
//        super(x, y, this);
//    }
}
