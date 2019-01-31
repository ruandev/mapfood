package br.com.mapfood.RecordCSV;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import br.com.mapfood.domain.Motoboy;
import org.apache.commons.csv.CSVRecord;

public class LerCSVMotoboyTest {

    static List<Motoboy> listMotoboys;

    public static void main(String[] args) {

        buscaListaMoboys();
    }

    static public void buscaListaMoboys() {

        LerCSV  lerCSV = new LerCSV();

        Stream<CSVRecord> csvRecordStream = StreamSupport.stream(lerCSV.buscaInformacoesArquivoMotoboy().spliterator(), false);
        listMotoboys = csvRecordStream
                .skip(1)
                .map(linhaMotoboy ->  new Motoboy( linhaMotoboy.toMap()))
                .collect(Collectors.toList());

        listMotoboys.forEach(System.out::println);

    }
}
