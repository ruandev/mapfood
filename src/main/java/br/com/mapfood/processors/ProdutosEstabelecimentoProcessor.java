package br.com.mapfood.processors;

import br.com.mapfood.MapfoodApplication;
import br.com.mapfood.domain.ProdutosEstabelecimento;
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
public class ProdutosEstabelecimentoProcessor {

    public List<ProdutosEstabelecimento> lerArquivo(){
        List<ProdutosEstabelecimento> listProdutoEstabelecimentos = new ArrayList<>();

        ClassLoader classLoader = new MapfoodApplication().getClass().getClassLoader();

        CSVFormat format = CSVFormat.DEFAULT.withHeader().withDelimiter(',');

        try (CSVParser parser = new CSVParser(new FileReader(classLoader.getResource("filesCsv/produtos-por-estabelecimento.csv").getFile()), format)) {
            for (CSVRecord record : parser) {
                ProdutosEstabelecimento produtoEstabelecimento = montarProdutoEstabelecimento(record);
                listProdutoEstabelecimentos.add(produtoEstabelecimento);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listProdutoEstabelecimentos;
    }

    private ProdutosEstabelecimento montarProdutoEstabelecimento(CSVRecord record) {
        return ProdutosEstabelecimento.builder()
                .itemDescription(record.get(0))
                .itemId(record.get(1))
                .restaurantId(record.get(2))
                .restaurant(record.get(3))
                .classification(record.get(4))
                .unitPrice(Double.valueOf(record.get(5)))
                .addressCity(record.get(6))
                .build();
    }
}
