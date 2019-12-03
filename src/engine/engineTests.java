package engine;

import engine.physics.Hitbox;
import org.junit.Assert;
import org.junit.Test;

public class engineTests {
    @Test
    public void hitbox_Intersects() {
        Hitbox hitbox1 = new Hitbox(0, 0, 20, 20);
        Hitbox hitbox2 = new Hitbox(10, 10, 20, 20);
        Assert.assertTrue(hitbox1.intersects(hitbox2));

        hitbox2 = new Hitbox(20, 20, 20, 20);
        Assert.assertFalse(hitbox1.intersects(hitbox2));

        hitbox2 = new Hitbox(10, 10, 1, 1);
        Assert.assertTrue(hitbox1.intersects(hitbox2));

        hitbox2 = new Hitbox(200, 200, 20, 20);
        Assert.assertFalse(hitbox1.intersects(hitbox2));

        hitbox2 = new Hitbox(-20, -20, 10, 10);
        Assert.assertFalse(hitbox1.intersects(hitbox2));
    }

    @Test
    public void hitbox_resize() {
        Hitbox hitbox1 = new Hitbox(0, 0, 200, 200);

        hitbox1.resize(20, 20);
        Assert.assertEquals(20, hitbox1.getWidth(), 0);
        Assert.assertEquals(20, hitbox1.getHeight(), 0);
    }

    @Test
    public void hitbox_resizeCenter() {
        Hitbox hitbox1 = new Hitbox(0, 0, 20, 40);
        hitbox1.resizeCenter((float) 0.5);
        Assert.assertEquals(hitbox1.getWidth(), 10, 0);
        Assert.assertEquals(hitbox1.getHeight(), 20, 0);

        Assert.assertEquals(hitbox1.getX(), 5, 0);
        Assert.assertEquals(hitbox1.getY(), 10, 0);
    }


}
