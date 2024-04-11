//EX03_208642868_207850074

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BayesianNetworkSimulation {

    private static final Random random = new Random();
    private static final int SAMPLES_1000 = 1000;
    private static final int SAMPLES_10000 = 10000;
    private static final int SAMPLES_100000 = 100000;

    private static final Map<String, Double> firstGenProb = new HashMap<>();
    private static final Map<String, Map<String, Double>> secondAndThirdGenProb = new HashMap<>();
    private static final Map<String, Map<String, Double>> fourthGenProb = new HashMap<>();

    static {
        // Initialize probabilities for the first generation
        firstGenProb.put("pp", 0.99);
        firstGenProb.put("pd", 0.01);

        // Initialize probabilities for second and third generations
        Map<String, Double> pdPd = new HashMap<>();
        pdPd.put("pd", 0.67);
        pdPd.put("pp", 0.33);
        secondAndThirdGenProb.put("pd-pd", pdPd);

        Map<String, Double> pdPp = new HashMap<>();
        pdPp.put("pd", 0.5);
        pdPp.put("pp", 0.5);
        secondAndThirdGenProb.put("pd-pp", pdPp);
        secondAndThirdGenProb.put("pp-pd",pdPp);

        Map<String, Double> ppPp = new HashMap<>();
        ppPp.put("pd", 0.0);
        ppPp.put("pp", 1.0);
        secondAndThirdGenProb.put("pp-pp", ppPp);

        // Initialize probabilities for the fourth generation
        Map<String, Double> pdPdFourth = new HashMap<>();
        pdPdFourth.put("dd", 0.25);
        pdPdFourth.put("pd", 0.5);
        pdPdFourth.put("pp", 0.25);
        fourthGenProb.put("pd-pd", pdPdFourth);

        Map<String, Double> pdPpFourth = new HashMap<>();
        pdPpFourth.put("dd", 0.0);
        pdPpFourth.put("pd", 0.5);
        pdPpFourth.put("pp", 0.5);
        fourthGenProb.put("pp-pd", pdPpFourth);
        fourthGenProb.put("pd-pp", pdPpFourth);

        // Assuming that "pp-pp" will not produce "dd" offspring
        Map<String, Double> ppPpFourth = new HashMap<>();
        ppPpFourth.put("dd", 0.0);
        ppPpFourth.put("pd", 0.0);
        ppPpFourth.put("pp", 1.0);
        fourthGenProb.put("pp-pp", ppPpFourth);
    }

    public static void main(String[] args) {
        // Run simulations
        simulate(SAMPLES_1000,0);
        simulate(SAMPLES_10000,0);
        simulate(SAMPLES_100000,0);
        simulate(SAMPLES_1000,1);
        simulate(SAMPLES_10000,1);
        simulate(SAMPLES_100000,1);
        calcEnumeration();
    }
private static void calcEnumeration (){
    double[] carrierProbabilities = new double[11];
    double[] conditionalCarrierProbabilities = new double[11];
    double totalProbLouisSick = 0.0;

    for (String aliceGenotype : new String[] {"pp", "pd"}) {
        double probAlice = firstGenProb.get(aliceGenotype);

        for (String bobGenotype : new String[] {"pp", "pd"}) {
            double probBob= firstGenProb.get(bobGenotype);
            for (String cindyGenotype : new String[] {"pp", "pd"}) {
                double probCindy= firstGenProb.get(cindyGenotype);

                for (String daveGenotype : new String[] {"pp", "pd"}) {
                    double probDave= firstGenProb.get(daveGenotype);

                    for (String ellenGenotype : new String[] {"pp", "pd"}) {
                        double probEllen= firstGenProb.get(ellenGenotype);
//second and third generations
                        for (String fredGenotype : new String[] {"pp", "pd"}) {
                            double probFred = secondAndThirdGenProb.get(aliceGenotype + "-" + bobGenotype).get(fredGenotype);

                            for (String gwenGenotype : new String[] {"pp", "pd"}) {
                                double probGwen = secondAndThirdGenProb.get(bobGenotype + "-" + cindyGenotype).get(gwenGenotype);

                                for (String henryGenotype : new String[] {"pp", "pd"}) {
                                    double probHenry = secondAndThirdGenProb.get(cindyGenotype + "-" + daveGenotype).get(henryGenotype);

                                    for (String ionaGenotype : new String[] {"pp", "pd"}) {
                                        double probIona = secondAndThirdGenProb.get(bobGenotype + "-" + ellenGenotype).get(ionaGenotype);

                                        for (String johnGenotype : new String[] {"pp", "pd"}) {
                                            double probJohn = secondAndThirdGenProb.get(fredGenotype + "-" + gwenGenotype).get(johnGenotype);

                                            for (String katherineGenotype : new String[] {"pp", "pd"}) {
                                                double probKatherine = secondAndThirdGenProb.get(henryGenotype + "-" + ionaGenotype).get(katherineGenotype);


                                                double probLouis = fourthGenProb.getOrDefault(johnGenotype + "-" + katherineGenotype, new HashMap<>()).getOrDefault("dd", 0.0);



// Multiply all probabilities together to get the joint probability for this combination
                                                double jointProbability = probAlice * probBob * probCindy * probDave * probEllen * probFred * probGwen * probHenry * probIona * probJohn * probKatherine * probLouis;


                                                if (probLouis>0) {
                                                    totalProbLouisSick += jointProbability;

                                                    // Update joint probability that each ancestor is a carrier and Louis is sick
                                                    if ("pd".equals(aliceGenotype)) {
                                                        carrierProbabilities[0]+=jointProbability;
                                                    }
                                                    if ("pd".equals(bobGenotype)) {
                                                        carrierProbabilities[1]+=jointProbability;
                                                    }
                                                    if ("pd".equals(cindyGenotype)) {
                                                        carrierProbabilities[2]+=jointProbability;
                                                    }
                                                    if ("pd".equals(daveGenotype)) {
                                                        carrierProbabilities[3]+=jointProbability;
                                                    }
                                                    if ("pd".equals(ellenGenotype)) {
                                                        carrierProbabilities[4]+=jointProbability;
                                                    }
                                                    if ("pd".equals(fredGenotype)) {
                                                        carrierProbabilities[5]+=jointProbability;
                                                    }
                                                    if ("pd".equals(gwenGenotype)) {
                                                        carrierProbabilities[6]+=jointProbability;
                                                    }
                                                    if ("pd".equals(henryGenotype)) {
                                                        carrierProbabilities[7]+=jointProbability;
                                                    }
                                                    if ("pd".equals(ionaGenotype)) {
                                                        carrierProbabilities[8]+=jointProbability;
                                                    }
                                                    if ("pd".equals(johnGenotype)) {
                                                        carrierProbabilities[9]+=jointProbability;
                                                    }
                                                    if ("pd".equals(katherineGenotype)) {
                                                        carrierProbabilities[10]+=jointProbability;
                                                    }




                                                    }



                                                }}}}}}}}}}}

    for (int i=0;i<=10;i++) {
        conditionalCarrierProbabilities[i]=(carrierProbabilities[i]/totalProbLouisSick);
    }
    System.out.println("Enumeration:");
    System.out.println("alice carrier probability: " + conditionalCarrierProbabilities[0]);
    System.out.println("bob carrier probability: " + conditionalCarrierProbabilities[1]);
    System.out.println("cindy carrier probability: " + conditionalCarrierProbabilities[2]);
    System.out.println("dave carrier probability: " + conditionalCarrierProbabilities[3]);
    System.out.println("ellen carrier probability: " + conditionalCarrierProbabilities[4]);
    System.out.println("fred carrier probability: " + conditionalCarrierProbabilities[5]);
    System.out.println("gwen carrier probability: " + conditionalCarrierProbabilities[6]);
    System.out.println("henry carrier probability: " + conditionalCarrierProbabilities[7]);
    System.out.println("iona carrier probability: " + conditionalCarrierProbabilities[8]);
    System.out.println("john carrier probability: " + conditionalCarrierProbabilities[9]);
    System.out.println("katherine carrier probability: " + conditionalCarrierProbabilities[10]);






                                            }
    private static void simulate(int samples, int isWeighted) {
        int louisSickCount = 0;
        Double totalWeight=0.0;
        double[] carrierWeights = new double[11];
        int[] carrierCounts = new int[11]; // Index 0 for Alice, 1 for Bob, etc.

        for (int i = 0; i < samples; i++) {
            //initializing all the mice
            Mouse alice = new Mouse(Generation.FIRST);
            Mouse bob = new Mouse(Generation.FIRST);
            Mouse cindy = new Mouse(Generation.FIRST);
            Mouse dave = new Mouse(Generation.FIRST);
            Mouse ellen = new Mouse(Generation.FIRST);
            Mouse fred = new Mouse(Generation.SECOND);
            Mouse gwen = new Mouse(Generation.SECOND);
            Mouse henry = new Mouse(Generation.SECOND);
            Mouse iona = new Mouse(Generation.SECOND);
            Mouse john = new Mouse(Generation.THIRD);
            Mouse katherine = new Mouse(Generation.THIRD);
            Mouse louis = new Mouse(Generation.FOURTH);
            // Sample genotypes for each mouse
            //First gen
            sampleGenotype(alice,"","");
            sampleGenotype(bob,"","");
            sampleGenotype(cindy,"","");
            sampleGenotype(dave,"","");
            sampleGenotype(ellen,"","");
            //Second gen
            sampleGenotype(fred, alice.getGenoType(), bob.getGenoType());
            sampleGenotype(gwen, cindy.getGenoType(), bob.getGenoType());
            sampleGenotype(henry, cindy.getGenoType(), dave.getGenoType());
            sampleGenotype(iona, ellen.getGenoType(), bob.getGenoType());
            //Third gen
            sampleGenotype(john, gwen.getGenoType(), fred.getGenoType());
            sampleGenotype(katherine, henry.getGenoType(), iona.getGenoType());
            //Fourth gen
            sampleGenotype(louis, john.getGenoType(), katherine.getGenoType());
            // Calculate weight for this sample
            double weight = calculateWeight(louis, john, katherine);


//updating weights if weighted
            if(isWeighted!=0&&(weight!=0.0)){
                totalWeight+=weight;
                louisSickCount++;
                //we are adding the right weights and if louis is not sick we are reject the sample which is exactly the same as given it a weight of zero.
                if (alice.getGenoType().equals("pd")) carrierWeights[0]+=weight;
                if (bob.getGenoType().equals("pd")) carrierWeights[1]+=weight;
                if (cindy.getGenoType().equals("pd")) carrierWeights[2]+=weight;
                if (dave.getGenoType().equals("pd")) carrierWeights[3]+=weight;
                if (ellen.getGenoType().equals("pd")) carrierWeights[4]+=weight;
                if (fred.getGenoType().equals("pd")) carrierWeights[5]+=weight;
                if (gwen.getGenoType().equals("pd")) carrierWeights[6]+=weight;
                if (henry.getGenoType().equals("pd")) carrierWeights[7]+=weight;
                if (iona.getGenoType().equals("pd")) carrierWeights[8]+=weight;
                if (john.getGenoType().equals("pd")) carrierWeights[9]+=weight;
                if (katherine.getGenoType().equals("pd")) carrierWeights[10]+=weight;


            }


            // If Louis is sick, count carrier status for each ancestor
            if (louis.getGenoType().equals("dd")&&isWeighted==0) {
                louisSickCount++;
                if (alice.getGenoType().equals("pd")) carrierCounts[0]++;
                if (bob.getGenoType().equals("pd")) carrierCounts[1]++;
                if (cindy.getGenoType().equals("pd")) carrierCounts[2]++;
                if (dave.getGenoType().equals("pd")) carrierCounts[3]++;
                if (ellen.getGenoType().equals("pd")) carrierCounts[4]++;
                if (fred.getGenoType().equals("pd")) carrierCounts[5]++;
                if (gwen.getGenoType().equals("pd")) carrierCounts[6]++;
                if (henry.getGenoType().equals("pd")) carrierCounts[7]++;
                if (iona.getGenoType().equals("pd")) carrierCounts[8]++;
                if (john.getGenoType().equals("pd")) carrierCounts[9]++;
                if (katherine.getGenoType().equals("pd")) carrierCounts[10]++;

            }
        }


        double[] carrierProbabilities = new double[11];
        // Calculate probabilities - rejection sampling
        if(isWeighted==0){
        for (int i = 0; i < carrierCounts.length; i++) {
            carrierProbabilities[i] = ((double) carrierCounts[i]) / ((double) louisSickCount);
        }}

        // Calculate probabilities - importance sampling
        else{
        for (int i = 0; i < carrierWeights.length; i++) {
            carrierProbabilities[i] = carrierWeights[i] / totalWeight;
        }}

        // Output results
        if(isWeighted!=0) {
            System.out.println("Importance Sampling results (likelihood sampling)");
        }
        else{
            System.out.println("Rejection Sampling results");
        }
        System.out.println("Simulation for " + samples + " samples:");
        System.out.println("alice carrier probability: " + carrierProbabilities[0]);
        System.out.println("bob carrier probability: " + carrierProbabilities[1]);
        System.out.println("cindy carrier probability: " + carrierProbabilities[2]);
        System.out.println("dave carrier probability: " + carrierProbabilities[3]);
        System.out.println("ellen carrier probability: " + carrierProbabilities[4]);
        System.out.println("fred carrier probability: " + carrierProbabilities[5]);
        System.out.println("gwen carrier probability: " + carrierProbabilities[6]);
        System.out.println("henry carrier probability: " + carrierProbabilities[7]);
        System.out.println("iona carrier probability: " + carrierProbabilities[8]);
        System.out.println("john carrier probability: " + carrierProbabilities[9]);
        System.out.println("katherine carrier probability: " + carrierProbabilities[10]);
        System.out.println("");
        System.out.println("Percentage of samples where Louis is sick: "
                + ((double) louisSickCount / samples * 100) + "%");
        System.out.println("");
    }
    // Method to calculate the weight of a sample
    private static Double calculateWeight(Mouse louis, Mouse parent1, Mouse parent2) {
        if (parent1.getGenoType().equals("pd") && parent2.getGenoType().equals("pd")) {
            return 0.25; // Probability of sick Louis given both parents are carriers
        }
        return 0.0; // Indicate that this combination of genotypes cannot produce a sick Louis
    }
    private static void sampleGenotype(Mouse mouse,String parent1, String parent2) {
        // This method set "dd", "pd", or "pp" based on the input genotypes

        Random random =new Random();
        float rand = random.nextFloat();
         if(mouse.getGeneration()==Generation.FIRST){
             if(0.99 >= rand){
                mouse.setGenoType("pp");
             }
             else{
                 mouse.setGenoType("pd");
             }

         }
        else if(mouse.getGeneration()==Generation.SECOND||mouse.getGeneration()==Generation.THIRD){
             if(parent1=="pd" && parent2=="pd"){
                 if(0.67 >= rand){
                     mouse.setGenoType("pd");
                 }
                 else{
                     mouse.setGenoType("pp");
                 }
             }
             else if ((parent1=="pd" && parent2=="pp")||(parent2=="pd" && parent1=="pp")){
                 if(0.5 >= rand){
                     mouse.setGenoType("pd");
                 }
                 else{
                     mouse.setGenoType("pp");
                 }

             }

             else { //"pp" "pp"
                 if(0 >= rand){
                     mouse.setGenoType("pd");
                 }
                 else{
                     mouse.setGenoType("pp");
                 }
             }

         }
         else{//fourth generation (louis)
             if(parent1=="pd" && parent2=="pd"){
                 if(0.25 >= rand){
                     mouse.setGenoType("dd");
                 }
                 else if (rand<=0.75 && rand>0.25){
                     mouse.setGenoType("pd");
                 }
                 else{
                     mouse.setGenoType("pp");

                 }
             }
             else if ((parent1=="pd" && parent2=="pp")||(parent2=="pd" && parent1=="pp")){
                 if(0 >= rand){
                     mouse.setGenoType("dd");
                 }
                 else if (rand<=0.5 && rand>0){
                     mouse.setGenoType("pd");
                 }
                 else{
                     mouse.setGenoType("pp");

                 }

             }

             else { //"pp" "pp"
                 mouse.setGenoType("pp");
             }

         }
        return;
    }






}
