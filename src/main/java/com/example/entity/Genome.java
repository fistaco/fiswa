import java.util.ArrayList;

public abstract class Genome {
    private float fitness;

    public abstract float computeFitness();

    public float getFitness() {
        return this.fitness;
    };

    public void setFitness(float fitness) {
        this.fitness = fitness;
    }
    
    public abstract void mutate();
    public abstract ArrayList<Genome> crossover(Genome other, CrossoverType crossoverType) throws Exception;

    public abstract Genome clone();

    public abstract void print();
}
