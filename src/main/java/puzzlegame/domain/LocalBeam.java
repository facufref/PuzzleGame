/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzlegame.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author facuf
 */
public class LocalBeam {

    int states;
    Puzzle initialPuzzle;
    List<Puzzle> currentIteration;

    public LocalBeam(int states, Puzzle puzzle) {
        this.states = states;
        this.initialPuzzle = puzzle;
        this.currentIteration = new ArrayList<>();
    }

    public List<Puzzle> getCurrentIteration() {
        return currentIteration;
    }

    public void generateNextIteration() {
        List<Puzzle> nextSteps = new ArrayList<>();
        
        if (currentIteration.isEmpty()) {
            nextSteps.addAll(initialPuzzle.getNextSteps());
        } else {
            for (Iterator<Puzzle> p = currentIteration.iterator(); p.hasNext();) {
                nextSteps.addAll(p.next().getNextSteps());
            }
        }
        nextSteps.sort((step1, step2) -> step1.getManhattanDistance() - step2.getManhattanDistance());
        currentIteration = nextSteps.subList(0, (states < nextSteps.size()) ? states : nextSteps.size());
    }
    
    public Puzzle getCurrentBestPuzzle(){
        if(currentIteration.isEmpty())
            return initialPuzzle;
        return currentIteration.get(0);
    }
}
