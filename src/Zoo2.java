import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Zoo2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Zoo Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        frame.setVisible(true);
    }

    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 80, 25);
        panel.add(nombreLabel);

        JTextField nombreText = new JTextField(20);
        nombreText.setBounds(100, 20, 165, 25);
        panel.add(nombreText);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoLabel.setBounds(10, 50, 80, 25);
        panel.add(apellidoLabel);

        JTextField apellidoText = new JTextField(20);
        apellidoText.setBounds(100, 50, 165, 25);
        panel.add(apellidoText);

        JLabel cedulaLabel = new JLabel("Cédula:");
        cedulaLabel.setBounds(10, 80, 80, 25);
        panel.add(cedulaLabel);

        JTextField cedulaText = new JTextField(20);
        cedulaText.setBounds(100, 80, 165, 25);
        panel.add(cedulaText);

        JLabel celularLabel = new JLabel("Celular:");
        celularLabel.setBounds(10, 110, 80, 25);
        panel.add(celularLabel);

        JTextField celularText = new JTextField(20);
        celularText.setBounds(100, 110, 165, 25);
        panel.add(celularText);

        JLabel tipoPersonaLabel = new JLabel("Tipo de Persona:");
        tipoPersonaLabel.setBounds(10, 140, 150, 25);
        panel.add(tipoPersonaLabel);

        JRadioButton empleadoButton = new JRadioButton("Empleado");
        empleadoButton.setBounds(150, 140, 100, 25);
        panel.add(empleadoButton);

        JRadioButton visitanteButton = new JRadioButton("Visitante");
        visitanteButton.setBounds(250, 140, 100, 25);
        panel.add(visitanteButton);

        ButtonGroup tipoPersonaGroup = new ButtonGroup();
        tipoPersonaGroup.add(empleadoButton);
        tipoPersonaGroup.add(visitanteButton);

        JButton registerPersonButton = new JButton("Registrar Persona");
        registerPersonButton.setBounds(10, 180, 150, 25);
        panel.add(registerPersonButton);

        registerPersonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String apellido = apellidoText.getText();
                String cedula = cedulaText.getText();
                String celular = celularText.getText();
                String tipoPersona = empleadoButton.isSelected() ? "Empleado" : "Visitante";

                Persona persona = new Persona(nombre, apellido, cedula, celular, tipoPersona);

                try {
                    Connection connection = DatabaseUtil.getConnection();
                    String query = "INSERT INTO personas (cedula, nombre, apellido, celular, tipo_persona) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, persona.getCedula());
                    preparedStatement.setString(2, persona.getNombre());
                    preparedStatement.setString(3, persona.getApellido());
                    preparedStatement.setString(4, persona.getCelular());
                    preparedStatement.setString(5, persona.getTipoPersona());
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Persona registrada: " + nombre + " " + apellido + "\nCédula: " + cedula + "\nCelular: " + celular + "\nTipo: " + tipoPersona);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error al registrar la persona en la base de datos.");
                }
            }
        });

        JButton registerAnimalButton = new JButton("Registrar Animal");
        registerAnimalButton.setBounds(10, 210, 150, 25);
        panel.add(registerAnimalButton);

        registerAnimalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegistroAnimal();
            }
        });
    }
}
