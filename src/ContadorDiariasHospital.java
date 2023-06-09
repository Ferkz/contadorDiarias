import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ContadorDiariasHospital extends JFrame {
    private JTextField campoDataEntrada;
    private JTextField campoDataSaida;
    private JTextField campoNomePaciente;
    private JTextField campoIdPaciente;
    private JTextField campoValorDiaria;
    private JComboBox<String> comboBoxTipoAlta;
    private JLabel labelNumDiarias;
    private JLabel labelValorTotal;
    private double valorDiaria;
    private String nomeCidade;

        public ContadorDiariasHospital() {
        setTitle("Contador de Diárias - Hospital");
        setSize(800, 640);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);

        JLabel labelTitulo = new JLabel("Contador de Diárias - Hospital Samar");
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        labelTitulo.setBounds(200, 20, 450, 30);
        painelPrincipal.add(labelTitulo);

        JLabel labelDataEntrada = new JLabel("Data de Entrada:");
        labelDataEntrada.setBounds(50, 80, 150, 20);
        painelPrincipal.add(labelDataEntrada);

        campoDataEntrada = new JTextField();
        campoDataEntrada.setBounds(200, 80, 150, 20);
        painelPrincipal.add(campoDataEntrada);

        JLabel labelDataSaida = new JLabel("Data de Saída:");
        labelDataSaida.setBounds(400, 80, 150, 20);
        painelPrincipal.add(labelDataSaida);

        campoDataSaida = new JTextField();
        campoDataSaida.setBounds(550, 80, 150, 20);
        painelPrincipal.add(campoDataSaida);

        JLabel labelNomePaciente = new JLabel("Nome do Paciente:");
        labelNomePaciente.setBounds(50, 120, 150, 20);
        painelPrincipal.add(labelNomePaciente);

        campoNomePaciente = new JTextField();
        campoNomePaciente.setBounds(200, 120, 500, 20);
        painelPrincipal.add(campoNomePaciente);

        JLabel labelIdPaciente = new JLabel("Número Prontuário:");
        labelIdPaciente.setBounds(50, 160, 150, 20);
        painelPrincipal.add(labelIdPaciente);

        campoIdPaciente = new JTextField();
        campoIdPaciente.setBounds(200, 160, 150, 20);
        painelPrincipal.add(campoIdPaciente);

        JLabel labelTipoAlta = new JLabel("Tipo de Alta:");
        labelTipoAlta.setBounds(50, 200, 150, 20);
        painelPrincipal.add(labelTipoAlta);

        comboBoxTipoAlta = new JComboBox<>();
        comboBoxTipoAlta.addItem("Alta Hospitalar");
        comboBoxTipoAlta.addItem("Óbito");
        comboBoxTipoAlta.addItem("Transferência");
        comboBoxTipoAlta.addItem("A pedido");
        comboBoxTipoAlta.addItem("Administrativa");
        comboBoxTipoAlta.addItem("Evasão");
        comboBoxTipoAlta.addItem("Doação de Órgão");
        comboBoxTipoAlta.setBounds(200, 200, 150, 20);
        painelPrincipal.add(comboBoxTipoAlta);

        JButton botaoCalcular = new JButton("Calcular");
        botaoCalcular.setBounds(50, 240, 100, 30);
        painelPrincipal.add(botaoCalcular);

        JLabel labelResultado = new JLabel("Resultado:");
        labelResultado.setBounds(50, 300, 100, 20);
        painelPrincipal.add(labelResultado);

        labelNumDiarias = new JLabel("");
        labelNumDiarias.setBounds(50, 340, 200, 20);
        painelPrincipal.add(labelNumDiarias);

        labelValorTotal = new JLabel("");
        labelValorTotal.setBounds(50, 380, 200, 20);
        painelPrincipal.add(labelValorTotal);

        JButton botaoImprimir = new JButton("Imprimir");
        botaoImprimir.setBounds(50, 420, 100, 30);
        painelPrincipal.add(botaoImprimir);

        JButton botaoParametros = new JButton("Parâmetros");
        botaoParametros.setBounds(50, 460, 100, 30);
        painelPrincipal.add(botaoParametros);

        botaoCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calcularDiarias();
            }
        });

        botaoImprimir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                imprimir();
            }
        });

        botaoParametros.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirParametros();
            }
        });

        setContentPane(painelPrincipal);
    }

    private void calcularDiarias() {
        String dataEntrada = campoDataEntrada.getText();
        String dataSaida = campoDataSaida.getText();

        if (dataEntrada.isEmpty() || dataSaida.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, informe a data de entrada e saída.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int numDiarias = calcularNumeroDiarias(dataEntrada, dataSaida);

        double valorTotal = numDiarias * valorDiaria;

        labelNumDiarias.setText("Número de Diárias: " + numDiarias);
        labelValorTotal.setText("Valor Total: " + valorTotal);
    }
    private int calcularNumeroDiarias(String dataEntrada, String dataSaida) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate dateEntrada = LocalDate.parse(dataEntrada, formatter);
        LocalDate dateSaida = LocalDate.parse(dataSaida, formatter);

        long numDias = ChronoUnit.DAYS.between(dateEntrada, dateSaida);

        // Se quiser contar o dia da saída como uma diária completa,
        // numDias++;
        return (int) numDias;
    }
    private void exibirParametros() {
        JFrame frameParametros = new JFrame("Parâmetros");
        frameParametros.setSize(300, 200);
        frameParametros.setLocationRelativeTo(null);
        frameParametros.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel painelParametros = new JPanel();
        painelParametros.setLayout(null);

        JLabel labelValorDiaria = new JLabel("Valor da Diária:");
        labelValorDiaria.setBounds(50, 50, 100, 20);
        painelParametros.add(labelValorDiaria);

        JTextField campoValorDiaria = new JTextField();
        campoValorDiaria.setBounds(160, 50, 100, 20);
        painelParametros.add(campoValorDiaria);

        JLabel labelNomeCidade = new JLabel("Nome da Cidade:");
        labelNomeCidade.setBounds(50, 90, 100, 20);
        painelParametros.add(labelNomeCidade);

        JTextField campoNomeCidade = new JTextField();
        campoNomeCidade.setBounds(160, 90, 100, 20);
        painelParametros.add(campoNomeCidade);

        JButton botaoSalvar = new JButton("Salvar");
        botaoSalvar.setBounds(50, 130, 80, 30);
        painelParametros.add(botaoSalvar);

        JButton botaoCancelar = new JButton("Cancelar");
        botaoCancelar.setBounds(150, 130, 100, 30);
        painelParametros.add(botaoCancelar);
        botaoSalvar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String valorDiariaStr = campoValorDiaria.getText();
                String nomeCidade = campoNomeCidade.getText();

                if (valorDiariaStr.isEmpty() || nomeCidade.isEmpty()) {
                    JOptionPane.showMessageDialog(frameParametros, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    valorDiaria = Double.parseDouble(valorDiariaStr);
                    ContadorDiariasHospital.this.nomeCidade = nomeCidade;
                    JOptionPane.showMessageDialog(frameParametros, "Parâmetros salvos com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frameParametros.dispose(); // Fechar a janela após salvar os parâmetros
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frameParametros, "Valor da diária inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frameParametros.setContentPane(painelParametros);
        frameParametros.setVisible(true);
    }
    private void imprimir() {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                Graphics2D g2d = (Graphics2D) graphics;
                g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
                // Desenhar o conteúdo da página de impressão aqui
                return PAGE_EXISTS;
            }
        });
        boolean imprimir = job.printDialog();
        if (imprimir) {
            try {
                job.print();
            } catch (PrinterException e) {
                JOptionPane.showMessageDialog(this, "Erro ao imprimir: " + e.getMessage(), "Erro de Impressão", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ContadorDiariasHospital().setVisible(true);
            }
        });
    }
}
