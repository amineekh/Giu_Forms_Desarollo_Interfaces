import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;

public class Helloword extends JFrame {
    private JPanel mainpanel;
    private JTextField idField;
    private JTextField nombre_Field;
    private JTextField apellido_Field;
    private JTextField DNI_Field;

    private JTextField email_Field;

    private JPasswordField pass_Field;


    private JButton añadir_Button;
    private JButton nuevo_Button;
    private JButton borrar_Button;
    private JButton modificar_Button;
    private JTable table;
    v
    private DefaultTableModel model;

    private int idCounter = 1; // Establecer el valor inicial del contador de ID a 1
    private JPanel Panel2;


    public Helloword() {
        // Inicializar el modelo de la tabla
        model = new DefaultTableModel();
        //metodo para la creacion de la tabla
        createTable();

        // Configurar la interfaz de usuario
        configuracion_UI();
    }

    private void createTable() {

        idField.setEditable(false); // Hacemos el campo de ID no editable al inicio
        idField.setText(String.valueOf(idCounter)); // Mostramos el ID actual

        // Creación del modelo de tabla y definición de la no edición de la columna ID
        model = new DefaultTableModel() {
            // Hacemos que la columna ID sea no editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Hacemos que la columna ID sea no editable
            }
        };
        // Agregar columnas al modelo de la tabla si es necesario
        model.addColumn("ID");
        model.addColumn("Nombre");
        model.addColumn("Apellidos");
        model.addColumn("DNI");
        model.addColumn("Email");
        model.addColumn("Contraseña");

        // Asignar el modelo a la tabla
        table.setModel(model);

        // Definir un DefaultTableCellRenderer para ocultar la contraseña en la tabla
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer() {
            @Override
            protected void setValue(Object value) {
                if (value instanceof String) {
                    setText("\u2022\u2022\u2022\u2022\u2022\u2022"); // Mostrar "••••••" en lugar del texto real
                } else {
                    super.setValue(value);
                }
            }
        };
        // Aplicar el renderer a la columna de contraseñas (índice 5)
        table.getColumnModel().getColumn(5).setCellRenderer(renderer);
    }

    private void configuracion_UI() {
        // Configurar la ventana
        setTitle("Hello World");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainpanel); // Establecer el panel principal

        //CONFIGURACION DE LOS BOTONES
        // Acción para el botón Nuevo
        nuevo_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                clearFields(); // Limpia los campos de texto
            }
        });

        // Acción para el botón Añadir
        añadir_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Verifica si los campos obligatorios están rellenos antes de agregar una nueva fila a la tabla
                if (!nombre_Field.getText().isEmpty() && !apellido_Field.getText().isEmpty() && !email_Field.getText().isEmpty() && !pass_Field.getText().isEmpty()) {
                    String[] rowData = {
                            idField.getText(),
                            nombre_Field.getText(),
                            apellido_Field.getText(),
                            DNI_Field.getText(),
                            email_Field.getText(),
                            pass_Field.getText()
                    };
                    model.addRow(rowData); // Añade una nueva fila con los datos introducidos en los campos
                    idCounter++; // Incrementa el contador de ID
                    idField.setText(String.valueOf(idCounter)); // Actualiza el campo de ID
                } else {
                    // Muestra un mensaje de advertencia si no se han rellenado los campos obligatorios
                    JOptionPane.showMessageDialog(Helloword.this, "Por favor, rellene los campos obligatorios.");
                }
            }
        });

        // Acción para el botón Modificar
        modificar_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                // Verifica si se ha seleccionado una fila y si los campos obligatorios están rellenos antes de realizar la modificación
                if (selectedRow != -1 && !nombre_Field.getText().isEmpty() && !apellido_Field.getText().isEmpty() && !email_Field.getText().isEmpty() && !pass_Field.getText().isEmpty()) {
                    // Actualiza los valores de la fila seleccionada con los valores de los campos de texto
                    table.setValueAt(nombre_Field.getText(), selectedRow, 1);
                    table.setValueAt(apellido_Field.getText(), selectedRow, 2);
                    table.setValueAt(DNI_Field.getText(), selectedRow, 3);
                    table.setValueAt(email_Field.getText(), selectedRow, 4);
                    table.setValueAt(pass_Field.getText(), selectedRow, 5);

                } else {
                    // Muestra un mensaje de advertencia si no se ha seleccionado una fila o si los campos obligatorios no están rellenos
                    JOptionPane.showMessageDialog(Helloword.this, "Por favor, seleccione una fila y rellene los campos obligatorios.");
                }
            }
        });

        // Acción para el botón Eliminar
        borrar_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                // Verifica si se ha seleccionado una fila antes de eliminarla
                if (selectedRow != -1) {
                    // Muestra una confirmación antes de eliminar la fila seleccionada
                    int confirm = JOptionPane.showConfirmDialog(Helloword.this, "¿Está seguro de que desea eliminar este usuario?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        model.removeRow(selectedRow); // Elimina la fila seleccionada del modelo de la tabla
                    }
                } else {
                    // Muestra un mensaje de advertencia si no se ha seleccionado ninguna fila para eliminar
                    JOptionPane.showMessageDialog(Helloword.this, "Por favor, seleccione una fila para eliminar.");
                }
            }
        });




        pack(); // Ajusta el tamaño de la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Método para limpiar los campos de texto del formulario
    private void clearFields() {
        nombre_Field.setText("");
        apellido_Field.setText("");
        DNI_Field.setText("");
        email_Field.setText("");
        pass_Field.setText("");
    }


    public static void main(String[] args) {
        Helloword Helloword= new Helloword();
        // Hacer visible la ventana
        Helloword.setVisible(true);
    }
}
