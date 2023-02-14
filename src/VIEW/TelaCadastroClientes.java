/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package VIEW;

import DAL.ModuloConexao;
import java.sql.*;
import javax.swing.JOptionPane;
//import net.proteanit.sql.DbUtils;

/**
 *
 * @author weslleyjoaquim
 */
public class TelaCadastroClientes extends javax.swing.JInternalFrame {

    /**
     * Creates new form TelaCadastroClientes
     */
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public TelaCadastroClientes() {
        initComponents();
        conexao = ModuloConexao.conector();
    }
    //Metodo para cadastrar no banco de dados
    private void cadastrar(){
        String sql = "insert into clientes(codigo,nome,rg,cpf,logradouro,nr,complemento,bairro,"
                + "cidade, uf, cep,tel,celular) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1,textCod.getText());
            pst.setString(2,textNome.getText());
            pst.setString(3,textRG.getText());
            pst.setString(4,textCPF.getText());
            pst.setString(5,textLogradouro.getText());
            pst.setString(6,textNR.getText());
            pst.setString(7,textComplemento.getText());
            pst.setString(8,textBairro.getText());
            pst.setString(9,textCidade.getText());
            pst.setString(10,textUF.getSelectedItem().toString());
            pst.setString(11,textCEP.getText());
            pst.setString(12,textTel.getText());
            pst.setString(13,textCelular.getText());
            
            int resultado = pst.executeUpdate();
            if(resultado==1){
                JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso");
                
                textNome.setText(null);
                textRG.setText(null);
                textCPF.setText(null);
                textTel.setText(null);
                textCelular.setText(null);
                textBairro.setText(null);
                textCEP.setText(null);
                textLogradouro.setText(null);
                textCidade.setText(null);
                textUF.setSelectedItem(null);
                textComplemento.setText(null);
                textNR.setText(null);
                textCod.setText(null);
            }else{
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    //Metodo para pesquisar no banco de dados
    private void consultar() {
        String sql = "select*from clientes where codigo=?"; 

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, textCod.getText());

            rs = pst.executeQuery();

            if (rs.next()) {
                textNome.setText(rs.getString("NOME"));
                textRG.setText(rs.getString("RG"));
                textCPF.setText(rs.getString("CPF"));
                textTel.setText(rs.getString("TEL"));
                textCelular.setText(rs.getString("CELULAR"));
                textBairro.setText(rs.getString("BAIRRO"));
                textCEP.setText(rs.getString("CEP"));
                textLogradouro.setText(rs.getString("LOGRADOURO"));
                textCidade.setText(rs.getString("CIDADE"));
                textUF.setSelectedItem(rs.getString("UF"));
                textComplemento.setText(rs.getString("COMPLEMENTO"));
                textNR.setText(rs.getString("NR"));
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    //Metodo para atualizar o cadastro no Banco de dados
     private void atualizar() {

        String sql = "update clientes set nome=?,rg=?,cpf=?,logradouro=?,"
                + "nr=?,complemento=?,bairro=?,cidade=?,uf=?,"
                + "cep=?,tel=?,celular=? where codigo=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(13,textCod.getText());
            pst.setString(1,textNome.getText());
            pst.setString(2,textRG.getText());
            pst.setString(3,textCPF.getText());
            pst.setString(4,textLogradouro.getText());
            pst.setString(5,textNR.getText());
            pst.setString(6,textComplemento.getText());
            pst.setString(7,textBairro.getText());
            pst.setString(8,textCidade.getText());
            pst.setString(9,textUF.getSelectedItem().toString());
            pst.setString(10,textCEP.getText());
            pst.setString(11,textTel.getText());
            pst.setString(12,textCelular.getText());
            
            
            System.out.println(pst);
            int atualizado = pst.executeUpdate();

            if (atualizado == 1) {
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");

                textNome.setText(null);
                textRG.setText(null);
                textCPF.setText(null);
                textTel.setText(null);
                textCelular.setText(null);
                textBairro.setText(null);
                textCEP.setText(null);
                textLogradouro.setText(null);
                textCidade.setText(null);
                textUF.setSelectedItem(null);
                textComplemento.setText(null);
                textNR.setText(null);
                textCod.setText(null);

            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    //Metodo de exclusão do banco de dados
     private void remover() {

        int confirma = JOptionPane.showConfirmDialog(null, "Deseja  remover ?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_NO_OPTION) {
            String sql = "delete from clientes where codigo=?";

            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, textCod.getText());
                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!!!!");
                textNome.setText(null);
                textRG.setText(null);
                textCPF.setText(null);
                textTel.setText(null);
                textCelular.setText(null);
                textBairro.setText(null);
                textCEP.setText(null);
                textLogradouro.setText(null);
                textCidade.setText(null);
                textUF.setSelectedItem(null);
                textComplemento.setText(null);
                textNR.setText(null);
                textCod.setText(null);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);

            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textNome = new javax.swing.JTextField();
        textCod = new javax.swing.JTextField();
        textRG = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textCPF = new javax.swing.JTextField();
        textTel = new javax.swing.JTextField();
        textCelular = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textLogradouro = new javax.swing.JTextField();
        textBairro = new javax.swing.JTextField();
        textComplemento = new javax.swing.JTextField();
        textCidade = new javax.swing.JTextField();
        textNR = new javax.swing.JTextField();
        textCEP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textUF = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        btnAddCliente = new javax.swing.JButton();
        btnPesquisaCliente = new javax.swing.JButton();
        btnEditaCliente = new javax.swing.JButton();
        btnDeletaCliente = new javax.swing.JButton();
        btnImprimeCliente = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Clientes");

        textNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNomeActionPerformed(evt);
            }
        });

        textCod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCodActionPerformed(evt);
            }
        });

        textRG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRGActionPerformed(evt);
            }
        });

        jLabel1.setText("Código");

        jLabel2.setText("Nome:");

        jLabel3.setText("CPF:");

        jLabel4.setText("RG:");

        jLabel5.setText("Telefone Celular:");

        jLabel6.setText("Telefone:");

        textCPF.setText("   .   .   -");
        textCPF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCPFActionPerformed(evt);
            }
        });

        textTel.setText("(  )");
        textTel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTelActionPerformed(evt);
            }
        });

        textCelular.setText("(  )");
        textCelular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCelularActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel7.setText("Logradouro:");

        jLabel8.setText("Bairro:");

        jLabel9.setText("Complemento:");

        jLabel11.setText("Cidade:");

        textLogradouro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textLogradouroActionPerformed(evt);
            }
        });

        textBairro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textBairroActionPerformed(evt);
            }
        });

        textComplemento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textComplementoActionPerformed(evt);
            }
        });

        textCidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCidadeActionPerformed(evt);
            }
        });

        textNR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNRActionPerformed(evt);
            }
        });

        textCEP.setText("    -");
        textCEP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCEPActionPerformed(evt);
            }
        });

        jLabel13.setText("Nº:");

        jLabel14.setText("CEP:");

        jLabel15.setText("UF:");

        textUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PE", "PI", "PR", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel11)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textBairro, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textCidade, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textNR, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(textCEP, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(textUF, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textLogradouro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textBairro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(textNR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textComplemento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(textCEP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(textUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Endereço");

        btnAddCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/IMAGE/userAdicionar.png"))); // NOI18N
        btnAddCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddClienteActionPerformed(evt);
            }
        });

        btnPesquisaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/IMAGE/userPesquisar.png"))); // NOI18N
        btnPesquisaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaClienteActionPerformed(evt);
            }
        });

        btnEditaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/IMAGE/userEditar.png"))); // NOI18N
        btnEditaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditaClienteActionPerformed(evt);
            }
        });

        btnDeletaCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/IMAGE/userDeletar.png"))); // NOI18N
        btnDeletaCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletaClienteActionPerformed(evt);
            }
        });

        btnImprimeCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VIEW/IMAGE/impressora.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnAddCliente)
                                .addGap(30, 30, 30)
                                .addComponent(btnPesquisaCliente)
                                .addGap(37, 37, 37)
                                .addComponent(btnEditaCliente)
                                .addGap(34, 34, 34)
                                .addComponent(btnDeletaCliente)
                                .addGap(31, 31, 31)
                                .addComponent(btnImprimeCliente))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6))
                                        .addGap(58, 58, 58))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(textTel, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                                                .addComponent(textCelular))
                                            .addComponent(textRG, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(textCod, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(59, 59, 59))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(312, 312, 312)
                .addComponent(jLabel12)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textCod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textRG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCPF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textTel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnPesquisaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddCliente)
                            .addComponent(btnEditaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnImprimeCliente, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(108, 108, 108))
        );

        setBounds(0, 0, 732, 598);
    }// </editor-fold>//GEN-END:initComponents

    private void textNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNomeActionPerformed

    private void textCodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCodActionPerformed

    private void textRGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textRGActionPerformed

    private void textCPFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCPFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCPFActionPerformed

    private void textTelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTelActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textTelActionPerformed

    private void textCelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCelularActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCelularActionPerformed

    private void textLogradouroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textLogradouroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textLogradouroActionPerformed

    private void textBairroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textBairroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textBairroActionPerformed

    private void textComplementoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textComplementoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textComplementoActionPerformed

    private void textCidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCidadeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCidadeActionPerformed

    private void textNRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNRActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textNRActionPerformed

    private void textCEPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCEPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCEPActionPerformed

    private void btnAddClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddClienteActionPerformed
        // TODO add your handling code here:
        cadastrar();
    }//GEN-LAST:event_btnAddClienteActionPerformed

    private void btnPesquisaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaClienteActionPerformed
        // TODO add your handling code here:
        consultar();
    }//GEN-LAST:event_btnPesquisaClienteActionPerformed

    private void btnEditaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditaClienteActionPerformed
        // TODO add your handling code here:
        atualizar();
    }//GEN-LAST:event_btnEditaClienteActionPerformed

    private void btnDeletaClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletaClienteActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btnDeletaClienteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddCliente;
    private javax.swing.JButton btnDeletaCliente;
    private javax.swing.JButton btnEditaCliente;
    private javax.swing.JButton btnImprimeCliente;
    private javax.swing.JButton btnPesquisaCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField textBairro;
    private javax.swing.JTextField textCEP;
    private javax.swing.JTextField textCPF;
    private javax.swing.JTextField textCelular;
    private javax.swing.JTextField textCidade;
    private javax.swing.JTextField textCod;
    private javax.swing.JTextField textComplemento;
    private javax.swing.JTextField textLogradouro;
    private javax.swing.JTextField textNR;
    private javax.swing.JTextField textNome;
    private javax.swing.JTextField textRG;
    private javax.swing.JTextField textTel;
    private javax.swing.JComboBox<String> textUF;
    // End of variables declaration//GEN-END:variables

    
}
