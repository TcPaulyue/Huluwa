//package Test;
import java.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
public class TestPlayer {
    Field field;
    Player player;
    public TestPlayer()
    {
        field=new Field();
        player=new Player(100,100,"yeye",field);
        player.setenemys(field.getBadmans());
        player.setFriends(field.getGoodmans());
    }
    @BeforeClass
    public static void beforeTest()
    {
        System.out.println("Test Begin");
    }
    @AfterClass
    public static void afterTest()
    {
        System.out.println("Test Over");
    }
    @Test
    public void Testmove()
    {
        int x = 50;
        int y=40;
        if(player.testPlace(x,y)==true)
        {
            player.MovePlace(x,y);
        }
        if(player.testPlace(player.x(),player.y())==false)
        {
            fail("testPlace failed");
        }
        System.out.println("success");
    }
    @Test
    public void TesttestPlace()
    {
        int x=500000;
        int y=-1;
        if(player.testPlace(x,y)==true)
        {
            System.out.println("Success");
            return;
        }
        fail("TesttestPlace failed");
    }

}
