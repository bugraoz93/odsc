import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.Random;

public class Trainer
{
    private static final Logger logger = (Logger) LogManager.getLogger(Trainer.class);
    public Trainer() {
    }

    public void train(int featureNumber, int classNumber, int instRandSeed, int modelRandSeed, int maxNumberData, boolean saveFile) throws IOException
    {
        DataGenerator dataGenerator = new DataGenerator();
        dataGenerator.generateData(featureNumber, classNumber, instRandSeed, modelRandSeed,
                maxNumberData, saveFile, "test");

        HT ht = new HT();
        ht.name = "HT";
        ht.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());

        NB nb = new NB();
        nb.name = "NB";
        nb.train(dataGenerator.getInstances(), dataGenerator.getInstanceExamples());
    }
    public void trainRandom(int numOfTrail) throws IOException
    {
        int featureNumber = 10;
        int classNumber = 2;
        logger.info("Model started to train for FeatureNumber: " + featureNumber + " and ClassNumber: " + classNumber);
        this.train(featureNumber, classNumber, 0, 0, 10000, false);

        Random random = new Random();
        for (int i = 0; i < numOfTrail; i++)
        {
            featureNumber = random.nextInt((15 - 5) + 1) + 5;
            classNumber = random.nextInt((6 - 2) + 1) + 2;
            logger.info("Model started to train for FeatureNumber: " + featureNumber
                    + " and ClassNumber: " + classNumber + "\n\n\n");
            this.train(featureNumber, classNumber, 0, 0, 10000, false);
        }
    }
}
