import com.yahoo.labs.samoa.instances.Instance;
import com.yahoo.labs.samoa.instances.Prediction;
import moa.classifiers.AbstractClassifier;
import moa.core.InstanceExample;
import moa.core.Measurement;
import moa.evaluation.BasicMultiLabelPerformanceEvaluator;
import moa.tasks.Plot;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.ArrayList;

public class Model
{
    private static final Logger logger = (Logger) LogManager.getLogger(Model.class);
    private Plot plot;
    private BasicMultiLabelPerformanceEvaluator basicMultiLabelPerformanceEvaluator;
    private double finalAcc;
    private double currentAcc;
    public String name;


    public Model()
    {
        basicMultiLabelPerformanceEvaluator = new BasicMultiLabelPerformanceEvaluator();

        plot = new Plot();
        plot.prepareForUse();
        plot.xTitleOption.setValue("Accuracy:");
        plot.yTitleOption.setValue("Data number:");
    }

    public void train(AbstractClassifier abstractClassifier, ArrayList<Instance> instances,
                      ArrayList<InstanceExample> instanceExamples)
    {
        abstractClassifier.prepareForUse();
        int allCorrect = 0;
        Instance back = instances.get(0);
        Instance current = instances.get(1);
        for (int i = 2; i < instances.size(); i++)
        {
            abstractClassifier.trainOnInstanceImpl(back);
            Prediction prediction = abstractClassifier.getPredictionForInstance(current);

            if (abstractClassifier.correctlyClassifies(current))
                allCorrect++;

            logger.debug("Current Prediction is " + abstractClassifier.correctlyClassifies(current));
            basicMultiLabelPerformanceEvaluator.addResult(instanceExamples.get(i), prediction);
            logger.debug(prediction.getVotes());

            Measurement[] measurement = basicMultiLabelPerformanceEvaluator.getPerformanceMeasurements();
            logger.debug(measurement[0] + ", " + measurement[1] + ", " + measurement[2] + ", " + measurement[3]
                    + ", " + measurement[4] + ", " + measurement[5]);
            this.currentAcc = (allCorrect * 1.0 / instances.size()) * 100.0;
            plot.xColumnOption.setValue((int) this.currentAcc);
            plot.yColumnOption.setValue(i);

            back = current;
            current = instances.get(i);
        }
        finalAcc = (allCorrect * 1.0 / instances.size()) * 100.0;
        logger.info("Final ACC for the model " + this.name + ": " + finalAcc);
        logger.info("All Correct Answers for the model " + this.name + ": "+ allCorrect + "\n\n");
//        File file = plot.plotOutputOption.getFile();
//        File file1 = plot.outputFileOption.getFile();
//        logger.info("");
    }

    public double getFinalAcc() {
        return finalAcc;
    }

    public AbstractClassifier getModel()
    {
        throw new IllegalStateException();
    }
}
