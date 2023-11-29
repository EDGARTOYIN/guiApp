package container;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductForm extends JFrame {

    private JTextField nombreField;
    private JTextField precioField;
    private JTextField descField;
    private JTextField cantidadField;
    private JTextField categoriaField;
    private Runnable loadTableOperation;

    private ProductList productList;

    public ProductForm(ProductList productList, Runnable loadTable) {
        this.productList = productList;
        this.loadTableOperation = loadTable;

        setTitle("Agregar Nuevo Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        crearLayout();
        add(createMainPanel());
        setVisible(true);

    }

    private void crearLayout() {
        nombreField = new JTextField(10);
        precioField = new JTextField(10);
        descField = new JTextField(10);
        cantidadField = new JTextField(10);
        categoriaField = new JTextField(10);

        JButton addButton = new JButton("Agregar Producto");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        setLayout(new FlowLayout());
        add(new JLabel("Nombre: "));
        add(nombreField);
        add(new JLabel("Precio: "));
        add(precioField);
        add(new JLabel("Descripción: "));
        add(descField);
        add(new JLabel("Cantidad: "));
        add(cantidadField);
        add(new JLabel("Categoría: "));
        add(categoriaField);
        add(addButton);
    }

    private JPanel createMainPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(createFooter(), BorderLayout.SOUTH);
        return mainPanel;
    }

    private Box createHeader() {
        Box verticalBox = Box.createVerticalBox();
        return verticalBox;
    }

    private Box createFooter() {
        Box horizontalBox = Box.createHorizontalBox();

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });

        horizontalBox.add(closeButton);
        return horizontalBox;
    }

    private void agregarProducto() {
        String nombre = nombreField.getText();
        double precio = Double.parseDouble(precioField.getText());
        String desc = descField.getText();
        int cantidad = Integer.parseInt(cantidadField.getText());
        String categoria = categoriaField.getText();

        // Crea un nuevo producto y agrégalo a la lista y la tabla
        Product nuevoProducto = new Product(nombre, precio, desc, cantidad, categoria);
        productList.getListaProductos().add(nuevoProducto);
        loadTableOperation.run();
        // Cierra la ventana después de agregar el producto
        dispose();
    }
}