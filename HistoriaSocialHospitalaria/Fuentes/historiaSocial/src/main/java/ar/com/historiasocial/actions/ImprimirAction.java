package ar.com.historiasocial.actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import ar.com.historiasocial.dao.PracticaDAO;
import ar.com.historiasocial.entities.Practica;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

public class ImprimirAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long		serialVersionUID	= 1L;

	private int						id;
	private transient InputStream	fileStream;

	PracticaDAO						practicaDAO;

	private static final Logger		LOGGER				= Logger.getLogger(ImprimirAction.class);

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public InputStream getFileStream(){
		return fileStream;
	}

	public void setFileStream(InputStream fileStream){
		this.fileStream = fileStream;
	}

	@Override
	public String execute(){
		Practica prac = practicaDAO.retrieveById(this.getId());
		try {

			String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/");
			System.out.print(path);
			File f = new File(prac.getId() + ".pdf");
			OutputStream file = new FileOutputStream(f);

			Document document = new Document();
			PdfWriter.getInstance(document, file);

			document.open();

			document.add(new Paragraph("Nro de Practica: " + prac.getId()));
			document.add(new Paragraph("Profesional a Cargo: " + prac.getProfesional().getApellido() + ", " + prac.getProfesional().getNombre()));
			document.add(new Paragraph("Matricula: " + prac.getProfesional().getMatricula()));
			document.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------"));
			document.add(new Paragraph(" "));
			PdfPTable table = new PdfPTable(2);
			PdfPCell cell;
			table.getDefaultCell().setBorderColor(CMYKColor.WHITE);
			cell = new PdfPCell();
			cell.setBorderColor(CMYKColor.WHITE);
			table.addCell("Detalle: ");
			table.addCell(prac.getDetalle());
			table.addCell("Fecha Practica: ");
			table.addCell(prac.getFechaPractica().toString());
			table.addCell("Fecha Carga: ");
			table.addCell(prac.getFechaCarga().toString());
			table.addCell("Tipo Practica: ");
			table.addCell(prac.getTipoPractica().getCodigo() + " " + prac.getTipoPractica().getDescripcion());
			table.addCell("Tipo Problematica: ");
			table.addCell(prac.getTipoProblematica().getCodigo() + " " + prac.getTipoProblematica().getDescripcion());
			table.addCell("Nro Historia Social: ");
			table.addCell(String.valueOf(prac.getHistoriaSocial().getId()));
			table.addCell("Paciente: ");
			table.addCell(prac.getHistoriaSocial().getPaciente().getApellidos());
			document.add(table);
			document.add(new Paragraph(
					"----------------------------------------------------------------------------------------------------------------------------------"));

			document.close();
			file.close();

			fileStream = new FileInputStream(new File(f.getAbsolutePath()));

			return SUCCESS;
		} catch (FileNotFoundException e) {
			LOGGER.error("Se se puede leer el archivo PDF de la práctica", e);
		} catch (DocumentException e) {
			LOGGER.error("Ocurrio un error manipulando el PDF", e);
		} catch (IOException e) {
			LOGGER.error("Error intentando leer el archivo", e);
		}
		return INPUT;
	}

	public PracticaDAO getPracticaDAO(){
		return practicaDAO;
	}

	public void setPracticaDAO(PracticaDAO practicaDAO){
		this.practicaDAO = practicaDAO;
	}
}
