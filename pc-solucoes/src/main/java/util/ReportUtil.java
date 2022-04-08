package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@SuppressWarnings({ "rawtypes" })
public class ReportUtil implements Serializable {

	private static final long serialVersionUID = -3068972006986512027L;

	public byte[] geraRelatorioPDF(List listaDados, String nomeRelatorio,HashMap<String, Object> params, ServletContext context) throws Exception {

		/* Cria a lista de dados que vem com sql da consulta feita */
		JRBeanCollectionDataSource jrbcds = new JRBeanCollectionDataSource(listaDados);

		String caminho_jasper = context.getRealPath("relatorio") + File.separator + nomeRelatorio + ".jasper";

		/* Juntado relatorio com a lista de dados */
		JasperPrint impressora_jasper = JasperFillManager.fillReport(caminho_jasper, params, jrbcds);

		return JasperExportManager.exportReportToPdf(impressora_jasper);

	}

}
