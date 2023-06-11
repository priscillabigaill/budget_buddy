package GUI;

import GUIhelper.Reload;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.filehandling.TransactionFileHandler;
import model.Transaction;
import model.data.DataManager;
import org.jfree.chart.*;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

public class TransactionDetailsPage extends javax.swing.JFrame {

    public TransactionDetailsPage() {
        initComponents();
        showPieChart();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        fromText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        toText = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 66, 90));

        jPanel2.setBackground(new java.awt.Color(141, 173, 185));

        jLabel3.setBackground(new java.awt.Color(0, 60, 99));
        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 3, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 60, 99));
        jLabel3.setText("Transaction Details");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Type", "Category", "Amount", "Description", "Date"
            }));
            jScrollPane1.setViewportView(jTable1);
            TransactionFileHandler fileHandler = new TransactionFileHandler();
            List<Transaction> transactions = fileHandler.load();

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            for (Transaction transaction : transactions) {
                String[] dataRow = new String[5];
                dataRow[0] = transaction.getType();
                dataRow[1] = transaction.getCategory().getName();
                dataRow[2] = String.valueOf(transaction.getAmount());
                dataRow[3] = transaction.getDescription();
                dataRow[4] = transaction.getDate().toString();
                model.addRow(dataRow);
            }

            // Add a ListSelectionListener to the JTable
            jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    // Check if the selection is adjusting and the row is selected
                    if (!e.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                        // Get the selected row index
                        int rowIndex = jTable1.getSelectedRow();

                        // Get the data from the selected row
                        String type = jTable1.getValueAt(rowIndex, 0).toString();
                        String category = jTable1.getValueAt(rowIndex, 1).toString();
                        int amount = Integer.parseInt(jTable1.getValueAt(rowIndex, 2).toString());
                        String description = jTable1.getValueAt(rowIndex, 3).toString();

                        String htmlText = "<html><div style='width: 200px;'>" +
                        "Type: " + type + "<br>" +
                        "Category: " + category + "<br>" +
                        "Amount: " + amount + "<br>" +
                        "Description: " + "<br>"
                        + description +
                        "</div></html>";
                        jLabel1.setText(htmlText);

                    }
                }
            });

            jPanel4.setBackground(new java.awt.Color(255, 255, 255));
            jPanel4.setMaximumSize(new java.awt.Dimension(100, 100));

            jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 12)); // NOI18N

            javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
            jPanel4.setLayout(jPanel4Layout);
            jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE))
            );
            jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(27, 27, 27)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(22, Short.MAX_VALUE))
            );

            jPanel3.setLayout(new java.awt.BorderLayout());

            jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
            jLabel4.setForeground(new java.awt.Color(255, 255, 255));
            jLabel4.setText("From:");

            fromText.setText("mm/dd/yyyy");

            jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 16)); // NOI18N
            jLabel5.setForeground(new java.awt.Color(255, 255, 255));
            jLabel5.setText("To:");

            toText.setText("mm/dd/yyyy");

            searchButton.setText("Search");
            searchButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    searchButtonActionPerformed(evt);
                }
            });

            refreshButton.setText("Refresh");
            refreshButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    refreshButtonActionPerformed(evt);
                }
            });

            jLabel2.setFont(new java.awt.Font("Helvetica", 0, 18)); // NOI18N
            jLabel2.setForeground(new java.awt.Color(255, 255, 255));
            jLabel2.setText("Detailed Information");

            javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
            jPanel1.setLayout(jPanel1Layout);
            jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(49, 49, 49)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(searchButton)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(refreshButton))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addContainerGap(55, Short.MAX_VALUE))
            );
            jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(fromText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(toText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton)
                        .addComponent(refreshButton))
                    .addGap(18, 18, 18)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 316, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(42, 42, 42))
            );

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );

            pack();
        }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Search button
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String dateStartString = fromText.getText();
        LocalDate startTime = LocalDate.parse(dateStartString, formatter);
        String dateEndString = toText.getText();
        LocalDate endTime = LocalDate.parse(dateEndString, formatter);

       
        TransactionFileHandler fileHandler = new TransactionFileHandler();
        List<Transaction> defaultTransactions = fileHandler.load();
        
        DataManager dataManager = new DataManager(defaultTransactions);
        List<Transaction> filteredTransactions = dataManager.getFilteredTransactions(startTime, endTime);
        

        // Clear the current data in the table
        DefaultTableModel tableModel = (DefaultTableModel) jTable1.getModel();
        tableModel.setRowCount(0);

        // Add the filtered transactions to the table model
        for (Transaction transaction : filteredTransactions) {
            String[] dataRow = new String[5];
            dataRow[0] = transaction.getType();
            dataRow[1] = transaction.getCategory().getName();
            dataRow[2] = String.valueOf(transaction.getAmount());
            dataRow[3] = transaction.getDescription();
            dataRow[4] = transaction.getDate().toString();
            tableModel.addRow(dataRow);
        }

        // Refresh the table to display the updated data
        jTable1.repaint();
    }

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // Refresh
        Reload.refreshPage(this, TransactionDetailsPage.class.getName());
    }

    public void showPieChart() {
    // Create dataset
    DefaultPieDataset dataset = new DefaultPieDataset();

    // Load transactions from the file using TransactionFileHandler
    TransactionFileHandler fileHandler = new TransactionFileHandler();
    List<Transaction> transactions = fileHandler.load();

    // Iterate over the transactions and add the ones with type "Expense" to the dataset
    for (Transaction transaction : transactions) {
        if (transaction.getType().equals("Expense")) {
            String category = transaction.getCategory().getName();
            double amount = transaction.getAmount();
            dataset.setValue(category, amount);
        }
    }

    // Create chart
    JFreeChart pieChart = ChartFactory.createPieChart("", dataset, false, true, false);

    PiePlot piePlot = (PiePlot) pieChart.getPlot();

    piePlot.setBackgroundPaint(Color.white); // Set the background color of PiePlot
    
    // Set custom colors for the pie slices
    piePlot.setSectionPaint("Food", new Color(0, 66, 90)); // Set custom color for Category 1

    // Create chartPanel to display chart
    ChartPanel pieChartPanel = new ChartPanel(pieChart);
    pieChartPanel.setPreferredSize(new Dimension(300, 200)); // Set the preferred size of the ChartPanel
    jPanel3.removeAll();
    jPanel3.add(pieChartPanel, BorderLayout.CENTER);
    jPanel3.revalidate();
}

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
            java.util.logging.Logger.getLogger(TransactionDetailsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TransactionDetailsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TransactionDetailsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TransactionDetailsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TransactionDetailsPage().setVisible(true);
            }
        });
    }

    private javax.swing.JTextField fromText;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField toText;
}
