package br.com.mapfood.RecordCSV;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class LerCSV {

    public CSVParser buscaInformacoesArquivoMotoboy() {

        String documentsPath = "/home/kaio/codenation/mapfood/";
        Path documentsDirectory = Paths.get(documentsPath);
        String fileName = "motoboys.csv";
        Path csvPath = documentsDirectory.resolve(fileName);
        CSVParser csvParser = null;
        try {
            csvParser = CSVParser.parse(csvPath, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("id",
                    "Longitude", "Latitude"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return csvParser;
    }

    public CSVParser buscaInformacoesArquivoClientes() {

        String documentsPath = "/home/kaio/codenation/mapfood/";
        Path documentsDirectory = Paths.get(documentsPath);
        String fileName = "clientes.csv";
        Path csvPath = documentsDirectory.resolve(fileName);
        CSVParser csvParser = null;
        try {
            csvParser = CSVParser.parse(csvPath, Charset.defaultCharset(), CSVFormat.DEFAULT.withHeader("id","Longitude", "Latitude"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return csvParser;
    }
}