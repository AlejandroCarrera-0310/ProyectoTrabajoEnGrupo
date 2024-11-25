package Hotel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class InterfazHotel {
    private Hotel hotel;

    public InterfazHotel() {
        hotel = new Hotel();
    }

    public void mostrarInterfaz() {
        JFrame frame = new JFrame("Gestión de Hotel");
        frame.setSize(600, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        JPanel panel = new JPanel();
        panel.setBackground(Color.DARK_GRAY);
        panel.setLayout(null);
        frame.add(panel);

        colocarComponentes(panel);

        frame.setVisible(true);
    }

    private void colocarComponentes(JPanel panel) {
        // Fuente y color para texto
        Font fuente = new Font("Arial", Font.BOLD, 14);
        Color colorTexto = Color.WHITE;

        JLabel labelNumero = new JLabel("Número de habitación:");
        labelNumero.setBounds(10, 20, 200, 25);
        labelNumero.setForeground(colorTexto);
        labelNumero.setFont(fuente);
        panel.add(labelNumero);

        JTextField numeroHabitacion = new JTextField();
        numeroHabitacion.setBounds(200, 20, 150, 25);
        panel.add(numeroHabitacion);

        JButton btnDisponibilidad = new JButton("Consultar Disponibilidad");
        btnDisponibilidad.setBounds(10, 60, 220, 30);
        btnDisponibilidad.setBackground(Color.LIGHT_GRAY);
        panel.add(btnDisponibilidad);

        JButton btnReserva = new JButton("Crear Reserva");
        btnReserva.setBounds(10, 100, 220, 30);
        btnReserva.setBackground(Color.LIGHT_GRAY);
        panel.add(btnReserva);

        JLabel labelTipo = new JLabel("Tipo de habitación:");
        labelTipo.setBounds(10, 140, 200, 25);
        labelTipo.setForeground(colorTexto);
        labelTipo.setFont(fuente);
        panel.add(labelTipo);

        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Individual", "Doble", "Suite"});
        comboTipo.setBounds(200, 140, 150, 25);
        panel.add(comboTipo);

        JButton btnPorTipo = new JButton("Ver Disponibilidad por Tipo");
        btnPorTipo.setBounds(10, 180, 250, 30);
        btnPorTipo.setBackground(Color.LIGHT_GRAY);
        panel.add(btnPorTipo);

        JTextArea areaResultado = new JTextArea();
        areaResultado.setFont(new Font("Monospaced", Font.PLAIN, 12));
        areaResultado.setEditable(false);
        areaResultado.setBackground(Color.BLACK);
        areaResultado.setForeground(Color.GREEN);

        JScrollPane scroll = new JScrollPane(areaResultado);
        scroll.setBounds(10, 230, 550, 200);
        panel.add(scroll);

        // Acciones de los botones
        btnDisponibilidad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(numeroHabitacion.getText());
                    boolean disponible = hotel.verificarDisponibilidad(numero);
                    areaResultado.setText(disponible ? "La habitación " + numero + " está disponible." : "La habitación " + numero + " no está disponible.");
                } catch (NumberFormatException ex) {
                    areaResultado.setText("Por favor, ingrese un número válido.");
                }
            }
        });

        btnReserva.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numero = Integer.parseInt(numeroHabitacion.getText());
                    boolean creado = hotel.crearReserva("Cliente", numero, new java.util.Date(), new java.util.Date());
                    areaResultado.setText(creado ? "Reserva creada para la habitación " + numero + "." : "No se pudo crear la reserva. Verifique la disponibilidad.");
                } catch (NumberFormatException ex) {
                    areaResultado.setText("Por favor, ingrese un número válido.");
                }
            }
        });

        btnPorTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipo = (String) comboTipo.getSelectedItem();
                List<Habitacion> disponibles = hotel.getHabitacionesPorTipo(tipo);
                StringBuilder lista = new StringBuilder("Habitaciones disponibles (" + tipo + "):\n");
                for (Habitacion habitacion : disponibles) {
                    if (habitacion.isDisponible()) {
                        lista.append("Habitación ").append(habitacion.getNumero()).append("\n");
                    }
                }
                if (lista.length() <= 35) {
                    lista.append("No hay habitaciones disponibles.");
                }
                areaResultado.setText(lista.toString());
            }
        });
    }

    public static void main(String[] args) {
        InterfazHotel interfaz = new InterfazHotel();
        interfaz.mostrarInterfaz();
    }
}