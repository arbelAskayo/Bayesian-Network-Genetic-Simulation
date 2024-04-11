public class Mouse {

    private Generation generation;
    private String genoType;


    public Mouse(Generation generation) {

        this.generation = generation;

    }


    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

    public String getGenoType() {
        return genoType;
    }

    public void setGenoType(String genoType) {
        this.genoType = genoType;
    }
}
