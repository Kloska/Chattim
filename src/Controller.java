public class Controller {

    public static void main(String[] args) {
        Model m = new Model();
        m.hostSession(4000);
        m.connect("127.0.0.1", 4000);

    }

}
