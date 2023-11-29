// package container;

// import java.awt.BorderLayout;
// import java.awt.GridLayout;
// import java.util.Vector;
// import javax.swing.*;
// import java.awt.*;
// import javax.swing.Box;
// import javax.swing.JButton;
// import javax.swing.JFrame;
// import javax.swing.JLabel;
// import javax.swing.JPanel;
// import javax.swing.JScrollPane;
// import javax.swing.JTable;
// import javax.swing.JTextField;
// import javax.swing.table.DefaultTableModel;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class Sales {
//     private JFrame newFrame;
//     DefaultTableModel tableSales = new DefaultTableModel();
//     ProductList productList;  // Agrega un campo para almacenar la lista de productos

//     public Sales(ProductList productList){
//         newFrame = new JFrame("Ventas");
//         this.productList = productList;  // Guarda la lista de productos proporcionada
//         newFrame.setSize(600, 420);
//         newFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//         createLayout();

//         newFrame.setVisible(true);
//     }

//     public void createLayout() {
//         JPanel mainPanel = new JPanel(new BorderLayout());
//         mainPanel.add(createHeader(), BorderLayout.NORTH);
//         mainPanel.add(new JScrollPane(createTableSales()), BorderLayout.CENTER);
//         mainPanel.add(createFooter(), BorderLayout.SOUTH);
//         newFrame.add(mainPanel);
//     }

//     private JPanel createHeader() {
//         JLabel dniLabel = new JLabel("DNI Cliente");
//         JTextField dniInput = new JTextField("1234567");
//         dniInput.setEnabled(false);

//         JLabel codLabel = new JLabel("COD.producto");
//         JTextField codInput = new JTextField();

//         JLabel preLabel = new JLabel("PRE.producto");
//         JTextField preInput = new JTextField();

//         JLabel cantLabel = new JLabel("CANT.producto");
//         JTextField cantInput = new JTextField();

//         JPanel gridPanel = new JPanel(new GridLayout(4, 2, 10, 10));
//         gridPanel.add(codLabel);
//         gridPanel.add(codInput);
//         gridPanel.add(preLabel);
//         gridPanel.add(preInput);
//         gridPanel.add(dniLabel);
//         gridPanel.add(dniInput);
//         gridPanel.add(cantLabel);
//         gridPanel.add(cantInput);

//         return gridPanel;
//     }

//     public Box createFooter() {
//         JButton addProduct = new JButton("Buscar Producto para agregar");
//         addProduct.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 filter(addProduct.getText());
//             }
//         });

//         JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 0));
        
//         gridPanel.add(addProduct);

//         Box horizontalBox = Box.createHorizontalBox();
//         horizontalBox.add(gridPanel);
//         return horizontalBox;
//     }

//     public void filter (String inputText){

//         // Limpiar las filas actuales
//         tableSales.setRowCount(0);
//         for (Product product : productList.getListaProductos()) {
//             if (product.getId().toLowerCase().contains(inputText.toLowerCase())) {
//                 Vector<Object> row = new Vector<>();
//                 row.add(product.getId());
//                 row.add(product.getNombre());
//                 row.add(product.getPrecio());
//                 row.add(product.getDesc());
//                 row.add(product.getCantidad());
//                 row.add(product.getCategoria());
//                 tableSales.addRow(row);
//             }
//         }
        
//     }

    

//     public JTable createTableSales() {
//         // Establecer las columnas del modelo
//         tableSales.addColumn("ID");
//         tableSales.addColumn("Producto");
//         tableSales.addColumn("Precio");
//         tableSales.addColumn("Descripcion");
//         tableSales.addColumn("Cantidad");
//         tableSales.addColumn("Categoria");

//         return new JTable(tableSales);
//     }

    
// }
