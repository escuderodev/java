package br.com.escuderodev.screenmatch.principal;

import br.com.escuderodev.screenmatch.model.DadosSerie;
import br.com.escuderodev.screenmatch.model.DadosTemporada;
import br.com.escuderodev.screenmatch.service.ConsumoApi;
import br.com.escuderodev.screenmatch.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner scan = new Scanner(System.in);
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=6585022c";
    private ConsumoApi consumoApi = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    public void exibeMenu() {
        System.out.print("\nDigite o nome da série desejada: ");
        var nomeSerie = scan.nextLine();
        var json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        System.out.println(ENDERECO + nomeSerie.replace(" ","+") + API_KEY);
        DadosSerie dadosSerie = conversor.obterDados(json, DadosSerie.class);
        System.out.println(dadosSerie);

        List<DadosTemporada> temporadas = new ArrayList<>();
        for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
            json = consumoApi.obterDados(ENDERECO + nomeSerie.replace(" ","+")+"&season="+i+API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);
        }
        temporadas.forEach(System.out::println);
    }
}
