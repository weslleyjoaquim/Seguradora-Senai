package br.com.seguradora.dao;

import br.com.seguradora.view.TelaLogin;
import br.com.seguradora.view.TelaPrincipal;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import br.com.seguradora.util.ModuloConexao;


public class TelaLoginDAO {
    Connection conexao = ModuloConexao.conector();
    PreparedStatement pst = null;
    ResultSet rs = null;
    public void logar(javax.swing.JTextField txtUsuario,javax.swing.JPasswordField txtSenha){

        String sql = "SELECT * FROM usuarios WHERE LOGIN=? AND SENHA=?";

        try{
            //prepara a consulta no BD
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUsuario.getText());
            pst.setString(2, txtSenha.getText());

            rs=pst.executeQuery();

            if(rs.next()){
                System.out.println("Logado com sucesso");
                new TelaPrincipal().setVisible(true);

            }
            else{
                JOptionPane.showMessageDialog(null, "Usuário incorreto");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }

}
