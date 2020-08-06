package com.example.felipe.clienteintegraponto;

import android.content.DialogInterface;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TelaModulos extends AppCompatActivity {
    ArrayList<TelaModuloCadastroClasse> telaCadastro;
    TelaModuloCadastroClasse  usuarioCadastro;
    UsuarioDAO usuarioDAO;
    ListView listaCadastro;
    AlertDialog.Builder dialogoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_modulos);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        montaTelaCadastro();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void montaTelaCadastro() {
        listaCadastro = (ListView) findViewById(R.id.lstModCad);

        telaCadastro = new ArrayList<TelaModuloCadastroClasse>();
        telaCadastro.clear();

        usuarioDAO = new UsuarioDAO();
        usuarioCadastro = usuarioDAO.busarUsuario(62150880059L);

        telaCadastro.add(usuarioCadastro);
        listaCadastro.setAdapter(new TelaModuloCadastroAdapter(TelaModulos.this, telaCadastro));

        listaCadastro.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final String nomFun = telaCadastro.get(position).getNomFun();
                Configuracao config = new Configuracao();

                dialogoCadastro = new AlertDialog.Builder(TelaModulos.this);
                dialogoCadastro.setTitle(config.titulo_sincronizar_cadastro);
                dialogoCadastro.setMessage(config.mensagem_sincronizar_cadastro);
                dialogoCadastro.setNegativeButton(config.botao_nao_sincronizar_cadastro, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(TelaModulos.this, "cancelado pelo usuário", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogoCadastro.setPositiveButton(config.botao_sim_sincronizar_cadastro, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        Toast.makeText(TelaModulos.this, "sincronizar" + nomFun, Toast.LENGTH_SHORT).show();
                    }
                });

                dialogoCadastro.show();
                return true;
            }
        });

        listaCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomFun = telaCadastro.get(position).getNomFun();
                Toast.makeText(TelaModulos.this, "dados " + nomFun, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
