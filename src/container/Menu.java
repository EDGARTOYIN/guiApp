package container;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import java.awt.*;
import javax.swing.table.DefaultTableModel;

public class Menu {
    
    private JFrame f;
    ProductList lista = new ProductList();
    DefaultTableModel table = new DefaultTableModel();

    public Menu() {
        this.f = new JFrame("Listar Productos");
        this.f.setSize(800, 500);
        this.f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.createLayout(f);
        this.f.setVisible(true);
    }

    public void createLayout(JFrame panel) {
        JPanel mainPanel = new JPanel(new BorderLayout());
        
        JTable tabla = createTable(lista);
        mainPanel.add(createHeader(), BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(tabla), BorderLayout.CENTER);

        // Agregar un TableModelListener para detectar cambios en la tabla
        table.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE && e.getFirstRow() >= 0) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();
                    Object idColumn = table.getValueAt(row, 0);
                    Object newValue = table.getValueAt(row, column);
                    UpdateProducts(row, column, newValue, idColumn);
                }
            }
        });

        mainPanel.add(createFooter(tabla), BorderLayout.SOUTH);
        panel.add(mainPanel);


    }

    public Box createFooter(JTable t) {
           JButton deleteButton = new JButton("Borrar Producto");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedProduct(t);
            }
        });

        JButton b1 = new JButton("Print Products");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateProductsFile(lista);
            }
        });

        JButton sales = new JButton("Vender");
        sales.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewWindow();
            }
        });

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 0));
        gridPanel.add(b1);
        gridPanel.add(deleteButton);
        gridPanel.add(sales);

        Box horizontalBox = Box.createHorizontalBox();
        horizontalBox.add(gridPanel);
        return horizontalBox;
    }

    public Box createHeader(){
        JLabel titleLabel = new JLabel("Pancho Games", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel search = new JLabel("Buscar Productos", JLabel.LEFT);
        search.setFont(new Font("Arial", Font.BOLD, 18));
        
        JTextField input = new JTextField();
        input.setMaximumSize(new Dimension(100, 30));

        JButton findButton = new JButton("Buscar");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filterProducts(input.getText());
            }
        });
        // findButton.setMaximumSize(new Dimension(100, 30));

        // Crear un JPanel con GridLayout
        JPanel gridPanel = new JPanel(new GridLayout(2, 0, 0, 24));
        JPanel gridSearch = new JPanel(new GridLayout(0, 3, 0, 0));
        gridSearch.add(search);
        gridSearch.add(input);
        gridSearch.add(findButton);
        // Agregar elementos al panel de la cuadrícula
        gridPanel.add(titleLabel);
        gridPanel.add(gridSearch);


        Box verticalBox = Box.createVerticalBox();
        verticalBox.add(gridPanel);
        return verticalBox;
    }

    public void filterProducts (String inputText){

        // Limpiar las filas actuales
        table.setRowCount(0);
        for (Product product : lista.getListaProductos()) {
            if (product.getId().toLowerCase().contains(inputText.toLowerCase())) {
                Vector<Object> row = new Vector<>();
                row.add(product.getId());
                row.add(product.getNombre());
                row.add(product.getPrecio());
                row.add(product.getDesc());
                row.add(product.getCantidad());
                row.add(product.getCategoria());
                table.addRow(row);
            }
        }
        
    }

    public JTable createTable(ProductList lista){
          // Crear el modelo de la tabla

        // Establecer las columnas del modelo
        table.addColumn("ID");
        table.addColumn("Producto");
        table.addColumn("Precio");
        table.addColumn("Descripcion");
        table.addColumn("Cantidad");
        table.addColumn("Categoria");
        

       loadTable();

        JTable t = new JTable(table);
      

        return t;
    }

    public void loadTable() {
        table.setRowCount(0);
         for (Product product : lista.getListaProductos()) {
            Vector<Object> row = new Vector<>();
            row.add(product.getId());
            row.add(product.getNombre());
            row.add(product.getPrecio());
            row.add(product.getDesc());
            row.add(product.getCantidad());
            row.add(product.getCategoria());
            table.addRow(row);
        }
    }

    public void deleteSelectedProduct(JTable t) {
        int selectedRow = t.getSelectedRow();
        if (selectedRow >= 0) {
            // Obtener el ID de la fila seleccionada
            String idColumn = (String) table.getValueAt(selectedRow, 0);
            // Eliminar el producto de la lista y de la tabla
            lista.removeProductById(idColumn);
            table.removeRow(selectedRow);
        }
    }

    public void UpdateProducts (int row, int colum, Object newValue, Object idColumn) {
        // Convertir el ID de Object a int
        String id = idColumn.toString();
        // Obtener el producto correspondiente a la fila editada en la lista de productos
        Product editedProduct = lista.getProductById(id);

         // Actualizar la información del producto según la celda editada
         switch (colum) {
            case 1:
                editedProduct.setNombre((String) newValue);
                break;
            case 2:
                // Convertir el valor de la celda (que podría ser un String) a Double
                editedProduct.setPrecio(Double.parseDouble(newValue.toString()));
                break;
            case 3:
                editedProduct.setDesc((String) newValue);
                break;
            case 4:
                // Convertir el valor de la celda (que podría ser un String) a Integer
                editedProduct.setCantidad(Integer.parseInt(newValue.toString()));
                break;
            case 5:
                editedProduct.setCategoria((String) newValue);
                break;
            default:
                break;
        }

        loadTable();

    }


    private void openNewWindow() {
        new Sales(lista);
    }

    public void CreateProductsFile(ProductList p){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Products.txt"));
            writer.write(p.printLista());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
