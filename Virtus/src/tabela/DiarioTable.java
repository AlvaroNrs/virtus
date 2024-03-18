package tabela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import model.Diario;

public class DiarioTable extends AbstractTableModel {

	
	// VARIÁVEIS QUE REPRESENTAM AS POSIÇÕES DAS COLUNAS DA TABELA.
	
				private static final int COL_COD = 0;
			
				private static final int COL_VIRTUDE_CARDEAL = 1;
				
				private static final int COL_VIRTUDE_SATELITE = 2;
					
				private static final int COL_ULTIMA_MODIFICACAO = 3;
			
				private static final int COL_DESCRICAO = 4;
			
				// CRIA UMA LISTA QUE ABRIGARÁ OS REGISTROS RETORNADOS DA CONSULTA.
				private List<Diario> valores;
				
				// MÉTODO CONSTRUTOR DA CLASSE.
				public DiarioTable(List<Diario> valores) {
					this.valores = valores;
				}
				
				// RETORNA NÚMERO DE COLUNAS DEFINIDAS PARA TABELA
				public int getColumnCount() {
					// NESTE CASO 5
					return 5;
				}

				// RETRONA O NÚMERO DE LINHAS (registros) DA CONSULTA
				public int getRowCount() {
					return valores.size();
				}

				// PERMITE QUE OS DADOS SEJAM PREENCHIDOS EM CADA COLUNA E LINHA DA TABELA.
				public Object getValueAt(int indiceLinha, int indiceColuna) {

					// PERMITE QUE O MÉTODO SAIBA QUAL LINHA ESTA SENDO ACESSADA.
					Diario diar = valores.get(indiceLinha);
					
					//System.out.println("GetValueAT colunas: " + indiceColuna + " - Linhas" + indiceLinha);
					
					// VEREIFICA SE A COLUNA POSSUI UM VALOR IGUAL AO DA COLUNA QUE FOI
					// CONFIGURADO ANTERIORMENTE.
					if (indiceColuna == COL_COD) {
						
						return diar.getCodigo();
					
					}else if (indiceColuna == COL_VIRTUDE_CARDEAL) {
						
						return diar.getVirtudeCardeal();
					
					}else if (indiceColuna == COL_VIRTUDE_SATELITE) {
						
						return diar.getVirtudeSatelite();
						
					}else if (indiceColuna == COL_ULTIMA_MODIFICACAO) {
						
						return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(diar.getUltimaModificacao()).toString().replace("-", "/");
					
					}else if (indiceColuna == COL_DESCRICAO) {
						
						return diar.getDescricao();
						
					}
					return null;
				}
				
				// PERMITE CONFIGURAR OS NOMES DAS COLUNAS.
				public String getColumnName(int col) {
					String coluna = "";
					switch (col) {
					
					case COL_COD:
						coluna = "Código";
						break;
						
					case COL_VIRTUDE_CARDEAL:
						coluna = "Virtude Cardeal";
						break;
						
					case COL_VIRTUDE_SATELITE:
						coluna = "Virtude Satélite";
						break;
						
					case COL_ULTIMA_MODIFICACAO:
						coluna = "Última Modificação";
						break;		
					
					case COL_DESCRICAO:
						coluna = "Descrição";
						break;
						
					default:
						throw new IllegalArgumentException("Coluna Inválida!");
					}
					return coluna;
				}
				
				//PERMITE SABER O TIPO DE DADOS DE CADA COLUNA.
				public Class<?> getColumnClass(int indiceColuna) {
					
					if (indiceColuna == COL_COD) {
						return Integer.class;
					
					}else if (indiceColuna == COL_VIRTUDE_CARDEAL) {
						return String.class;
						
					}else if (indiceColuna == COL_VIRTUDE_SATELITE) {
						return String.class;
						
					}else if (indiceColuna == COL_ULTIMA_MODIFICACAO) {
						return String.class;
						
					}else if (indiceColuna == COL_DESCRICAO) {
						return String.class;	
					}
					return null;
				}
				
				//MÉTODO AUXULIAR QUE PERMITE SABER QUAL LINHA ESTÁ SENDO UTILIZADA.
				public Diario get(int linha){
					return valores.get(linha);
				}
	
	
}
