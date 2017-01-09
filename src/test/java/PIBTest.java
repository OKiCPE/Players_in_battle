import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

public class PIBTest {

    @AfterClass
    public static void HibernateClose() {
        HibernateFactory.close();
    }

    @Test
    public void getBattleTest() {
        Battle battle = Battle.getBattle(123);
        Assert.assertNotNull(battle);
    }

    @Test
    public void PIBTest() {
        PIB pib = new PIB();
        pib.setPlayer(Player.getPlayer("a"));
        pib.setBattle(Battle.getBattle(33));
        Session session = HibernateFactory.getSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(pib);
        transaction.commit();
        session.close();
    }

    @Test
    public void getPlayerTest() {
        Player player = Player.getPlayer("test");
        Assert.assertNotNull(player);
    }

}
