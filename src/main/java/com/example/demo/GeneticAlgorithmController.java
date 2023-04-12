package com.example.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.CrossoverType;
import com.example.entity.GeneticAlgorithm;
import com.example.entity.GenomeType;


/**
 * Runs a GA and serves its results at the request of a user.
 */
@RestController
public class GeneticAlgorithmController {
    
    /**
     * Runs a genetic algorithm with the provided parameters, stores the results, and returns the
     * best resulting genome to the user.
     * @return a string representation of the best genome upon termination of the GA.
     * @throws Exception
     */
    @GetMapping("/binaryga")
    public String runBinaryGeneticAlgorithm(
        @RequestParam int popSize,
        @RequestParam int gens,
        @RequestParam int binaryStringLength) throws Exception {
        List<Object> genomeInitialisationArgs = List.of(binaryStringLength);
        var binaryGa = new GeneticAlgorithm(popSize, gens, CrossoverType.ONEPOINT, 0.5, 0.5, binaryStringLength, GenomeType.BINARYSTRING);

        binaryGa.run(genomeInitialisationArgs);
        
        return "TODO";
    }
}
