package br.com.mapfood.processors;

import br.com.mapfood.MapfoodApplication;
import br.com.mapfood.domain.Motoboy;
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
public class MotoboyProcessor {

    public List<Motoboy> lerArquivo(){
        List<Motoboy> listMotoboys = new ArrayList<>();

        ClassLoader classLoader = new MapfoodApplication().getClass().getClassLoader();

        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(',');

        try (CSVParser parser = new CSVParser(new FileReader(classLoader.getResource("filesCsv/motoboys.csv").getFile()), format)) {
            for (CSVRecord record : parser) {
                Motoboy motoboy = montarMotoboy(record);
                listMotoboys.add(motoboy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listMotoboys;
    }

    private Motoboy montarMotoboy(CSVRecord record) {
        return Motoboy.builder()
                .id(Long.valueOf(record.get(0)))
                .latitude(record.get(1))
                .longitude(record.get(2))
                .disponivel(true)
                .build();
    }
}
