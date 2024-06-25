# Bayesian Network Simulation

## Overview

This project simulates a Bayesian Network for genetic inheritance across multiple generations using rejection sampling and importance sampling. The goal is to calculate the probabilities of each ancestor being a carrier of a particular genotype given the conditions.

## Project Structure

- **BayesianNetworkSimulation.java**: Main Java class containing the implementation of the Bayesian Network simulation.
- **Mouse.java**: Class representing a mouse with genotype and generation attributes.
- **Generation.java**: Enum representing different generations (FIRST, SECOND, THIRD, FOURTH).

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher.

### Running the Simulation

1. **Compile the Java files**:

    ```sh
    javac BayesianNetworkSimulation.java Mouse.java Generation.java
    ```

2. **Run the main simulation**:

    ```sh
    java BayesianNetworkSimulation
    ```

## Simulation Details

### Classes and Methods

- **BayesianNetworkSimulation**:
  - `main(String[] args)`: Entry point of the simulation.
  - `simulate(int samples, int isWeighted)`: Runs the simulation for a given number of samples and type (weighted or not).
  - `calcEnumeration()`: Calculates the enumeration probabilities.
  - `sampleGenotype(Mouse mouse, String parent1, String parent2)`: Samples the genotype of a mouse based on its parents' genotypes.
  - `calculateWeight(Mouse louis, Mouse parent1, Mouse parent2)`: Calculates the weight of a sample.

- **Mouse**:
  - Represents a mouse with attributes for genotype and generation.

- **Generation**:
  - Enum representing the generation of a mouse (FIRST, SECOND, THIRD, FOURTH).

### Probability Maps

- **firstGenProb**: Probabilities for the first generation.
- **secondAndThirdGenProb**: Probabilities for the second and third generations.
- **fourthGenProb**: Probabilities for the fourth generation.

## Results

The simulation outputs the probabilities of each ancestor being a carrier of the "pd" genotype given that the fourth generation (Louis) is sick ("dd"). It uses both rejection sampling and importance sampling (likelihood weighting) to compute these probabilities.

### Example Output

```sh
Rejection Sampling results
Simulation for 1000 samples:
alice carrier probability: 0.2
bob carrier probability: 0.3
...

Importance Sampling results (likelihood sampling)
Simulation for 1000 samples:
alice carrier probability: 0.18
bob carrier probability: 0.28
...

Percentage of samples where Louis is sick: 1.5%
