import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "battles")
public class Battle implements Serializable {
    private static final long serialVersionUID = 1985100973166286289L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    Battle(long battleId) {
        this.battleId = battleId;
    }

    Battle() {
    }

    static Battle getBattle(long battleId) {
        Battle battle;
        Session session = HibernateFactory.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("from Battle where battleId = :battleId");
        query.setParameter("battleId", battleId);
        List list = query.getResultList();
        if (!list.isEmpty()) {
            battle = (Battle) query.getSingleResult();
        } else {
            battle = new Battle(battleId);
            session.saveOrUpdate(battle);
            transaction.commit();
        }
        session.close();
        return battle;
    }


    public long getBattleId() {
        return battleId;
    }

    public void setBattleId(long battleId) {
        this.battleId = battleId;
    }

    @Column(name = "battle_id", unique = true, nullable = false)
    long battleId;
}
