package tabela;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Diario;
import model.Marco;

public class MarcoTable extends AbstractTableModel
{
	// VARIÁVEIS QUE REPRESENTAM AS POSIÇÕES DAS COLUNAS DA TABELA.
	
	private static final int COL_COD = 0;

	private static final int COL_VIRTUDE_CARDEAL = 1;
	
	private static final int COL_VIRTUDE_SATELITE = 2;
	
	private static final int COL_NOME = 3;
		
	private static final int COL_DATA = 4;

	private static final int COL_DESCRICAO = 5;

	// CRIA UMA LISTA QUE ABRIGARÁ OS REGISTROS RETORNADOS DA CONSULTA.
	private List<Marco> valores;
	
	// MÉTODO CONSTRUTOR DA CLASSE.
	public MarcoTable(List<Marco> valores) {
		this.valores = valores;
	}
	
	// RETORNA NÚMERO DE COLUNAS DEFINIDAS PARA TABELA
	public int getColumnCount() {
		// NESTE CASO 6
		return 6;
	}

	// RETRONA O NÚMERO DE LINHAS (registros) DA CONSULTA
	public int getRowCount() {
		return valores.size();
	}

	// PERMITE QUE OS DADOS SEJAM PREENCHIDOS EM CADA COLUNA E LINHA DA TABELA.
	public Object getValueAt(int indiceLinha, int indiceColuna) {

		// PERMITE QUE O MÉTODO SAIBA QUAL LINHA ESTA SENDO ACESSADA.
		Marco marc = valores.get(indiceLinha);
		
		//System.out.println("GetValueAT colunas: " + indiceColuna + " - Linhas" + indiceLinha);
		
		// VEREIFICA SE A COLUNA POSSUI UM VALOR IGUAL AO DA COLUNA QUE FOI
		// CONFIGURADO ANTERIORMENTE.
		if (indiceColuna == COL_COD) {
			
			return marc.getCodigo();
		
		}else if (indiceColuna == COL_VIRTUDE_CARDEAL) {
			
			return marc.getVirtudeCardeal();
		
		}else if (indiceColuna == COL_VIRTUDE_SATELITE) {
			
			return marc.getVirtudeSatelite();
			
		}else if (indiceColuna == COL_NOME) {
			
			return marc.getNome();
			
		}else if (indiceColuna == COL_DATA) {
			
			return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(marc.getData()).toString().replace("-", "/");
		
		}else if (indiceColuna == COL_DESCRICAO) {
			
			return marc.getDescricao();
			
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
			
		case COL_NOME:
			coluna = "Nome";
			break;
			
		case COL_DATA:
			coluna = "Data";
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
			
		}else if (indiceColuna == COL_NOME) {
			return String.class;
			
		}else if (indiceColuna == COL_DATA) {
			return String.class;
			
		}else if (indiceColuna == COL_DESCRICAO) {
			return String.class;	
		}
		return null;
	}
	
	//MÉTODO AUXULIAR QUE PERMITE SABER QUAL LINHA ESTÁ SENDO UTILIZADA.
	public Marco get(int linha){
		return valores.get(linha);
	}
}
