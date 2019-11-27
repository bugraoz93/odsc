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
        ht.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());

        NB nb = new NB();
        nb.name = "nb";
        nb.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());
    }
}
