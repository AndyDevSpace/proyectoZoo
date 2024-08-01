public class Animal {
    private String nombre;
    private String id;
    private String dieta;
    private int edad;
    private String sexo;
    private boolean vacunado;
    private boolean esterilizado;

    public Animal(String nombre, String id, String dieta, int edad, String sexo, boolean vacunado, boolean esterilizado) {
        this.nombre = nombre;
        this.id = id;
        this.dieta = dieta;
        this.edad = edad;
        this.sexo = sexo;
        this.vacunado = vacunado;
        this.esterilizado = esterilizado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean isVacunado() {
        return vacunado;
    }

    public void setVacunado(boolean vacunado) {
        this.vacunado = vacunado;
    }

    public boolean isEsterilizado() {
        return esterilizado;
    }

    public void setEsterilizado(boolean esterilizado) {
        this.esterilizado = esterilizado;
    }
}
