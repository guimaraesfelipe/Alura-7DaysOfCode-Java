<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193043634-acaf5c81-c5f8-4b05-9c3f-d8f4e04cb679.png" />
</div>

# *Desafios Alura - 7DaysOfCode com Java*

## Dia 1 - Fazer uma chamada à API do IMDB

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>
Este primeiro dia é super importante! Ao final dele, você terá um novo conhecimento que é essencial para os próximos desafios.
Muita gente na nossa área de tecnologia é fanática por cinema, séries e filmes. Por isso mesmo, o tema geral do nosso desafio vai ter tudo a ver com isso.
Você conhece o IMDB? É provavelmente a plataforma mais famosa que agrupa basicamente todos os filmes, séries, programas de TV, atores, etc., do mundo. Você pode imaginar que o banco de dados deles deve ser colossal!
No desafio de hoje, você vai fazer o seu código Java rodar e consumir a API do IMDB! Seu objetivo será imprimir os resultados de uma busca na linha de comando.
Explicando melhor, você vai usar essa API para pesquisar os top 250 filmes e imprimir o JSON correspondente no console da sua IDE.
Para isso, você pode acessar o webservice ou API da plataforma em:

https://imdb-api.com/api

O site do IMDB tem uma documentação bem legal para você se familiarizar com os parâmetros da chamada e analisar o retorno.
Ahh, também será preciso gerar uma APIKey (uma chave de acesso), registrando o seu email no site. Com a chave em mãos, você poderá buscar filmes, séries, etc., através da API.
Por exemplo, para buscar o nome de um filme, basta usar:

https://imdb-api.com/en/API/Top250Movies/<apiKey>https://imdb-api.com/en/API/Top250Movies/

O resultado da pesquisa será um JSON parecido com esse:

    {"items":[{"id":"tt5491994","rank":"1","title":"Planet Earth II","fullTitle":"Planet Earth II (2016)"…

Mas chegou a hora de escrever o código e fazer a coisa funcionar! Vamos lá? Vou passar aqui abaixo uma espécie de passo-a-passo, para você não se perder muito neste primeiro dia. O que você vai precisar fazer:
Criar uma conta no IMDB para ter a chave de acesso ao serviço (apiKey), mas cuidado, essa chave não deve ser commitada no Github ou em outro repositório!
Criar o código Java que executará uma requisição HTTP do tipo GET. Você pode usar o pacote java.net.http e as classes HttpRequest, HttpClient e HttpResponse, além da classe URI
Executar a requisição e pegar a resposta (o JSON)
Imprimir o corpo da resposta no console
Simples, não? Nesse exercício, eu sugeri usar as classes do pacote java.net.http, mas você pode escolher qualquer outra. Se quiser, você pode inclusive usar outros conectores HTTP.
Para esse início, faça tudo no main mesmo! Posteriormente, vou te guiar na evolução desse código, para que ele seja mais maleável, orientado a objetos, e para te gerar algumas reflexões também.

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193060994-5d85861c-2f5e-4617-bf83-23e0b09f099b.png" />
</div>

## Dia 2 - Extrair informações do resultado da chamada

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

No primeiro dia, você fez uma chamada para a API do IMDB para receber o JSON dos top 250 filmes como resposta. Você deve ter recebido algo como:

    {"items":[
      {"id":"tt5491994","rank":"1","title":"Planet Earth II","fullTitle":"Planet Earth II (2016)","year":"2016","image":"....", …},
      {"id":"tt0903747","rank":"2","title":"Breaking Bad","fullTitle":"Breaking Bad (2008)","year":"2008","image":"...."...},
    ….
    ],"errorMessage":""}

A sua tarefa de hoje será parsear essa resposta. Em outras palavras, você vai extrair as informações desse JSON. Repare que o JSON possui um array de filmes, e cada filme possui vários atributos como id, título, etc.
Trabalhar com dados em JSON é algo muito comum no dia a dia para uma pessoa desenvolvedora Java e, por isso, existem várias bibliotecas para tal. Essas bibliotecas abstraem todos os detalhes, como ler e extrair informações.
No entanto, nesse desafio, a ideia é praticar os fundamentos do Java! Ou seja, trabalhar com as principais bibliotecas incluídas no JRE, como a classe String e as famosas Expressões Regulares.

Sua tarefa será extrair o título do filme e a URL da imagem a partir da resposta JSON.
Existem várias maneiras de fazer isso e, neste momento, não se preocupe ainda em escrever um código elegante. Tente usar os métodos da classe java.lang.String como substring(), split() e replace(). Você também pode usar Regex (através das classes Matcher e Pattern do pacote java.util.regex) para encontrar uma string que siga um determinado padrão.
Com o resultado do parseamento, você deverá criar diferentes listas, cada uma com um atributo do filme. Uma lista com os títulos, outra com a URL da imagem e assim por diante. Exemplo:

    List<String> titles = //parseia o título de cada filme do JSON;

    List<String> urlImages = //parseia a URL do pôster de cada filme do JSON;

    // outras listas, com os anos (year) e as notas (imDbRating)

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193596890-c42b2ed5-68fc-41d9-abbc-c137018efe0a.png" />
</div>

## Dia 3 - Modelar os dados da chamada segundo o paradigma de Orientação a Objetos

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

Recapitulando, no primeiro dia você fez uma chamada à API do IMDB para buscar um JSON com os top 250 filmes. No segundo dia, você extraiu algumas informações desse JSON, como o título dos filmes e a URL dos pôsteres.

Como resultado, você provavelmente obteve algumas listas no seu código para guardar esses atributos, algo como:

    //chamada da API omitida

    List<String> titles = parseTitles(moviesArray);
    titles.forEach(System.out::println);

    List<String> urlImages = parseUrlImages(moviesArray);
    urlImages.forEach(System.out::println);

    //outras listas para anos e notas

No desafio de hoje, a ideia será modelar, ou pelo menos iniciar uma modelagem melhor do seu código.
Pensando um pouco sobre Orientação a Objetos, uma pergunta simples pode ajudar: no caso do seu projeto, o título e o pôster se referem a que tipo de objeto? A um filme, claro. Mas você ainda não tem nenhuma estrutura que defina o que é um filme.

Qual vai ser a cara disso? Um filme (Movie) deve ter os seguintes atributos:

* título (title)
* URL da imagem do pôster (urlImage)
* nota (rating)
* ano (year)

Em outras palavras, em vez de ter várias listas diferentes, uma para cada atributo do filme, é bem melhor organizar isso em uma única List<Movie>, onde cada filme encapsula os seus próprios dados. Bora implementar essa classe?

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193599418-02d11cb1-0584-4de2-bdfa-9397ae64de30.png" />
</div>

## Dia 4 - Gerar página HTML com o retorno na API

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

Até agora, você praticou o consumo de dados de uma API. Isto é, exercitou a maneira de receber os dados JSON de uma chamada HTTP e parseá-los, a fim de transformá-los em objetos.

No desafio de hoje, você vai trabalhar com a saída e gerar uma página HTML a partir da lista de objetos que você já tem no seu código Java.
A ideia será criar uma página HTML onde você possa ver as informações sobre o filme, incluindo o pôster.
 
Para tal, vou dar um pequeno passo-a-passo para te auxiliar:

* Crie uma nova classe HTMLGenerator, que irá receber no construtor um Writer (por exemplo, PrintWriter)

* Adicione um método chamado ‘generate’, que irá receber uma List<Movie>. Nesse método, gere todo o HTML a partir da lista, usando as informações do objeto. Você pode usar métodos privados para delegar responsabilidades.
(Obs: Você deve criar e fechar o Writer no método main)

Nesse momento, você pode estar se perguntando se é uma boa prática gerar um HTML dentro de uma classe Java. Realmente não é, pois existem bibliotecas de mais alto nível que simplificam isso, mas para este desafio de Java e a fim de praticar alguns conceitos sobre Orientação a Objetos, podemos fazer uma exceção!

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193908416-dfaec9c7-c3a7-491b-8ea1-49692bba2008.png" />
</div>

## Dia 5 - Encapsular a chamada da API e o parseamento dos dados

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

No último desafio, você encapsulou toda a geração do HTML dos filmes dentro de uma classe. Uma possível implementação poderia funcionar da seguinte forma:

    PrintWriter writer = new PrintWriter("content.html");

    new HtmlGenerator(writer).generate(movies); //movies é um List<Movie>

    writer.close();

Repare que os detalhes sobre a geração do HTML não importam para quem lê o código, pois a geração do HTML foi encapsulada, e isso é ótimo. No entanto, você ainda tem provavelmente outros códigos expostos - eu estou me referindo ao código sobre a chamada HTTP da API e também ao outro que faz o parseamento do JSON.

A sua primeira tarefa neste desafio será encapsular a chamada da API dentro de uma nova classe. Você pode chamar essa classe de ImdbApiClient.

Além disso, uma segunda tarefa: o código que faz o parseamento do JSON ainda está "solto"! Para melhorar o encapsulamento e separar todas as responsabilidades em suas devidas classes, crie uma nova classe para fazer o parseamento do JSON, algo como:

    String json = //chamada da API

    List<Movie> movies = new ImdbMovieJsonParser(json).parse()

    //gerando HTML
    //…

Mãos à obra!

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/193912192-5e33c74d-2e5a-4fc6-aeb2-9c16058e0736.png" />
</div>

## Dia 6 - Abstrair as funcionalidades do seu código de forma genérica, afim de permitir a compatibilidade com APIs similares

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

O seu código evoluiu bastante e, olhando para o método main, já dá para identificar 3 responsabilidades principais:

* chamar a API
* parsear o JSON
* gerar o HTML

Provavelmente, você desenvolveu algo como:

    public static void main(String[] args) throws Exception {

        String apiKey = "<sua chave>";
        String json = new ImdbApiClient(apiKey).getBody();

        List<Movie> movies = new ImdbMovieJsonParser(json).parse();

        PrintWriter writer = new PrintWriter("content.html");
        new HtmlGenerator(writer).generate(movies);
        writer.close();
    }

Você usou a API do IMDB como exemplo para implementar o seu projeto, mas existem várias outras APIs por aí que também fornecem informações sobre filmes e séries, como, por exemplo, a API da Marvel.
Com a API da Marvel, você pode buscar séries e histórias em quadrinhos. Fique à vontade para analisar e testar a API, ela funciona de maneira similar à API do IMDB.
Voltando para o seu código e com a API da Marvel em mente, o seu desafio será que o HTML seja gerado independentemente do conteúdo em questão (seja ele um filme, uma série, uma história em quadrinhos ou outro).
Você deverá deixar o seu código mais genérico, ou seja, preparado para receber dados de outras APIs. Para isso, entram em cena as interfaces, que permitem implementações diferentes.

Então, vamos lá: o seu modelo deverá implementar uma nova interface que irá definir o comportamento comum de um conteúdo. Você pode chamá-la de Content, e ela poderá ter os seguintes métodos:

    public interface Content { 
        String title();
        String urlImage();
        String rating();
        String year();
    }

E a sua classe (ou record) Movie se tornará um Content, dessa forma:

    public class Movie implements Content {...}

Sendo assim, você também poderá pensar em uma abstração para o parser de JSON. Você pode criar uma interface chamada 'JsonParser':

    public interface JsonParser{
        public List<? extends Content> parse();
    }

Repare que o método devolve uma lista que possui elementos do tipo <? extends Content>. Como o Movie implementa a interface Content, esse código vai funcionar!

A partir daí, você poderá usar a nova interface JsonParser na classe ImdbMovieJsonParser:

    public class ImdbMovieJsonParser implements JsonParser{
        //…
    }

Resumindo, você criará duas abstrações: uma para o seu modelo chamado de Content e outra para o JsonParser. Basta que futuras implementações sigam essas interfaces e o seu gerador de HTML continuará funcionando! Ou seja, você desacoplou o parseamento do JSON da geração de HTML.

<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/194941253-d9c42edb-8e64-414c-a90f-3f007d6d6388.png" />
</div>

## Dia 7 - Implementar um padrão de ordenação personalizado para os dados retornados da API

![version](https://img.shields.io/badge/7DaysOfCode-Java-yellow)
![status](https://img.shields.io/badge/status-Concluído-brightgreen)

<h6> Descrição do Desafio: </h6>

Esse é o seu último dia de #7DaysOfCode com Java! Você avançou muito no seu projeto e, para fechar com chave de ouro, você irá voltar a mexer com o seu modelo.
E você pode me perguntar: mas por quê? Ao usar a API do IMDB, os filmes já vêm com uma ordem predefinida, descendente, baseada na nota dos filmes (rating). Tudo bem até aí, mas e se você quiser ordenar os filmes com base em algum outro critério, talvez pela ordem alfabética do nome ou pelo ano?

Bora praticar então?

No mundo Java, é possível ordenar uma coleção usando o método sort() da classe Collections, cuja base é algo como:

    Collections.sort(contentList); //mas qual é o critério de ordenação?

Esse método sabe ordenar listas, desde que você defina uma regra - é aí que vai entrar o seu modelo. A partir dele, você poderá definir essa regra, que também é chamada de "ordem natural". Ou seja, para ter essa regra no modelo, você deverá implementar um método bem definido pela interface Comparable. Dessa forma, os objetos da classe se tornarão comparáveis.

Bora lá então? Vou te dar novamente um pequeno passo-a-passo para ajudar:

Implemente a interface Comparable<? extends Content> na classe (ou record) Movie (e também na classe Series, se você a tiver criado).
Para começar, você pode implementar o método usando a nota (rating) como parâmetro de comparação. Por exemplo:

    public int compareTo(Content outro) {
        return this.rating().compareTo(outro.rating());
    }

No método main, para ordenar a lista e gerar o HTML, use:

    Collections.sort(contentList);

O método sort() está sobrecarregado, então você pode passar um Comparator como segundo parâmetro para inverter a lista:

    Collections.sort(contentList, Comparator.reverseOrder());
    
<h6> Resultado: </h6>

<div align="center">
  <img src="https://user-images.githubusercontent.com/64509839/194941813-e67919b7-2eb2-43d2-be1c-acf947e855b0.png" />
</div>
