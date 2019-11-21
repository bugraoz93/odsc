import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateData(10, 2, 15, 100,
                10000, false, "test");
        HT ht = new HT();

        ht.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());
    }
}
