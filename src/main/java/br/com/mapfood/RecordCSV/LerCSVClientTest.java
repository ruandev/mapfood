package br.com.mapfood.RecordCSV;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import br.com.mapfood.domain.Cliente;
import org.apache.commons.csv.CSVRecord;

public class LerCSVClientTest {

    static List<Cliente> listCLientes;

    public static void main(String[] args) {

        buscaListaClientes();
    }

    static public void buscaListaClientes() {

        LerCSV  lerCSV = new LerCSV();

        Stream<CSVRecord> csvRecordStream = StreamSupport.stream(lerCSV.buscaInformacoesArquivoClientes().spliterator(), false);
        listCLientes = csvRecordStream
                .skip(1)
                .map(linhaCliente->  new Cliente( linhaCliente.toMap()))
                .collect(Collectors.toList());

        listCLientes.forEach(System.out::println);

    }
}
