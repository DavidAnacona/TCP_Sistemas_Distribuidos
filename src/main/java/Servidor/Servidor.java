/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author davii
 */
public class Servidor extends javax.swing.JFrame {

    private final int PORT = 12345;
    private ServerSocket serverSocket;
    private boolean servidorActivo = true;
    private List<PrintWriter> listaClientes = new ArrayList<>();

    /**
     * Creates new form Servidor
     */
    public Servidor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_iniciar_servidor = new javax.swing.JButton();
        btn_detener_servidor = new javax.swing.JButton();
        js_mensajes = new javax.swing.JScrollPane();
        jt_mensajes = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btn_iniciar_servidor.setText("Iniciar servidor");
        btn_iniciar_servidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_iniciar_servidorActionPerformed(evt);
            }
        });

        btn_detener_servidor.setText("Detener servidor");
        btn_detener_servidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_detener_servidorActionPerformed(evt);
            }
        });

        jt_mensajes.setColumns(20);
        jt_mensajes.setRows(5);
        js_mensajes.setViewportView(jt_mensajes);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(btn_iniciar_servidor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                .addComponent(btn_detener_servidor)
                .addGap(71, 71, 71))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(js_mensajes)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_iniciar_servidor)
                    .addComponent(btn_detener_servidor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(js_mensajes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_iniciar_servidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_iniciar_servidorActionPerformed
        iniciarServidor();
    }//GEN-LAST:event_btn_iniciar_servidorActionPerformed

    private void btn_detener_servidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_detener_servidorActionPerformed
        detenerServidor();
    }//GEN-LAST:event_btn_detener_servidorActionPerformed

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
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Servidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Servidor().setVisible(true);
            }
        });
    }

    private void iniciarServidor() {
        servidorActivo = true;
        new Thread(() -> {
            try {
                InetAddress addr = InetAddress.getLocalHost();
                serverSocket = new ServerSocket(PORT);

                if (serverSocket.isBound()) {
                    jt_mensajes.append("Servidor TCP en ejecución: " + addr + " ,Puerto " + serverSocket.getLocalPort() + "\n");
                    btn_iniciar_servidor.setEnabled(false);
                } else {
                    jt_mensajes.append("Error: No se pudo iniciar el servidor en el puerto " + PORT + "\n");
                    return;
                }

                while (servidorActivo) {
                    Socket clientSocket = serverSocket.accept();
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                    listaClientes.add(out);

                    new Thread(new ClienteHandler(clientSocket, out)).start();
                }
            } catch (SocketException ex) {
                if (servidorActivo) {
                    jt_mensajes.append("Error en el servidor: " + ex.getMessage() + "\n");
                } else {
                    jt_mensajes.append("Servidor detenido.\n");
                }
            } catch (IOException ex) {
                jt_mensajes.append("Error en el servidor: " + ex.getMessage() + "\n");
            } finally {
                try {
                    if (serverSocket != null && !serverSocket.isClosed()) {
                        serverSocket.close();
                    }
                } catch (IOException e) {
                    jt_mensajes.append("Error cerrando el servidor: " + e.getMessage() + "\n");
                }
            }
        }).start();
    }

    private void detenerServidor() {
        servidorActivo = false;
        btn_iniciar_servidor.setEnabled(true);

        try {
            for (PrintWriter out : listaClientes) {
                out.println("Servidor detenido.");
            }

            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }

            jt_mensajes.append("Servidor detenido.\n");
        } catch (IOException e) {
            jt_mensajes.append("Error al detener el servidor: " + e.getMessage() + "\n");
        }
    }

    private class ClienteHandler implements Runnable {

        private final Socket clientSocket;
        private final PrintWriter out;
        private BufferedReader in;

        public ClienteHandler(Socket clientSocket, PrintWriter out) {
            this.clientSocket = clientSocket;
            this.out = out;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                String nombreCliente = in.readLine();
                jt_mensajes.append("Cliente conectado: " + nombreCliente + "\n");

                String linea;
                while ((linea = in.readLine()) != null) {
                    jt_mensajes.append(nombreCliente + ": " + linea + "\n");

                    // Enviar mensaje a todos los clientes conectados excepto al que lo envió
                    for (PrintWriter writer : listaClientes) {
                        if (writer != out) {
                            writer.println(nombreCliente + ": " + linea);
                        }
                    }
                }
            } catch (IOException ex) {
                jt_mensajes.append("Error en la comunicación con el cliente: " + ex.getMessage() + "\n");
            } finally {
                try {
                    if (clientSocket != null && !clientSocket.isClosed()) {
                        clientSocket.close();
                    }
                    listaClientes.remove(out);
                } catch (IOException e) {
                    jt_mensajes.append("Error al cerrar la conexión con el cliente: " + e.getMessage() + "\n");
                }
            }
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_detener_servidor;
    private javax.swing.JButton btn_iniciar_servidor;
    private javax.swing.JScrollPane js_mensajes;
    private javax.swing.JTextArea jt_mensajes;
    // End of variables declaration//GEN-END:variables
}
