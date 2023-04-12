import java.util.List;
import java.util.Random;

public class GenomeFactory {
    private Random random;

    public GenomeFactory(Random random) {
        this.random = random;
    }

    public GenomeFactory() {
        this.random = new Random();
    }

    public Genome randomGenome(GenomeType genomeType, List<Object> args) {
        if (genomeType == GenomeType.BINARYSTRING) {
            return this.randomBinaryString(args);
        }

        return null;
    }

    /**
     * Returns a binary string object with a size defined in the first item of the given list of
     * {@code args} and bits randomly set to 0 or 1.
     * 
     * @param args
     * @return
     */
    private BinaryString randomBinaryString(List<Object> args) {
        int size = (int) args.get(0);
        return BinaryString.random(this.random, size);
    }
}
