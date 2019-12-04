package game.objects.modifiers;

import engine.graphics.MovingSpriteTexture;
import engine.graphics.Orientation;
import engine.graphics.SpriteTexture;
import game.PacmanWorld;
import game.objects.GameObject;
import game.objects.enemies.Blocker;
import game.objects.enemies.Chaser;
import game.objects.enemies.Crazy;
import game.utils.CollisionSignal;
import javafx.scene.image.Image;

public class InvincibleEffect extends EffectModifier {
    public InvincibleEffect(AppliableEffect appliableEffect) {
        super(appliableEffect);
    }

    @Override
    public void applyModifier(GameObject gameObject, double duration) {
        PacmanWorld pc = (PacmanWorld) gameObject.getWorld();
        pc.pacman.isInvincible = true;
        for (int i = 0; i < pc.getEntities().size(); i++) {

            if (pc.getEntities().get(i).getClass() == Chaser.class) {
                Chaser tmp = (Chaser) pc.getEntities().get(i);
                tmp.setScared(true);
            }
            if (pc.getEntities().get(i).getClass() == Blocker.class) {
                Blocker tmp = (Blocker) pc.getEntities().get(i);
                tmp.setScared(true);
            }
            if (pc.getEntities().get(i).getClass() == Crazy.class) {
                Crazy tmp = (Crazy) pc.getEntities().get(i);
                tmp.setScared(true);
            }
        }
        super.applyModifier(gameObject, duration);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN_INVINCIBLE);
        gameObject.setSpriteTexture(new MovingSpriteTexture(new Image("player_powered.png")));
    }

    @Override
    public void removeModifier(GameObject gameObject) {
        PacmanWorld pc = (PacmanWorld) gameObject.getWorld();
        pc.pacman.isInvincible = false;
        super.removeModifier(gameObject);
        gameObject.setCollisionSignal(CollisionSignal.PACMAN);

        for (int i = 0; i < pc.getEntities().size(); i++) {

            if (pc.getEntities().get(i).getClass() == Chaser.class) {
                Chaser tmp = (Chaser) pc.getEntities().get(i);
                tmp.setScared(false);
            }
            if (pc.getEntities().get(i).getClass() == Blocker.class) {
                Blocker tmp = (Blocker) pc.getEntities().get(i);
                tmp.setScared(false);
            }
            if (pc.getEntities().get(i).getClass() == Crazy.class) {
                Crazy tmp = (Crazy) pc.getEntities().get(i);
                tmp.setScared(false);
            }
        }
        gameObject.setSpriteTexture(new MovingSpriteTexture(new Image("player_normal.png")));
    }
}
