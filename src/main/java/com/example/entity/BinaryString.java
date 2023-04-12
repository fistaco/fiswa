import java.rmi.UnexpectedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Represents a binary string by tracking a fixed-size array of integers that are assumed to be
 * either 0 or 1.
 */
public class BinaryString extends Genome {

    public int[] bits;

    /**
     * Constructs a binary string of the given {@code size} initialised with
     * zeros.
     * 
     * @param size
     */
    public BinaryString(int size) {
        this.bits = new int[size];
        for (int i = 0; i < size; i++) {
            this.bits[i] = 0;
        }
    }

    /**
     * Flips a randomly selected bit of this binary string with the given probability.
     */
    @Override
    public void mutate() {
        int randomIndex = (int) (Math.random() * this.bits.length);
        this.bits[randomIndex] ^= 1;  // 0 XOR 1 == 1, 1 XOR 1 == 0
    }

    /**
     * Constructs and returns children from this and the given {@code other} binary string according
     * to the given {@code crossoverType}.
     */
    @Override
    public ArrayList<Genome> crossover(Genome other, CrossoverType crossoverType) throws Exception {
        if (crossoverType == CrossoverType.ONEPOINT) {
            return this.onepointCrossover((BinaryString) other);
        }
        if (crossoverType == CrossoverType.UNIFORM) {
            return this.uniformCrossover((BinaryString) other);
        }

        throw new Exception("Unexpected crossover type given.");
    }

    /**
     * Performs crossover where the first child is constructed from this binary string's bits until
     * a random cutoff point and the {@code other} parent's bits after that point, while the second
     * child is constructed with the complementing bits.
     * 
     * @param other
     * @return
     */
    private ArrayList<Genome> onepointCrossover(BinaryString other) {
        var child0 = new BinaryString(this.bits.length);
        var child1 = new BinaryString(this.bits.length);

        int cutoff = (int) (Math.random() * this.bits.length);  // cutoff point in the range [0, n)

        for (int i = 0; i < this.bits.length; i++) {
            // Assign one of the parent's bits according to whether we've passed the cutoff point
            child0.bits[i] = i <= cutoff ? this.bits[i] : other.bits[i];
            child1.bits[i] = i <= cutoff ? other.bits[i] : this.bits[i];
        }        

        return new ArrayList<>(List.of(child0, child1));
    }

    /**
     * Performs crossover where each bit of the first child is randomly taken from one parent, while
     * the second child's bit at that position is taken from the {@code other} parent.
     * 
     * @param other
     * @return
     */
    private ArrayList<Genome> uniformCrossover(BinaryString other) {
        var child0 = new BinaryString(this.bits.length);
        var child1 = new BinaryString(this.bits.length);

        for (int i = 0; i < this.bits.length; i++) {
            int r = (int) (Math.random() * 2);  // Choose a random parent

            // Assign the chosen parent's bit to the 1st child and the other parent's bit to the 2nd
            child0.bits[i] = r == 0 ? this.bits[i] : other.bits[i];
            child1.bits[i] = r == 0 ? other.bits[i] : this.bits[i];
        }    

        return new ArrayList<>(List.of(child0, child1));
    }

    @Override
    public BinaryString clone() {
        BinaryString clone = new BinaryString(this.bits.length);
        for (int i = 0; i < this.bits.length; i ++) {
            clone.bits[i] = this.bits[i];
        }

        return clone;
    }

    @Override
    public void print() {
        System.out.print("[");
        for (int i : bits) {
            System.out.print(i + " ");
        }
        System.out.print("]\n");
    }
    
    /**
     * Returns a binary strings of the given {@code size} with its bits randomly set to 0 or 1.
     * 
     * @param size
     */
    public static BinaryString random(Random rng, int size) {
        BinaryString binString = new BinaryString(size);

        for (int i = 0; i < size; i ++) {
            binString.bits[i] = rng.nextInt(2);
        }

        return binString;
    }

    /**
     * Computes and sets this binary string's fitness as the number of its bits that are set to 1.
     */
    @Override
    public float computeFitness() {
        float fitness = 0.0f;

        for (int i : bits) {
            fitness += i;
        }

        this.setFitness(fitness);
        return fitness;
    }
}
