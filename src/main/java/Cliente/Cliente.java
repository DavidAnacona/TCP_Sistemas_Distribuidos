/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author davii
 */
public class Cliente extends javax.swing.JFrame {

    private final int PORT = 12345;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;
    String nombreCliente;

    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
        nombreCliente = pedirNombnre();
        jl_nombreCliente.setText(nombreCliente);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jt_enviarMensaje = new javax.swing.JTextField();
        bt_enviarMensaje = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jt_mensajes = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jl_nombreCliente = new javax.swing.JLabel();
        bt_conectarServidor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        bt_enviarMensaje.setText("Enviar");
        bt_enviarMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_enviarMensajeActionPerformed(evt);
            }
        });

        jt_mensajes.setColumns(20);
        jt_mensajes.setRows(5);
        jScrollPane1.setViewportView(jt_mensajes);

        jLabel1.setText("Cliente:");

        bt_conectarServidor.setText("Conectar");
        bt_conectarServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_conectarServidorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jt_enviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bt_enviarMensaje)
                                .addGap(0, 3, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl_nombreCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_conectarServidor)
                        .addGap(9, 9, 9)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jl_nombreCliente))
                    .addComponent(bt_conectarServidor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 381, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jt_enviarMensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_enviarMensaje))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_conectarServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_conectarServidorActionPerformed
        conectar();
    }//GEN-LAST:event_bt_conectarServidorActionPerformed

    private void bt_enviarMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_enviarMensajeActionPerformed
        this.enviarMensaje();
    }//GEN-LAST:event_bt_enviarMensajeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    private String pedirNombnre() {
        nombreCliente = JOptionPane.showInputDialog(this, "Ingresa tu nombre:", "Nombre del Cliente", JOptionPane.PLAIN_MESSAGE);

        if (nombreCliente == null || nombreCliente.trim().isEmpty()) {
            jt_mensajes.append("Conexión cancelada. Debes ingresar un nombre.\n");
        }
        return nombreCliente;
    }

    private void conectar() {
        try {
            if (socket == null || socket.isClosed()) {
                socket = new Socket("localhost", PORT); // Conecta al servidor en localhost y el puerto especificado
                out = new PrintWriter(socket.getOutputStream(), true);

                // Verifica si la conexión es exitosa
                if (socket.isConnected()) {
                    jt_mensajes.append("Conectado al servidor en " + socket.getInetAddress() + " en el puerto " + socket.getPort() + "\n");
                    bt_conectarServidor.setEnabled(false);

                    // Enviar el nombre del cliente al servidor
                    out.println(nombreCliente);
                } else {
                    jt_mensajes.append("No se pudo conectar al servidor.\n");
                    return;
                }
            }

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(() -> {
                try {
                    String fromServer;
                    while ((fromServer = in.readLine()) != null) {
                        jt_mensajes.append(fromServer + "\n");
                        if ("Servidor detenido.".equals(fromServer)) {
                            desconectarCliente(); // Llama a desconectar cuando el servidor se detiene
                            break;
                        }
                    }
                } catch (IOException ex) {
                    jt_mensajes.append("Error en la comunicación con el servidor: " + ex.getMessage() + "\n");
                }
            }).start();

        } catch (IOException e) {
            jt_mensajes.append("Error al conectar con el servidor: " + e.getMessage() + "\n");
        }
    }

    private void desconectarCliente() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
                jt_mensajes.append("Desconectado del servidor.\n");
            }
            bt_conectarServidor.setEnabled(true);
        } catch (IOException e) {
            jt_mensajes.append("Error al desconectar del servidor: " + e.getMessage() + "\n");
        }
    }

    private void enviarMensaje() {
        out.println(jt_enviarMensaje.getText());
        jt_enviarMensaje.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_conectarServidor;
    private javax.swing.JButton bt_enviarMensaje;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jl_nombreCliente;
    private javax.swing.JTextField jt_enviarMensaje;
    private javax.swing.JTextArea jt_mensajes;
    // End of variables declaration//GEN-END:variables
}
