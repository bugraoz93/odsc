import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        Trainer trainer = new Trainer();
        trainer.trainFirst(false);
        trainer.trainRandom(3, true, false, false);
        trainer.trainRandom(3, false, true, false);
    }
}
