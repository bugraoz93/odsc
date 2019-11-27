import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.IOException;
import java.util.Random;

public class Trainer
{
    private static final Logger logger = (Logger) LogManager.getLogger(Trainer.class);
    private int featureNumber;
    private int classNumber;

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

    private void setAttrsDefault()
    {
        this.featureNumber = 10;
        this.classNumber = 2;
    }

    public void trainFirst() throws IOException
    {
        this.setAttrsDefault();
        logger.info("Model started to train for FeatureNumber: " + this.featureNumber + " and ClassNumber: " + this.classNumber);
        this.train(this.featureNumber, this.classNumber, 0, 0, 10000, false);
    }

    public void trainRandom(int numOfTrail, boolean isFeature, boolean isClass) throws IOException
    {
        Random random = new Random();
        for (int i = 0; i < numOfTrail; i++)
        {
            this.setAttrsDefault();
            if (isFeature)
                this.featureNumber = random.nextInt((15 - 5) + 1) + 5;
            if (isClass)
                this.classNumber = random.nextInt((6 - 2) + 1) + 2;
            logger.info("Model started to train for FeatureNumber: " + this.featureNumber
                    + " and ClassNumber: " + this.classNumber);
            this.train(this.featureNumber, this.classNumber, 0, 0, 10000, false);
        }
    }
}
