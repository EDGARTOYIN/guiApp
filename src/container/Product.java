package container;
import java.util.Random;

public class Product {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int ID_LENGTH = 7;
    private String id;
    private String nombre;
    private double precio;
    private String desc;
    private int cantidad;
    private String categoria;

    public Product(String nombre, double precio, String descripcion, int cantidad, String categoria){
        this.nombre = nombre;
        this.precio = precio;
        this.desc = descripcion;
        this.cantidad = cantidad;
        this.categoria = categoria;
        this.id = generateRandomId();
    }

    private String generateRandomId() {
        StringBuilder randomId = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            randomId.append(CHARACTERS.charAt(randomIndex));
        }

        return randomId.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }


} 