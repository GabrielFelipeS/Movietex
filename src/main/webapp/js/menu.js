const searchButton = document.getElementById('searchButton');
const moviesArea = document.getElementById('MoviesArea');

console.log(moviesArea);

searchButton.addEventListener('click', async () => {

    const generoSelecionado = document.querySelector('input[name="genero"]:checked');
    const anoLancamento = document.getElementById('ano').value;
    const diretorSelecionado = document.getElementById('diretor').value;
    const avaliacaoMinima = document.getElementById('avaliacao_minima').value;
    const avaliacaoMaxima = document.getElementById('avaliacao_maxima').value;
    const currentUrl = window.location.href;

    let baseUrl = currentUrl.replace(/\/movies\/?$/, '');

    let url = `${baseUrl}/api/movie/advancedSearch?`;

    if (generoSelecionado) {
        url += `genre=${generoSelecionado.value}&`;
    }
    if (anoLancamento) {
        url += `year=${anoLancamento}&`;
    }
    if (diretorSelecionado) {
        url += `director=${diretorSelecionado}&`;
    }
    if (avaliacaoMinima) {
        url += `minRatingAverage=${avaliacaoMinima}&`;
    }
    if (avaliacaoMaxima) {
        url += `maxRatingAverage=${avaliacaoMaxima}&`;
    }

    url = url.slice(0, -1);

    try {
        const data = await fetch(url);
        console.log(url);
        const response = await data.json();
        console.log(response);

        moviesArea.innerHTML = '';

        if (response.length == 0) {
            moviesArea.innerHTML = `
            <div id="404" class="w-full h-full flex flex-col items-center justify-center" >
            <img src="img/figuras/404.png" alt="Erro na pesquisa" class="w-auto">
            <p class="text-primary text-2xl font-bold">Nenhum resultado para os filtros informados!</p>
        </div>`
        }else{
            response.forEach(movie => {
                moviesArea.innerHTML += `
            <div class="w-[30%] flex flex-col aspect-banner mb-5 items-center justify-center">
            <img src="${movie.poster}" alt="A Origem" class="w-[200px] object-cover rounded-lg">
            <p class="text-primary text-lg font-bold">${movie.ratingAverage} / 10</p>
            <a href="./movie/${movie.id}" class="bg-primary text-white p-2 rounded-lg hover:-translate-y-1 transition-all duration-150">Ver
                 detalhes</a>
        </div>`
            })
        }

        if(generoSelecionado){
            generoSelecionado.checked = false;
        }
        


    } catch (error) {
        console.error('Erro ao buscar dados:', error);
    }
    anoLancamento.value = '';
        diretorSelecionado.value = '';
        avaliacaoMinima.value = '';
        avaliacaoMaxima.value = '';
});
