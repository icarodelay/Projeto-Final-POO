package simon.project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class JogadorListener implements ActionListener{
	private File lista;
	private FileReader listLoader;
	private BufferedReader buffLoader;
	private BotaoListener bListener;
	public static String botao;
	public static boolean controlador;
	public static int i=0;
	
	public JogadorListener()
	{
		lista = new File("ListaCores.txt");
		controlador = false;
	}
	
	@Override
	public void actionPerformed(ActionEvent a) {
		botao = ((JButton) a.getSource()).getText();
//		TelaSimon.btnVerde.addActionListener(bListener);
//		TelaSimon.btnVermelho.addActionListener(bListener);
//		TelaSimon.btnAzul.addActionListener(bListener);
//		TelaSimon.btnAmarelo.addActionListener(bListener);
		TelaSimon.btnAmarelo.setEnabled(false);
		TelaSimon.btnAzul.setEnabled(false);
		TelaSimon.btnVerde.setEnabled(false);
		TelaSimon.btnVermelho.setEnabled(false);
		try {
			listLoader = new FileReader(lista);
			buffLoader = new BufferedReader(listLoader);
			
			try {
				TelaSimon.btnVerde.removeActionListener(bListener);
				TelaSimon.btnVermelho.removeActionListener(bListener);
				TelaSimon.btnAzul.removeActionListener(bListener);
				TelaSimon.btnAmarelo.removeActionListener(bListener);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			TelaSimon.btnVerde.addActionListener(bListener);
			TelaSimon.btnVermelho.addActionListener(bListener);
			TelaSimon.btnAzul.addActionListener(bListener);
			TelaSimon.btnAmarelo.addActionListener(bListener);
			ArrayList<String> cores = new ArrayList<String>();
			while(buffLoader.ready())
			{
				cores.add(buffLoader.readLine());
			}
//			for(int i=0;i<cores.size();i++)
//			{
//				while(controlador);
				//controlador=true;
				try{
					if(cores.get(i).compareTo(botao)!=0)
					{

						JOptionPane.showMessageDialog(null,"VOC� PERDEU A cor era " + cores.get(i)+", PONTUA��O: "+(cores.size()-1));
						FileWriter pontuacao = new FileWriter("Player.txt",true);
						@SuppressWarnings("resource")
						BufferedWriter buffpont = new BufferedWriter(pontuacao);
						buffpont.write("> "+(cores.size()-1));
						buffpont.newLine();
						buffpont.flush();
						System.out.println("Cliquei no bot�o: " + i + "   texto: " + botao);
						System.exit(0);
					}
					else
					{
						TelaSimon.btnAmarelo.setEnabled(true);
						TelaSimon.btnAzul.setEnabled(true);
						TelaSimon.btnVerde.setEnabled(true);
						TelaSimon.btnVermelho.setEnabled(true);
					}
					System.out.println("Cliquei no bot�o: " + i + "   texto: " + botao);
					
					i++;
					if(i>= cores.size())
					{
						i=0;
						TelaSimon.gthread.run();
					}
				}catch(Exception e){
					i=0;
				}
//			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"ERRO NO BANCO DE DADOS");
		}finally{
			this.close();
			controlador = false;
		}	
		
	}
	
	public void close()
	{
		try{
			buffLoader.close();
			listLoader.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null,"ERRO NO BANCO DE DADOS");
		}
		
	}
	
}