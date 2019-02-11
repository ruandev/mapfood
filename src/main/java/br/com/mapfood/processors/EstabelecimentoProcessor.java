package br.com.mapfood.processors;

import br.com.mapfood.MapfoodApplication;
import br.com.mapfood.domain.Estabelecimento;
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
public class EstabelecimentoProcessor {

    public List<Estabelecimento> lerArquivo() {
        List<Estabelecimento> listEstabelecimentos = new ArrayList<>();

        ClassLoader classLoader = new MapfoodApplication().getClass().getClassLoader();

        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(',');

        try (CSVParser parser = new CSVParser(new FileReader(classLoader.getResource("filesCsv/estabelecimento-por-municipio.csv").getFile()), format)) {
            for (CSVRecord record : parser) {
                Estabelecimento estabelecimento = montarEstabelecimento(record);
                listEstabelecimentos.add(estabelecimento);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listEstabelecimentos;
    }

    private Estabelecimento montarEstabelecimento(CSVRecord record) {
        return Estabelecimento.builder()
                .codigoRestaurante(record.get(0))
                .restaurant(record.get(1))
                .addressCity(record.get(2))
                .latitude(record.get(3))
                .longitude(record.get(4))
                .dishDescription(record.get(5))
                .build();
    }
}
