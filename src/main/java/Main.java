import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateData(10, 2, 0, 0,
                10000, false, "test");
        HT ht = new HT();
        ht.name = "HT";
//        ht.configure(0, 0.2F, 0.5F, 10, false, null);
//        ht.configure(0, 10000, 3, true, 0.1F, 0.000000001F);
        ht.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());

        NB nb = new NB();
        nb.name = "nb";
//        nb.configure(15);
        nb.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());
    }
}
