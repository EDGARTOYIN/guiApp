package container;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class Sales {
    private JFrame newFrame;
    DefaultTableModel tableSales = new DefaultTableModel();
    ProductList productList;  // Agrega un campo para almacenar la lista de productos
    private JTextField codInput;
    private JTextField preInput;
    private JTextField dniInput;
    private JTextField cantInput;
    String cantidad;
    JButton pay;
    private JLabel paying;  // Agrega un campo para la etiqueta del precio total
    private double precioTotal = 0.0;  // Agrega un campo para almacenar el precio total
    private Vector<String> checkout;


    public Sales(ProductList productList){
        newFrame = new JFrame("Ventas");
        this.productList = productList;  // Guarda la lista de productos proporcionada
        newFrame.setSize(600, 420);
        newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        checkout = new Vector<>();
        createLayout();

        newFrame.setVisible(true);
    }

    public void createLayout() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(createTableSales()), BorderLayout.CENTER);
        mainPanel.add(createFooter(), BorderLayout.SOUTH);
        newFrame.add(mainPanel);
    }

    private JPanel createHeader() {
        JLabel dniLabel = new JLabel("DNI Cliente");
        dniInput = new JTextField("1234567");
        dniInput.setEnabled(false);

        JLabel codLabel = new JLabel("COD.producto");
        codInput = new JTextField();

        JLabel preLabel = new JLabel("PRE.producto");
        preInput = new JTextField();

        JLabel cantLabel = new JLabel("CANT.producto");
        cantInput = new JTextField();

        JPanel gridPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        gridPanel.add(codLabel);
        gridPanel.add(codInput);
        gridPanel.add(preLabel);
        gridPanel.add(preInput);
        gridPanel.add(dniLabel);
        gridPanel.add(dniInput);
        gridPanel.add(cantLabel);
        gridPanel.add(cantInput);

        return gridPanel;
    }

    public Box createFooter() {
        JButton addProduct = new JButton("Buscar Producto para agregar");
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filter(codInput.getText());
            }
        });

        JButton pay = new JButton("Ejecutar Venta");
        addProduct.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerVenta();
            }
        });

        paying = new JLabel("El total a pagar es: " + precioTotal);

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 0));

        gridPanel.add(addProduct);
        gridPanel.add(pay);
        gridPanel.add(paying);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(gridPanel);
        return horizontalBox;
    }

    public String value() {
        if (cantInput.getText().isEmpty()) {
            cantInput.setText("0");
        }
        return cantInput.getText();
    }

    public void hacerVenta() {
        for (String id : checkout){
            Product p = productList.getProductById(id);
            p.setCantidad(p.getCantidad() - Integer.parseInt(cantidad));
        }
    }

    public void filter (String inputText){
        // Limpiar las filas actuales
        precioTotal = 0.0; // Reiniciar el precio total

        for (Product product : productList.getListaProductos()) {
            if (product.getId().toLowerCase().contains(inputText.toLowerCase())) {
                if (!(product.getCantidad() < Integer.parseInt(value()))) {
                    Vector<Object> row = new Vector<>();
                    row.add(product.getId());
                    row.add(product.getNombre());
                    row.add(product.getPrecio());
                    row.add(product.getDesc());
                    cantidad = value();
                    row.add(cantidad);
                    row.add(product.getCategoria());
                    tableSales.addRow(row);

                    // Calcular el precio parcial y sumarlo al precio total
                    double precioParcial = (product.getPrecio()) * Double.parseDouble(cantidad);
                    precioTotal += precioParcial;
                    checkout.add(product.getId());
                }else
                {
                    JOptionPane.showMessageDialog(null, "No tenemos ese stock");
                }
            }
        }

        updatePayingLabel(); // Actualizar la etiqueta del precio total
    }

    private void updatePayingLabel() {
        paying.setText("El total a pagar es: " + precioTotal);
    }

    public JTable createTableSales() {
        // Establecer las columnas del modelo
        tableSales.addColumn("ID");
        tableSales.addColumn("Producto");
        tableSales.addColumn("Precio");
        tableSales.addColumn("Descripcion");
        tableSales.addColumn("Cantidad");
        tableSales.addColumn("Categoria");

        return new JTable(tableSales);
    }
}
