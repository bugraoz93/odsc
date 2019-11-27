import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.bayes.NaiveBayes;
import moa.core.InstanceExample;

import java.io.IOException;
import java.util.ArrayList;

public class NB extends Model
{
    private NaiveBayes naiveBayes;

    public NB()
    {
        naiveBayes = new NaiveBayes();
    }

    public void configure(int randomSeed)
    {
        this.naiveBayes.setRandomSeed(randomSeed);
    }

    public void train(ArrayList<Instance> instances, ArrayList<InstanceExample> instanceExamples) throws IOException {
        super.train(this.naiveBayes, instances, instanceExamples);
    }

    @Override
    public AbstractClassifier getModel()
    {
        return this.naiveBayes;
    }
}
