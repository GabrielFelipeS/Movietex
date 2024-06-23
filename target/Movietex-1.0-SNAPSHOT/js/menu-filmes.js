const genero = document.querySelector('input[name="genero"]:checked')?.value;
const ano  = document.getElementById('ano').value;
const diretor  = document.getElementById('diretor').value;
const avaliacao  = document.getElementById('avaliacao').value;
const searchButton  = document.getElementById('searchButton');

searchButton.addEventListener('click',async ()=>{
    const response = await fetch(`/api/filmes?genero=${genero}&ano=${ano}&diretor=${diretor}&avaliacao=${avaliacao}`);
    const filmes = await response.json();
    const filmesContainer = document.getElementById('filmes');
    filmesContainer.innerHTML = '';
    filmes.forEach(filme => {
        const filmeElement = document.createElement('div');
        filmeElement.classList.add('filme');
        filmeElement.innerHTML = `
            <img src="${filme.poster}" alt="${filme.nome}">
            <div class="filme-info">
                <h2>${filme.nome}</h2>
                <p>${filme.genero}</p>
                <p>${filme.ano}</p>
                <p>${filme.diretor}</p>
                <p>${filme.avaliacao}</p>
            </div>
        `;
        filmesContainer.appendChild(filmeElement);
    });
});