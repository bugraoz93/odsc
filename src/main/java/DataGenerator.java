import com.yahoo.labs.samoa.instances.Instance;
import moa.core.InstanceExample;
import moa.streams.generators.RandomRBFGenerator;
import moa.tasks.StandardTaskMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class DataGenerator
{
    private static final Logger logger = (Logger) LogManager.getLogger(DataGenerator.class);
    private RandomRBFGenerator randomRBFGenerator;
    private FileWriter csvWriter;
    private ArrayList<Instance> instances;
    private ArrayList<InstanceExample> instanceExamples;

    public DataGenerator() {
        this.randomRBFGenerator = new RandomRBFGenerator();
        this.instances = new ArrayList<Instance>();
        this.instanceExamples = new ArrayList<InstanceExample>();
    }

    public void generateData(int featureNumber, int classNumber, int instRandSeed, int modelRandSeed,
                             int maxNumberData, boolean saveFile, String fileName) throws IOException {
        randomRBFGenerator.numAttsOption.setValue(featureNumber);
        randomRBFGenerator.numClassesOption.setValue(classNumber);
        assert instRandSeed != 0;
        randomRBFGenerator.instanceRandomSeedOption.setValue(instRandSeed);
        assert modelRandSeed != 0;
        randomRBFGenerator.modelRandomSeedOption.setValue(modelRandSeed);
        randomRBFGenerator.prepareForUse();

        int dataCounter = 0;
        if (saveFile)
        {
            try
            {
                this.csvWriter = new FileWriter(fileName + ".csv");
            }
            catch (IOException e)
            {
                logger.error("CSV file cannot be opened!!");
                throw e;
            }
        }
        while (randomRBFGenerator.hasMoreInstances() && dataCounter <= maxNumberData)
        {
            InstanceExample instanceExample = randomRBFGenerator.nextInstance();
            Instance instance = instanceExample.instance;
            if (saveFile)
            {
                try
                {
                    this.csvWriter.write(instance.toString() + "\n");
                }
                catch (IOException e)
                {
                    logger.error("CSV Writing operation is failed!!");
                    throw e;
                }
            }
            instanceExamples.add(instanceExample);
            instances.add(instance);
            logger.debug(instance);
            dataCounter++;
        }
    }

    public ArrayList<Instance> getInstances() {
        return instances;
    }

    public ArrayList<InstanceExample> getInstanceExamples() {
        return instanceExamples;
    }
}
