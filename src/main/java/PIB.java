import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "p_i_b")
public class PIB implements Serializable {
    private static final long serialVersionUID = 7565548417348465897L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column
    byte team;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "battle_id", referencedColumnName = "id", nullable = false)
    Battle battle;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id", nullable = false)
    Player player;

    public int getTeam() {
        return (int) team;
    }

    public void setTeam(int team) {
        this.team = (byte) team;
    }

    public Battle getBattle() {
        return battle;
    }

    public void setBattle(Battle battle) {
        this.battle = battle;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
