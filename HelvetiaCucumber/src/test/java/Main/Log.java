package Main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FileUtils;

import Main.Actions.WebActions;

public class Log extends WebActions{

	static BufferedWriter bw = null;
	static FileWriter fw = null;
	static String ruta = null;
	static boolean debuging;
	
	public Log() throws IOException {
		String rutaOutputs = "outputs";
		Path p = Paths.get(rutaOutputs);
		try {
			if(Files.exists(p)) {
				FileUtils.cleanDirectory(new File(rutaOutputs));
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String rutaLogs = rutaOutputs + File.separator + "logs";
		File file = new File(rutaLogs);
		if (!file.exists()) {
			if (!file.getParentFile().exists()) {
				System.out.println("Creando padre");
				file.getParentFile().mkdir();
			}
			file.mkdir();
		}
		ruta = ".\\outputs\\logs\\log-" + systemDate() + ".txt"; 
	}

	public static String systemDate() {
		Date date = new Date();
		String strDateFormat = "hh_mm_ss---dd_MMM"; // El formato de fecha esta especificado
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un
																		// argumento al objeto
		String dt = objSDF.format(date);
		return dt;
	}

	public static String systemHour() {
		Date hour = new Date();
		String strDateFormat = "HH_mm_ss"; // El formato de fecha esta especificado
		SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat); // La cadena de formato de fecha se pasa como un
																		// argumento al objeto
		String time = objSDF.format(hour);
		return time;
	}

	public static void register(String msg) {
		// Con este método se mostrará en el Log (un archivo guardado en una carpeta) el
		// resultado del caso
		String log;
		log = "[" + systemHour() + "] - " + msg;
		System.out.println(log);
		writeLog(log);
	}


	public static void writeLog(String log) {
		//Este método será el encargado de escribir en el log 
		try {
			File fl = new File(ruta);
			if (!fl.exists()) {
				fl.createNewFile();
			}
			fw = new FileWriter(fl.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(log);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
