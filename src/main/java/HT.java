import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Prediction;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.InstanceExample;
import moa.core.Measurement;
import moa.evaluation.BasicMultiLabelPerformanceEvaluator;
import moa.tasks.Plot;
import moa.tasks.StandardTaskMonitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class HT
{
    private static final Logger logger = (Logger) LogManager.getLogger(HT.class);
    private HoeffdingTree hoeffdingTree;
    private Plot plot;
    private BasicMultiLabelPerformanceEvaluator basicMultiLabelPerformanceEvaluator;
    private StandardTaskMonitor standardTaskMonitor;

    public HT() 
    {
        standardTaskMonitor = new StandardTaskMonitor();
        hoeffdingTree = new HoeffdingTree();
        hoeffdingTree.prepareForUse(standardTaskMonitor, null);

        basicMultiLabelPerformanceEvaluator = new BasicMultiLabelPerformanceEvaluator();

        plot = new Plot();
        plot.prepareForUse(standardTaskMonitor, null);
        plot.xTitleOption.setValue("Accuracy:");
        plot.yTitleOption.setValue("Data number:");
    }
    
    public void train(ArrayList<Instance> instances, ArrayList<InstanceExample> instanceExamples) throws IOException {
        Instance back = instances.get(0);
        Instance current = instances.get(1);
        for (int i = 2; i < instances.size(); i++)
        {
            hoeffdingTree.trainOnInstanceImpl(back);
            Prediction prediction = hoeffdingTree.getPredictionForInstance(current);
            basicMultiLabelPerformanceEvaluator.addResult(instanceExamples.get(i), prediction);
            logger.debug(prediction.getVotes());
            Measurement[] measurement = basicMultiLabelPerformanceEvaluator.getPerformanceMeasurements();
            logger.info(measurement[0] + ", " + measurement[1] + ", " + measurement[2] + ", " + measurement[3]
                    + ", " + measurement[4] + ", " + measurement[5]);
            plot.xColumnOption.setValue((int) measurement[1].getValue());
            plot.yColumnOption.setValue(i);
            back = current;
            current = instances.get(i);
        }

        File file = plot.plotOutputOption.getFile();
        File file1 = plot.outputFileOption.getFile();
        logger.info("");
    }
}
