import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.AbstractClassifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.InstanceExample;

import java.io.IOException;
import java.util.ArrayList;

public class HT extends Model
{
    private HoeffdingTree hoeffdingTree;

    public HT() 
    {
        hoeffdingTree = new HoeffdingTree();
    }

    public void configure(int byteSize, int mem, int gracePeriod, boolean removePoorAtts, float tieThresholdOption, float splitConfidence)
    {
        assert byteSize != 0;
        this.hoeffdingTree.maxByteSizeOption.setValue(byteSize);
        assert mem != 0;
        this.hoeffdingTree.memoryEstimatePeriodOption.setValue(mem);
        assert gracePeriod != 0;
        this.hoeffdingTree.gracePeriodOption.setValue(gracePeriod);
        assert tieThresholdOption != 0;
        this.hoeffdingTree.tieThresholdOption.setValue(tieThresholdOption);
        assert splitConfidence > 0;
        this.hoeffdingTree.splitConfidenceOption.setValue(splitConfidence);
        this.hoeffdingTree.removePoorAttsOption.setValue(removePoorAtts);
    }

    public void train(ArrayList<Instance> instances, ArrayList<InstanceExample> instanceExamples) throws IOException {
        super.train(this.hoeffdingTree, instances, instanceExamples);
    }

    @Override
    public AbstractClassifier getModel()
    {
        return this.hoeffdingTree;
    }
}
