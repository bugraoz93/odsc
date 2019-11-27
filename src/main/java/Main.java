import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Trainer trainer = new Trainer();
        trainer.trainFirst();
        trainer.trainRandom(3, true, false);
        trainer.trainRandom(3, false, true);
    }
}
