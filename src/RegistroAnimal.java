import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroAnimal extends JFrame {
    public RegistroAnimal() {
        setTitle("Registro de Animales");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel panel = new JPanel();
        add(panel);
        placeComponents(panel);
        setVisible(true);
    }

    private void placeComponents(JPanel panel) {
        panel.setLayout(null);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreLabel.setBounds(10, 20, 80, 25);
        panel.add(nombreLabel);

        JTextField nombreText = new JTextField(20);
        nombreText.setBounds(150, 20, 165, 25);
        panel.add(nombreText);

        JLabel idLabel = new JLabel("ID:");
        idLabel.setBounds(10, 50, 80, 25);
        panel.add(idLabel);

        JTextField idText = new JTextField(20);
        idText.setBounds(150, 50, 165, 25);
        panel.add(idText);

        JLabel dietaLabel = new JLabel("Dieta:");
        dietaLabel.setBounds(10, 80, 80, 25);
        panel.add(dietaLabel);

        JRadioButton carnivoroButton = new JRadioButton("Carnívoro");
        carnivoroButton.setBounds(150, 80, 100, 25);
        panel.add(carnivoroButton);

        JRadioButton herbivoroButton = new JRadioButton("Herbívoro");
        herbivoroButton.setBounds(250, 80, 100, 25);
        panel.add(herbivoroButton);

        JRadioButton omnivoroButton = new JRadioButton("Omnívoro");
        omnivoroButton.setBounds(350, 80, 100, 25);
        panel.add(omnivoroButton);

        ButtonGroup dietaGroup = new ButtonGroup();
        dietaGroup.add(carnivoroButton);
        dietaGroup.add(herbivoroButton);
        dietaGroup.add(omnivoroButton);

        JLabel edadLabel = new JLabel("Edad:");
        edadLabel.setBounds(10, 110, 80, 25);
        panel.add(edadLabel);

        JTextField edadText = new JTextField(20);
        edadText.setBounds(150, 110, 165, 25);
        panel.add(edadText);

        JLabel sexoLabel = new JLabel("Sexo:");
        sexoLabel.setBounds(10, 140, 80, 25);
        panel.add(sexoLabel);

        JRadioButton machoButton = new JRadioButton("Macho");
        machoButton.setBounds(150, 140, 100, 25);
        panel.add(machoButton);

        JRadioButton hembraButton = new JRadioButton("Hembra");
        hembraButton.setBounds(250, 140, 100, 25);
        panel.add(hembraButton);

        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(machoButton);
        sexoGroup.add(hembraButton);

        JCheckBox vacunadoCheckBox = new JCheckBox("Vacunado");
        vacunadoCheckBox.setBounds(150, 170, 100, 25);
        panel.add(vacunadoCheckBox);

        JCheckBox esterilizadoCheckBox = new JCheckBox("Esterilizado");
        esterilizadoCheckBox.setBounds(250, 170, 100, 25);
        panel.add(esterilizadoCheckBox);

        JButton registerButton = new JButton("Registrar");
        registerButton.setBounds(10, 210, 150, 25);
        panel.add(registerButton);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreText.getText();
                String id = idText.getText();
                String dieta = carnivoroButton.isSelected() ? "Carnívoro" : herbivoroButton.isSelected() ? "Herbívoro" : "Omnívoro";
                int edad = Integer.parseInt(edadText.getText());
                String sexo = machoButton.isSelected() ? "Macho" : "Hembra";
                boolean vacunado = vacunadoCheckBox.isSelected();
                boolean esterilizado = esterilizadoCheckBox.isSelected();

                Animal animal = new Animal(nombre, id, dieta, edad, sexo, vacunado, esterilizado);
                try {
                    Connection connection = DatabaseUtil.getConnection();
                    String query = "INSERT INTO animales (nombre, id, dieta, edad, sexo, vacunado, esterilizado) VALUES (?, ?, ?, ?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, animal.getNombre());
                    preparedStatement.setString(2, animal.getId());
                    preparedStatement.setString(3, animal.getDieta());
                    preparedStatement.setInt(4, animal.getEdad());
                    preparedStatement.setString(5, animal.getSexo());
                    preparedStatement.setBoolean(6, animal.isVacunado());
                    preparedStatement.setBoolean(7, animal.isEsterilizado());
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(panel, "Animal registrado: " + nombre + "\nID: " + id + "\nDieta: " + dieta + "\nEdad: " + edad + "\nSexo: " + sexo + "\nVacunado: " + vacunado + "\nEsterilizado: " + esterilizado);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Error al registrar el animal en la base de datos.");
                }
            }
        });
    }
}
