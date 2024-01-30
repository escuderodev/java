package br.com.escuderodev.screenmatch.service;

import br.com.escuderodev.screenmatch.model.DadosSerie;

public interface IConverteDados {
    <T> T obterDados(String json, Class<T> classe);
}
