package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import entidad.Alumno;


public class Exportador {

	public void exportarJSON(List<Alumno> lista) {

	    Gson gson = new GsonBuilder().setPrettyPrinting().registerTypeAdapter(LocalDate.class, new JsonSerializer<LocalDate>() {

	                @Override
	                public JsonElement serialize(LocalDate fecha,java.lang.reflect.Type type,JsonSerializationContext context) {
	                    return new JsonPrimitive(fecha.toString());
	                }

	            })
	            .create();
	    String json = gson.toJson(lista);

	    System.out.println(json);


	    try {
	        FileWriter writer = new FileWriter("D:\\alumnos.json");
	        writer.write(json);
	        writer.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


    public void exportarXML(List<Alumno> lista) {
    	try {

    		File file = new File("D:\\alumnos.xml");
            FileWriter fileWriter = new FileWriter(file);

            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.registerModule(new JavaTimeModule());
            String xml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(lista);
            fileWriter.write(xml);
            fileWriter.close();
            System.out.println("Archivo XML creado exitosamente.");

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }


    public void exportarExcel(List<Alumno> lista) {

        String[] HEADERS = {"ID", "Nombre", "DNI", "Correo", "Fecha Nacimiento", "Estado"};
        String SHEET = "Alumnos";
        int[] ANCHOS = {3000, 8000, 5000, 10000, 6000, 4000};

        

        try {
            FileOutputStream archivo = new FileOutputStream("D:\\alumnos.xlsx");
            Workbook workbook = new XSSFWorkbook();
            Sheet hoja = workbook.createSheet(SHEET);

            for (int i = 0; i < HEADERS.length; i++) {
                hoja.setColumnWidth(i, ANCHOS[i]);
            }

            Row headerRow = hoja.createRow(0);
            for (int i = 0; i < HEADERS.length; i++) {
                headerRow.createCell(i).setCellValue(HEADERS[i]);
            }

            int fila = 1;

            for (Alumno a : lista) {

                Row row = hoja.createRow(fila++);
                row.createCell(0).setCellValue(a.getIdAlumno());
                row.createCell(1).setCellValue(a.getNombre());
                row.createCell(2).setCellValue(a.getDni());
                row.createCell(3).setCellValue(a.getCorreo());
                row.createCell(4).setCellValue(
                    a.getFechaNacimiento() != null 
                    ? a.getFechaNacimiento().toString() 
                    : ""
                );
                row.createCell(5).setCellValue(
                    a.getEstado() == 1 ? "Activo" : "Inactivo"
                );
            }

            workbook.write(archivo);
            archivo.close();
            workbook.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exportarPDF(List<Alumno> lista) {
    	try {
    		// Crear carpeta si no existe
    		File carpeta = new File("D:/alumnos");
    		if (!carpeta.exists()) {
    			carpeta.mkdirs();
    		}

    		File file = new File("D:/alumnos/alumnos.pdf");

    		// Crear documento
    		Document document = new Document();

    		// Asociar el documento con el archivo PDF
    		PdfWriter.getInstance(document, new FileOutputStream(file));

    		// Abrir documento
    		document.open();
    		
    		//Crear la cabecera
    		document.add(new com.itextpdf.text.Paragraph("Reporte de Alumnos"));
    		
    		//crea la columna
    		document.add(new com.itextpdf.text.Paragraph("ID | Nombre | DNI | Correo | Fecha Nacimiento | Estado"));
    		    		// Generar la tabla de contenido
    		for (Alumno a : lista) {
				document.add(new com.itextpdf.text.Paragraph("ID: " + a.getIdAlumno()));
				document.add(new com.itextpdf.text.Paragraph("Nombre: " + a.getNombre()));
				document.add(new com.itextpdf.text.Paragraph("DNI: " + a.getDni()));
				document.add(new com.itextpdf.text.Paragraph("Correo: " + a.getCorreo()));
				document.add(new com.itextpdf.text.Paragraph("Fecha Nacimiento: " + (a.getFechaNacimiento() != null ? a.getFechaNacimiento().toString() : "")));
				document.add(new com.itextpdf.text.Paragraph("Estado: " + (a.getEstado() == 1 ? "Activo" : "Inactivo")));
				document.add(new com.itextpdf.text.Paragraph("--------------------------------------------"));
			}
    		
    		
    		// Cerrar documento
    		document.close();

    		System.out.println("PDF generado correctamente.");
    		System.out.println(file.getAbsolutePath());

    	} catch (Exception ex) {
    		ex.printStackTrace();
    	}
	}
}
   



   