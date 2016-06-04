package cyril_and_methodius.bukvar.users;

/**
 * Created by teo on 04/06/16.
 */
public class User {
    public static final int LEVEL_ONE_POINTS  = 30;
    private int levelOneCurrentPoints;

    public User(){
        this.levelOneCurrentPoints = 0;
    }

    public int getLevelOneCurrentPoints() {
        return levelOneCurrentPoints;
    }

    public void setLevelOneCurrentPoints(int levelOneCurrentPoints) {
        this.levelOneCurrentPoints += levelOneCurrentPoints;
    }
}
