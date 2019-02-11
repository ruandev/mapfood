package br.com.mapfood.processors;

import br.com.mapfood.MapfoodApplication;
import br.com.mapfood.domain.Cliente;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteProcessor {

    public List<Cliente> lerArquivo() {
        List<Cliente> listClientes = new ArrayList<>();

        ClassLoader classLoader = new MapfoodApplication().getClass().getClassLoader();

        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(',');

        try (CSVParser parser = new CSVParser(new FileReader(classLoader.getResource("filesCsv/clientes.csv").getFile()), format)) {
            for (CSVRecord record : parser) {
                Cliente cliente = montarCliente(record);
                listClientes.add(cliente);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listClientes;
    }

    private Cliente montarCliente(CSVRecord record) {
        return Cliente.builder()
                .id(Long.valueOf(record.get(0)))
                .latitude(record.get(1))
                .longitude(record.get(2))
                .build();
    }
}