import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "players")
public class Player implements Serializable {
    private static final long serialVersionUID = 4200551546520122427L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(unique = true, nullable = false)
    String login;

    Player(String login) {
        this.login = login;
    }

    Player() {
    }

    static Player getPlayer(String login) {
        Player player;
        Session session = HibernateFactory.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Player where login = :login");
        query.setParameter("login", login);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            player = (Player) query.getSingleResult();
        } else {
            player = new Player(login);
            session.saveOrUpdate(player);
            transaction.commit();
        }
        session.close();
        return player;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
