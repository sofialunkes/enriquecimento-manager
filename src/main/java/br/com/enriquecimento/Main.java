package br.com.enriquecimento;

import br.com.enriquecimento.leitor.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		FileInputStream fileInput = new FileInputStream("/home/sofia/Downloads/arquivo_retorno.csv");
		InputStreamReader reader = new InputStreamReader(fileInput);
		BufferedReader br = new BufferedReader(reader);
		String linha = br.readLine();
		int cont = 0;

		while (linha != null) {
			Associado associado = new Associado();
			StringBuilder sqlInsert = new StringBuilder();
			StringBuilder sqlUpdate = new StringBuilder();
			StringBuilder sqlHistorico = new StringBuilder();
			boolean alterou = false;

			sqlInsert.append("INSERT INTO TBOD_HISTORICO_ASSOCIADO(" + "CD_ASSOCIADO, DT_ALTERACAO,");
			sqlUpdate.append("UPDATE TBOD_ASSOCIADO SET ");

			String[] dados = linha.split(";");

			associado.setNome(dados[0]);
			associado.setNomeCf(dados[1]);
			associado.setFlagNomeCf(dados[2]);
			associado.setDtNascimento(dados[3]);
			associado.setDtNascimentoCf(dados[4]);
			associado.setFlagDtNascimentoCf(dados[5]);
			associado.setCdAssociado(dados[6]);
			associado.setCpf(dados[7]);
			associado.setCpfCf(dados[8]);
			associado.setFlagCpfCf(dados[9]);
			associado.setNomeMae(dados[10]);
			associado.setNomeMaeCf(dados[11]);
			associado.setFlagNomeMaeCf(dados[12]);

			cont++;
			if (associado.getFlagNomeCf().equals("NOME ORIGINAL VALIDADO NA BASE CREDIFY")) {
				sqlUpdate.append(" ID_NOME_SERASA = 'S'");
				alterou=true;
			}
			if (associado.getFlagNomeCf().equals("NOME CORRIGIDO PELA CREDIFY")
					|| associado.getFlagNomeCf().equals("NOME ATRIBUIDO PELA CREDIFY")) {
				sqlUpdate.append(" ID_NOME_SERASA = 'S', NM_ASSOCIADO = '" + associado.getNomeCf() + "'");
				sqlInsert.append(" NM_ASSOCIADO");
				sqlHistorico.append("'" + associado.getNomeCf() + "'");
				alterou=true;
			}
			if (associado.getFlagDtNascimentoCf().equals("DATA DE NASCIMENTO CORRIGIDO PELA CREDIFY")) {

			}

			linha = br.readLine();
			if (cont == 3) {
				break;
			}

		}

	}
}
