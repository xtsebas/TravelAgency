package Model;

public interface Igraph {
    /**
     * @return the distancias
     */
    public int[][] getDistancias();

    /**
     * @param distancias the distancias to set
     */
    public void setDistancias(int[][] distancias);

    /**
     * @return the recorridos
     */
    public String[][] getRecorridos();

    /**
     * @param recorridos the recorridos to set
     */
    public void setRecorridos(String[][] recorridos);

    /**
     * @return the sIZE
     */
    public int getSIZE();

    /**
     * @param sIZE the sIZE to set
     */
    public void setSIZE(int sIZE);

    public void CalcularRutas();
}
