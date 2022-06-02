public class Cases {
    static int coordonnéex;
    static int coordonnéey;
    static int murs; //stocker sous un entier, les centaines indiques des murs en biais, les dizaines, les murs d'ouest et les unités, les murs au nords

    public Cases(){
        setCoordonnéex(Game.xi);
        setCoordonnéey(Game.yi);
        setMurs(Game.mur[Game.xi][Game.yi]);
    }

    public static void setCoordonnéey(int coordonnéey) {
        Cases.coordonnéey = coordonnéey;
    }

    public static void setCoordonnéex(int coordonnéex) {
        Cases.coordonnéex = coordonnéex;
    }

    public static void setMurs(int murs) {
        Cases.murs = murs;
    }

    public static int getCoordonnéex() {
        return coordonnéex;
    }

    public static int getCoordonnéey() {
        return coordonnéey;
    }
    public static int getMurs(){
        return murs;
    }
}
