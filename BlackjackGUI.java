import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BlackjackGUI extends JFrame {

    private JTextArea areaJogador;
    private JTextArea areaCrupie;
    private JButton btnHit, btnStand, btnNovo;

    private Baralho baralho;
    private ArrayList<Carta> mao;
    private ArrayList<Carta> maoCrupie;

    private boolean mostrarCrupieCompleto = false;

    public BlackjackGUI() {
        setTitle("Blackjack");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Áreas
        areaJogador = new JTextArea();
        areaCrupie = new JTextArea();

        areaJogador.setBorder(BorderFactory.createTitledBorder("Sua mão"));
        areaCrupie.setBorder(BorderFactory.createTitledBorder("Mão do Crupiê"));

        add(areaCrupie, BorderLayout.NORTH);
        add(areaJogador, BorderLayout.SOUTH);

        // Botões
        JPanel painel = new JPanel();

        btnHit = new JButton("Hit");
        btnStand = new JButton("Stand");
        btnNovo = new JButton("Novo Jogo");

        painel.add(btnHit);
        painel.add(btnStand);
        painel.add(btnNovo);

        add(painel, BorderLayout.CENTER);

        // Eventos
        btnNovo.addActionListener(e -> iniciarJogo());

        btnHit.addActionListener(e -> {
            mao.add(baralho.comprarCarta());
            atualizarTela();

            if (Jogadores.calcularValorMao(mao) > 21) {
                mostrarCrupieCompleto = true;
                atualizarTela();
                JOptionPane.showMessageDialog(this, "Você estourou! Perdeu!");
            }
        });

        btnStand.addActionListener(e -> turnoCrupie());

        iniciarJogo();
    }

    private void iniciarJogo() {
        baralho = new Baralho();
        baralho.embaralhar();

        mao = new ArrayList<>();
        maoCrupie = new ArrayList<>();

        mostrarCrupieCompleto = false;

        mao.add(baralho.comprarCarta());
        mao.add(baralho.comprarCarta());

        maoCrupie.add(baralho.comprarCarta());
        maoCrupie.add(baralho.comprarCarta());

        atualizarTela();
    }

    private void atualizarTela() {
        int valorJogador = Jogadores.calcularValorMao(mao);
        int valorCrupie = Jogadores.calcularValorMao(maoCrupie);

        areaJogador.setText(mao + "\nValor: " + valorJogador);

        if (mostrarCrupieCompleto) {
            areaCrupie.setText(maoCrupie + "\nValor: " + valorCrupie);
        } else {
            areaCrupie.setText(maoCrupie.get(0) + " + [CARTA ESCONDIDA]");
        }
    }

    private void turnoCrupie() {
        mostrarCrupieCompleto = true;

        while (Jogadores.calcularValorMao(maoCrupie) < 17) {
            maoCrupie.add(baralho.comprarCarta());
        }

        int jogador = Jogadores.calcularValorMao(mao);
        int crupie = Jogadores.calcularValorMao(maoCrupie);

        atualizarTela();

        if (crupie > 21 || jogador > crupie) {
            JOptionPane.showMessageDialog(this, "Você venceu!");
        } else if (jogador == crupie) {
            JOptionPane.showMessageDialog(this, "Empate!");
        } else {
            JOptionPane.showMessageDialog(this, "Você perdeu!");
        }
    }

    public static void main(String[] args) {
        new BlackjackGUI().setVisible(true);
    }
}