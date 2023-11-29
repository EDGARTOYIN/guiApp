package container;
import java.util.Vector;

public class ProductList {

    private Vector<Product> listaProductos;

    public ProductList() {
        this.listaProductos = new Vector<>();
        insertDefaultProducts();
    }

    public void insertDefaultProducts() {
        this.listaProductos.add(new Product("Hollow Knight", 100, "Juego sobre insectos", 20, "Aventura"));
        this.listaProductos.add(new Product("God of War", 200, "Juego sobre guerra", 20, "Accion"));
        this.listaProductos.add(new Product("Lol", 120, "Juego sobre farmear", 20, "MOBA" ));
    }

    public String printLista() {
        StringBuilder lista = new StringBuilder();
        for (Product item : this.listaProductos) {
            lista.append(item.getNombre()).append(",\n");
        }
        return lista.toString();
    }

    // Getter para obtener la lista de productos
    public Vector<Product> getListaProductos() {
        return listaProductos;
    }

     public Product getProductById(String id) {
        for (Product product : listaProductos) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public String getAllProductsAsString() {
        StringBuilder result = new StringBuilder();
    
        for (Product product : listaProductos) {
            result.append("ID: ").append(product.getId()).append("\n");
            result.append("Nombre: ").append(product.getNombre()).append("\n");
            result.append("Precio: ").append(product.getPrecio()).append("\n");
            result.append("Descripción: ").append(product.getDesc()).append("\n");
            result.append("Cantidad: ").append(product.getCantidad()).append("\n");
            result.append("Categoría: ").append(product.getCategoria()).append("\n");
            result.append("------------------------------\n");
        }
    
        return result.toString();
    }

    public void removeProductById(String id) {
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId() == id) {
                listaProductos.remove(i);
                break;
            }
        }
    }

    // Setter para establecer la lista de productos
    public void setListaProductos(Vector<Product> nuevaLista) {
        this.listaProductos = nuevaLista;
    }
}
